package com.lishuangqi.datasources;



import org.apache.ibatis.session.TransactionIsolationLevel;
import org.apache.ibatis.transaction.Transaction;
import org.mybatis.spring.transaction.SpringManagedTransactionFactory;

import javax.sql.DataSource;

/**
 * <P>支持Service内多数据源切换的Factory</P>
 *
 * @author lishuangqi
 * @date 2019/5/16 15:09
 * @since
 */
public class MultiDataSourceTransactionFactory extends SpringManagedTransactionFactory {
    @Override
    public Transaction newTransaction(DataSource dataSource, TransactionIsolationLevel level, boolean autoCommit) {
        return new MultiDataSourceTransaction(dataSource);
    }
}
