package com.openlabs.sample.config;

import javax.sql.DataSource;

import org.apache.ignite.transactions.TransactionConcurrency;
import org.apache.ignite.transactions.spring.SpringTransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class TransactionConfig {

	@Autowired
	private ApplicationContext context;
	
	@Primary
	@Bean(name = "primaryTransactionManager")
    public PlatformTransactionManager primaryTransactionManager(@Autowired @Qualifier("primaryDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
	
	@Bean(name = "igniteTransactionManager")
    public PlatformTransactionManager igniteTransactionManager(@Autowired @Qualifier("igniteDataSource") DataSource dataSource) {
		String cfgPath = "ignite-config.xml";
//		String igniteInstanceName = "SampleAppClient";
		SpringTransactionManager springTransactionManager = new SpringTransactionManager();
//		springTransactionManager.setIgniteInstanceName(igniteInstanceName);
		springTransactionManager.setConfigurationPath(cfgPath);
//		springTransactionManager.setApplicationContext(this.context);
        return springTransactionManager;
//        return new DataSourceTransactionManager(dataSource);
    }
	
//	@Primary
//	@Bean(name = "transactionManager")
//	public PlatformTransactionManager globalTransactionManager(@Qualifier("primaryTransactionManager") PlatformTransactionManager primaryTransactionManager
//			, @Qualifier("igniteTransactionManager") PlatformTransactionManager igniteTransactionManager) {
//		return new ChainedTransactionManager(primaryTransactionManager, igniteTransactionManager);
//	}
}
