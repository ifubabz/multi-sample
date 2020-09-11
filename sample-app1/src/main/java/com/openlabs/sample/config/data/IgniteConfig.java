package com.openlabs.sample.config.data;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ignite.Ignite;
import org.apache.ignite.Ignition;
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

import com.openlabs.sample.interceptor.MyBatisPagingInterceptor;
import com.openlabs.sample.interceptor.MyBatisPagingInterceptor.DatabaseType;

@MapperScan(basePackages = "com.openlabs.sample.mapper.cache", sqlSessionFactoryRef = "igniteSqlSessionFactory")
@EnableConfigurationProperties(IgniteProperties.class)
@Configuration
public class IgniteConfig {
	
	@Autowired
    private IgniteProperties properties;
	
	@Bean(name = "igniteDataSource")
	@ConfigurationProperties(prefix = "spring.ignite.datasource")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}

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
	
	@Bean
	public Ignite ignite() {
		Ignite ignite = Ignition.start(this.properties.getConfigFile());
		return ignite;
	}
}
