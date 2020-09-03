package com.openlabs.sample.config;

import java.io.File;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ignite.internal.util.IgniteUtils;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.openlabs.sample.interceptor.MyBatisPagingInterceptor;
import com.openlabs.sample.interceptor.MyBatisPagingInterceptor.DatabaseType;
import com.zaxxer.hikari.HikariDataSource;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@MapperScan(basePackages = "com.openlabs.sample.mapper.cache", sqlSessionFactoryRef = "igniteSqlSessionFactory")
@EnableConfigurationProperties(IgniteProperties.class)
@Configuration
public class IgniteConfig {
	
	@Autowired
    private IgniteProperties properties;
	
	@Bean(name = "igniteDataSource")
	public DataSource dataSource() {
		String igniteHomePath = System.getProperty("user.dir").concat(File.separator).concat("ignite");
		log.debug("IGNITEHOME:{}", igniteHomePath);
		IgniteUtils.setIgniteHome(igniteHomePath);
		String url = "jdbc:ignite:cfg://distributedJoins=true:transactionsAllowed=true:user=test:password=test@file:///" + igniteHomePath
				+ "/config/default-config.xml";
		
		HikariDataSource dataSource = new HikariDataSource();
		dataSource.setJdbcUrl(url);
		dataSource.setDriverClassName("org.apache.ignite.IgniteJdbcDriver");
		dataSource.setUsername("test");
		dataSource.setPassword("test");
		
		dataSource.setMaximumPoolSize(10);
		dataSource.setMinimumIdle(10);
		dataSource.setConnectionTestQuery("SELECT 1");
		dataSource.setConnectionInitSql("SELECT 1");
		
//		DriverManagerDataSource dataSource = new DriverManagerDataSource(url);
		return dataSource;
	}
	
//	@Bean(name = "igniteDataSource")
//	@ConfigurationProperties(prefix = "spring.ignite.datasource")
//	public DataSource dataSource() {
//		DataSource dataSource = DataSourceBuilder.create().build();
//		return dataSource;
//	}

	@Bean(name = "igniteSqlSessionFactory")
	public SqlSessionFactory sqlSessionFactory(@Autowired @Qualifier("igniteDataSource") DataSource dataSource) throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		sqlSessionFactoryBean.setMapperLocations(this.properties.resolveMapperLocations());
		sqlSessionFactoryBean.setConfiguration(this.properties.getConfiguration());
		sqlSessionFactoryBean.setPlugins(new MyBatisPagingInterceptor(DatabaseType.IGNITE));
		sqlSessionFactoryBean.setVfs(SpringBootVFS.class);

		return sqlSessionFactoryBean.getObject();
	}

	@Bean(name = "igniteSessionTemplate")
	public SqlSessionTemplate sqlSessionTemplate(@Autowired @Qualifier("igniteSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
}
