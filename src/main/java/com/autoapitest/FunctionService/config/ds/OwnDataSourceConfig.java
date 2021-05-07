package com.autoapitest.FunctionService.config.ds;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
/**
 * 装填数据库参数
 * @author wing
 *
 */
@Configuration
// 扫描 Mapper 接口并容器管理
@MapperScan(basePackages = OwnDataSourceConfig.PACKAGE, sqlSessionFactoryRef = "ownSqlSessionFactory")
public class OwnDataSourceConfig {
    // 精确到 cluster 目录，以便跟其他数据源隔离
    static final String PACKAGE = "com.autoapitest.FunctionService.mapper.own";
    static final String MAPPER_LOCATION = "classpath:mapper/own/*.xml";

    @Value("${own.datasource.url}")
    private String url;

    @Value("${own.datasource.username}")
    private String user;

    @Value("${own.datasource.password}")
    private String password;

    @Value("${own.datasource.driverClassName}")
    private String driverClass;

    @Bean(name = "ownDataSource")
    public DataSource ownDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean(name = "ownTransactionManager")
    public DataSourceTransactionManager itemTransactionManager() {
        return new DataSourceTransactionManager(ownDataSource());
    }

    @Bean(name = "ownSqlSessionFactory")
    public SqlSessionFactory itemSqlSessionFactory(@Qualifier("ownDataSource") DataSource ownDataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(ownDataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(OwnDataSourceConfig.MAPPER_LOCATION));
        return sessionFactory.getObject();
    }
}
