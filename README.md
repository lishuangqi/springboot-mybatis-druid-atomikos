# springboot-mybatis-druid-atomikos
mybatis-plus支持AOP动态数据源切换，atomikos分布式事务

一：JTA：Java Transaction API，JTA允许应用程序执行分布式事务处理——在两个或多个网络计算机资源上访问并且更新数据，对JTA接口主要有三种实现：

   1.1、Atomikos事务管理器”： Atomikos是一个非常流行的开源事务管理器，并且可以嵌入到Spring Boot应用中。可以使用 spring-boot-starter-jta-atomikos Starter去获取正确的Atomikos库。Spring Boot会自动配置Atomikos，并将合适的 depends-on 应用到Spring Beans上，确保它们以正确的顺序启动和关闭。

二：我们这里使用 Atomikos 来实现分布式事务的管理。

    2.1、首先添加 Atomikos maven 依赖：

    <!-- jta  atomikos 分布式事务管理依赖-->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-jta-atomikos</artifactId>
    </dependency>
   2.2、更改 application.yml 内容，需要注意的地方是将spring.datasource.type的值从com.alibaba.druid.pool.DruidDataSource更改为com.alibaba.druid.pool.xa.DruidXADataSource类：

##问题1 接入分布式事务后，动态数据源不能切换
DataSource必须切换为XADataSource，连接池环卫DruidXADataSource
    @Primary
    @Bean(name = "bigdataDataSource")
    public DataSource bigdataDataSource(Environment env) {
        String sourceName = "bigdata";
        Properties prop = build(env, basePackage+sourceName+".");
        MysqlXADataSource mysqlXaDataSource = new MysqlXADataSource();
        mysqlXaDataSource.setUrl(prop.getProperty("url"));
        mysqlXaDataSource.setUser(prop.getProperty("username"));
        mysqlXaDataSource.setPassword(prop.getProperty("password"));

        AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
        xaDataSource.setXaDataSource(mysqlXaDataSource);
        xaDataSource.setUniqueResourceName(sourceName);
        xaDataSource.setPoolSize(5);
        xaDataSource.setXaProperties(prop);
        return xaDataSource;
    }
##问题3 重写sqlSessionFactory
SqlSessionFactoryBean 修改 MybatisSqlSessionFactoryBean，mybatis才能正常启动

    
##问题4 接入分布式事务后，动态数据源不能切换
当我们配置了事物管理器和拦截Service中的方法后，每次执行Service中方法前会开启一个事务，
并且同时会缓存一些东西：DataSource、SqlSessionFactory、Connection等，
所以，我们在外面再怎么设置要求切换数据源也没用，因为Conneciton都是从缓存中拿的，
所以我们要想能够顺利的切换数据源，实际就是能够动态的根据DatabaseType获取不同的Connection，
并且要求不能影响整个事物的特性。
重写Transaction，注入到mybatis里sqlSessionFactory， bean.setTransactionFactory(new MultiDataSourceTransactionFactory());
 @Primary
    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory testSqlSessionFactory(@Qualifier("dynamicDataSource") DataSource dataSource)
            throws Exception {
        MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setTransactionFactory(new MultiDataSourceTransactionFactory());
//        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/**/*Dao.xml"));// 扫描指定目录的xml
        return bean.getObject();
    }