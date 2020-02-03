package com.lytw13.demo.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.lytw13.demo.mapper.goods", sqlSessionFactoryRef = "sqlSessionFactory2")
public class DBConfig2 {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.goods")
    public DataSource dataSource2() {
        return DataSourceBuilder.create().build();
    }
    @Bean
    public SqlSessionFactory sqlSessionFactory2(@Qualifier("dataSource2")DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/goods/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }
}