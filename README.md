# springboot-mybatis-druid-atomikos
mybatis-plus支持AOP动态数据源切换，atomikos分布式事务

一、 项目说明
本用例基于springboot+mybatis+druid+atomikos 配置动态多数据源， 实现分布式事务。
源码仓库地址： https://github.com/lishuangqi/springboot-mybatis-druid-atomikos

根据网上的搭建始终没实现atomikos分布式事务，经过两天网上查询资料终于实现了。刚开始分布式事务加入后，多数据源切换不能用了，一个线程内执行了第一个数据源service后，第二数据service不触发动态切换数据源(http://localhost:8101/testTrans)。单独访问第二数据源方法又是可以切换的(http://localhost:8101/testTrans1，http://localhost:8101/testTrans2)。

经查找 springboot+mybatis解决多数据源切换事务控制不生效的问题https://blog.csdn.net/gaoshili001/article/details/79378902
springboot的生命式事务需要重写Transaction，就能切换数据源了。

原因：查看源代码中DataSourceTransactionManager这个类

       当我们配置了事物管理器和拦截Service中的方法后，每次执行Service中方法前会开启一个事务，并且同时会缓存一些东西：DataSource、SqlSessionFactory、Connection等，所以，我们在外面再怎么设置要求切换数据源也没用，因为Conneciton都是从缓存中拿的，所以我们要想能够顺利的切换数据源，实际就是能够动态的根据DatabaseType获取不同的Connection，并且要求不能影响整个事物的特性。

JTA(Java Transaction API)：是J2EE的编程接口规范，它是XA协议的JAVA实现。它主要定义了：

一个事务管理器的接口javax.transaction.TransactionManager，定义了有关事务的开始、提交、撤回等>操作。
一个满足XA规范的资源定义接口javax.transaction.xa.XAResource，一种资源如果要支持JTA事务，就需要让它的资源实现该XAResource接口，并实现该接口定义的两阶段提交相关的接口。
如果我们有一个应用，它使用JTA接口实现事务，应用在运行的时候，就需要一个实现JTA的容器，一般情况下，这是一个J2EE容器，像JBoss，Websphere等应用服务器。但是，也有一些独立的框架实现了JTA，例如Atomikos, bitronix都提供了jar包方式的JTA实现框架。这样我们就能够在Tomcat或者Jetty之类的服务器上运行使用JTA实现事务的应用系统。
在上面的本地事务和外部事务的区别中说到，JTA事务是外部事务，可以用来实现对多个资源的事务性。它正是通过每个资源实现的XAResource来进行两阶段提交的控制。感兴趣的同学可以看看这个接口的方法，除了commit, rollback等方法以外，还有end(), forget(), isSameRM(), prepare()等等。光从这些接口就能够想象JTA在实现两阶段事务的复杂性。
Atomikos事务管理器： Atomikos是一个非常流行的开源事务管理器，并且可以嵌入到Spring Boot应用中。可以使用 spring-boot-starter-jta-atomikos Starter去获取正确的Atomikos库。Spring Boot会自动配置Atomikos，并将合适的 depends-on 应用到Spring Beans上，确保它们以正确的顺序启动和关闭。

 
二、动态多数据源，分布事务
这里我们创建druid数据源的时候，创建的是DruidXADataSource，它继承自DruidDataSource并支持XA分布式事务；
使用 AtomikosDataSourceBean 包装我们创建的DruidXADataSource，使得数据源能够被 JTA 事务管理器管理；
--------------------- 
作者：nicklsq 
来源：CSDN 
原文：https://blog.csdn.net/nicklsq/article/details/90286726 
版权声明：本文为博主原创文章，转载请附上博文链接！

##问题1 接入分布式事务后，动态数据源不能切换
DataSource必须切换为XADataSource，连接池环卫DruidXADataSource
    @Primary
    @Bean(name = "bigdataDataSource")
    public DataSource bigdataDataSource(Environment env) {
        String sourceName = "bigdata";
       Properties prop = build(env, basePackage+sourceName+".");
       AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
       xaDataSource.setXaDataSourceClassName(xaDataSourceClassName);
       xaDataSource.setUniqueResourceName(sourceName);
       xaDataSource.setPoolSize(5);
       xaDataSource.setXaProperties(prop);
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
MultiDataSourceTransaction.java
MultiDataSourceTransactionFactory.java
    @Primary
    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory testSqlSessionFactory(@Qualifier("dynamicDataSource") DataSource dataSource)
            throws Exception {
        MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setTransactionFactory(new MultiDataSourceTransactionFactory());
        return bean.getObject();
    }