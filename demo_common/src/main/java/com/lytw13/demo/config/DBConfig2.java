package com.lytw13.demo.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.quartz.QuartzDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.lytw13.demo.mapper.quarz", sqlSessionFactoryRef = "sqlSessionFactory2")
public class DBConfig2 {
    @Value("${spring.datasource.goods.driver-class-name}")
    private String driverClassName;
    @Value("${spring.datasource.goods.jdbc-url}")
    private String url;
    @Value("${spring.datasource.goods.username}")
    private String userName;
    @Value("${spring.datasource.goods.password}")
    private String password;

    @Bean
    @QuartzDataSource
    public DataSource dataSource2() {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUsername(userName);
        druidDataSource.setPassword(password);
        druidDataSource.setUrl(url);
        druidDataSource.setDriverClassName(driverClassName);
        return druidDataSource;
    }
    @Bean
    public SqlSessionFactory sqlSessionFactory2(@Qualifier("dataSource2")DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/quarz/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }
}