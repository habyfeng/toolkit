package com.albert.toolkit.distx.config;

import com.albert.toolkit.distx.CommonConstants;
import com.atomikos.jdbc.AtomikosDataSourceBean;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.mysql.cj.jdbc.MysqlXADataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * world数据库mybatis-plus和spring配置
 *
 * @author hoyo
 * @since 2024-07-20
 */
@Configuration
@MapperScan(basePackages = "com.albert.toolkit.distx.mapper.world", sqlSessionTemplateRef = "sqlSessionTemplateWorld")
public class DbWorldMybatisConfig {

    private static final String MAPPER_PATH = "classpath*:com/albert/toolkit/db/mapper/world/*.xml";

    /**
     * world数据库的配置
     *
     * @return
     */
    @Bean(name = "dataSourcePropertiesWorld")
    @ConfigurationProperties(prefix = "spring.datasource.world")
    public DataSourceProperties worldProperties() {
        return new DataSourceProperties();
    }

    /**
     * 数据源-world
     */
    @Bean(name = "dataSourceWorld")
    public DataSource dataSourceWorld() {
        DataSourceProperties dbProp = worldProperties();
        MysqlXADataSource mysqlXADataSource = new MysqlXADataSource();
        mysqlXADataSource.setURL(dbProp.getUrl());
        mysqlXADataSource.setUser(dbProp.getUsername());
        mysqlXADataSource.setPassword(dbProp.getPassword());

        AtomikosDataSourceBean atomikosDbBean = new AtomikosDataSourceBean();
        atomikosDbBean.setXaDataSource(mysqlXADataSource);
        atomikosDbBean.setUniqueResourceName(CommonConstants.DATASOURCE_WORLD);
        return atomikosDbBean;
    }

    @Bean(name = "sqlSessionFactoryWorld")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSourceWorld") DataSource dataSource) throws Exception {
        // 使用mybatis-plus 必须用MybatisSqlSessionFactoryBean，不能用mybatis的SqlSessionFactoryBean
        MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(MAPPER_PATH));
        return bean.getObject();
    }

    /**
     * 必须要装配SqlSessionTemplate Bean
     * @param sqlSessionFactory
     * @return
     */
    @Bean(name = "sqlSessionTemplateWorld")
    public SqlSessionTemplate orderSqlSessionTemplate(
            @Qualifier("sqlSessionFactoryWorld") SqlSessionFactory sqlSessionFactory) {
        SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory);
        return sqlSessionTemplate;
    }

}
