package com.autoapitest.FunctionService.config.ds;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
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
//@MapperScan 扫描 Mapper 接口并容器管理，包路径精确到 master，为了和下面 cluster 数据源做到精确区分
@MapperScan(basePackages = ItemDataSourceConfig.PACKAGE, sqlSessionFactoryRef = "itemSqlSessionFactory")
public class ItemDataSourceConfig {
    // 精确到 item 目录，以便跟其他数据源隔离
    static final String PACKAGE = "com.autoapitest.FunctionService.mapper.item";
    static final String MAPPER_LOCATION = "classpath:mapper/item/*.xml";


    //@Value 获取全局配置文件 application.properties 的 kv 配置,并自动装配
    @Value("${item.datasource.url}")
    private String url;

    @Value("${item.datasource.username}")
    private String user;

    @Value("${item.datasource.password}")
    private String password;

    @Value("${item.datasource.driverClassName}")
    private String driverClass;


    //@Primary 标志这个 Bean 如果在多个同类 Bean 候选时，该 Bean 优先被考虑。「多数据源配置的时候注意，必须要有一个主数据源，用 @Primary 标志该 Bean」
    @Bean(name = "itemDataSource")
    @Primary
    public DataSource itemDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean(name = "itemTransactionManager")
    @Primary
    public DataSourceTransactionManager itemTransactionManager() {
        return new DataSourceTransactionManager(itemDataSource());
    }

    //sqlSessionFactoryRef 表示定义了 key ，表示一个唯一 SqlSessionFactory 实例
    @Bean(name = "itemSqlSessionFactory")
    @Primary
    public SqlSessionFactory itemSqlSessionFactory(@Qualifier("itemDataSource") DataSource itemDataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(itemDataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(ItemDataSourceConfig.MAPPER_LOCATION));
        return sessionFactory.getObject();
    }
}
