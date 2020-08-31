package com.openlabs.sample.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@MapperScan(basePackages = "com.openlabs.sample.mapper.cache", sqlSessionFactoryRef = "igniteSqlSessionFactory")
@EnableConfigurationProperties(IgniteProperties.class)
@Configuration
public class IgniteConfig {
	
	@Autowired
    private IgniteProperties properties;
	
//	@Bean(name = "igniteDataSource")
//	public DataSource dataSource() {
//		String igniteHomePath = System.getProperty("user.dir").concat(File.separator).concat("ignite");
//		log.debug("IGNITEHOME:{}", igniteHomePath);
//		IgniteUtils.setIgniteHome(igniteHomePath);
//		String url = "jdbc:ignite:cfg://distributedJoins=true:user=test:password=test@file:///" + igniteHomePath
//				+ "/config/default-config.xml";
//		DriverManagerDataSource dataSource = new DriverManagerDataSource(url);
//		dataSource.setDriverClassName("org.apache.ignite.IgniteJdbcDriver");
//		return dataSource;
//	}
	
	@Bean(name = "igniteDataSource")
	@ConfigurationProperties(prefix = "spring.ignite.datasource")
	public DataSource dataSource() {
		DataSource dataSource = DataSourceBuilder.create().build();
		return dataSource;
	}

	@Bean(name = "igniteSqlSessionFactory")
	public SqlSessionFactory sqlSessionFactory(@Autowired @Qualifier("igniteDataSource") DataSource dataSource) throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		sqlSessionFactoryBean.setMapperLocations(this.properties.resolveMapperLocations());
		sqlSessionFactoryBean.setConfiguration(this.properties.getConfiguration());
		sqlSessionFactoryBean.setVfs(SpringBootVFS.class);

		return sqlSessionFactoryBean.getObject();
	}

	@Bean(name = "igniteSessionTemplate")
	public SqlSessionTemplate sqlSessionTemplate(@Autowired @Qualifier("igniteSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
	
	@Bean(name="igniteTransactionManager")
    public DataSourceTransactionManager secondaryTransactionManager(@Autowired @Qualifier("igniteDataSource") DataSource secondaryDataSource) {
        return new DataSourceTransactionManager(secondaryDataSource);
    }
}
