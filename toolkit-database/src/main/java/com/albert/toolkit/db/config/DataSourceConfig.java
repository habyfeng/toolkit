package com.albert.toolkit.db.config;

import com.albert.toolkit.db.CommonConstants;
import com.albert.toolkit.db.dynamicds.DynamicDatasource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 数据源bean配置
 */
@Configuration
public class DataSourceConfig {
    /**
     * sakila数据库的配置
     *
     * @return
     */
    @Bean(name = "dataSourcePropertiesSakila")
    @ConfigurationProperties(prefix = "spring.datasource.sakila")
    @Primary
    public DataSourceProperties sakilaProperties() {
        return new DataSourceProperties();
    }

    /**
     * world数据库的配置
     * @return
     */
    @Bean(name = "dataSourcePropertiesWorld")
    @ConfigurationProperties(prefix = "spring.datasource.world")
    @Primary
    public DataSourceProperties worldProperties() {
        return new DataSourceProperties();
    }

    /**
     * 数据源-sakila
     */
    @Bean(name = "dataSourceSakila")
    @Primary
    public DataSource dataSourceSakila() {
        // 默认使用的DataSource实现是HikariDataSource
        return sakilaProperties().initializeDataSourceBuilder().build();
    }

    /**
     * 数据源-world
     */
    @Bean(name = "dataSourceWorld")
    public DataSource dataSourceWorld() {
        // 默认使用的DataSource实现是HikariDataSource
        return worldProperties().initializeDataSourceBuilder().build();
    }

    /**
     * 动态数据源
     * @param sakila
     * @param world
     * @return
     */
    @Bean(name = "dynamicDatasource")
    public DynamicDatasource dynamicDatasource(@Qualifier(value = "dataSourceSakila") DataSource sakila,
                                               @Qualifier(value = "dataSourceWorld") DataSource world) {
        DynamicDatasource dynamicDatasource = new DynamicDatasource();

        // 为targetDataSources初始化所有数据源
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(CommonConstants.DATASOURCE_SAKILA, sakila);
        targetDataSources.put(CommonConstants.DATASOURCE_WORLD, world);
        dynamicDatasource.setTargetDataSources(targetDataSources);

        // 为defaultTargetDataSource 设置默认的数据源
        dynamicDatasource.setDefaultTargetDataSource(sakila);

        return dynamicDatasource;
    }
}
