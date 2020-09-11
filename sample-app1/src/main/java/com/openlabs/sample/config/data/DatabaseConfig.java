package com.openlabs.sample.config.data;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.openlabs.sample.interceptor.MyBatisPagingInterceptor;
import com.openlabs.sample.interceptor.MyBatisPagingInterceptor.DatabaseType;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@MapperScan(basePackages = "com.openlabs.sample.mapper.db", sqlSessionFactoryRef = "primarySqlSessionFactory")
@EnableConfigurationProperties(MybatisProperties.class)
@Configuration
public class DatabaseConfig {

	@Autowired
    private MybatisProperties properties;
	
	@Primary
	@Bean(name = "primaryDataSource")
	@ConfigurationProperties(prefix = "spring.primary.datasource")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}

	@Primary
	@Bean(name = "primarySqlSessionFactory")
	public SqlSessionFactory sqlSessionFactory(@Autowired @Qualifier("primaryDataSource") DataSource dataSource) throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		sqlSessionFactoryBean.setMapperLocations(this.properties.resolveMapperLocations());
		sqlSessionFactoryBean.setConfiguration(this.properties.getConfiguration());
		sqlSessionFactoryBean.setPlugins(new MyBatisPagingInterceptor(DatabaseType.ORACLE));
		sqlSessionFactoryBean.setVfs(SpringBootVFS.class);
		SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();
		return sqlSessionFactory;
	}

	@Primary
	@Bean(name = "primarySessionTemplate")
	public SqlSessionTemplate sqlSessionTemplate(@Autowired @Qualifier("primarySqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
}
