package com.research.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.servlet.MultipartConfigElement;
import javax.sql.DataSource;

/**
 * Created by sen on 2016/8/17.
 */
@Configuration
public class InitBeans {

    /**     * mybatis 配置路径     */
    private static String MYBATIS_CONFIG="mybatis-config.xml";
    /**     * mybatis mapper resource 路径     */
    private static String MAPPER_PATH="/com/research/mapper/*.xml";

    @Bean
    @ConfigurationProperties(prefix="spring.datasource")
    public DataSource dataSource() {
        return new org.apache.tomcat.jdbc.pool.DataSource();
    }

    @Bean
    public SqlSessionFactory sqlSessionFactoryBean() throws Exception {



        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());

        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

        /**
         * 配置自动扫描mapper.xml 路径
         */
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources(MAPPER_PATH));

        /**
         * 配置mybatis.cfg.xml
         */
        sqlSessionFactoryBean.setConfigLocation(new ClassPathResource(MYBATIS_CONFIG));


        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }



}
