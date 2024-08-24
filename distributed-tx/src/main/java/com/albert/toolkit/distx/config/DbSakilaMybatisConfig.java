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
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * sakila数据库mybatis-plus和spring配置
 */
@Configuration
@MapperScan(basePackages = "com.albert.toolkit.distx.mapper.sakila", sqlSessionTemplateRef = "sqlSessionTemplateSakila")
public class DbSakilaMybatisConfig {

    private static final String MAPPER_PATH = "classpath*:com/albert/toolkit/db/mapper/sakila/*.xml";

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
     * 数据源-sakila
     */
    @Bean(name = "dataSourceSakila")
    @Primary
    public DataSource dataSourceSakila() {
        DataSourceProperties dbProp = sakilaProperties();
        MysqlXADataSource mysqlXADataSource = new MysqlXADataSource();
        mysqlXADataSource.setURL(dbProp.getUrl());
        mysqlXADataSource.setUser(dbProp.getUsername());
        mysqlXADataSource.setPassword(dbProp.getPassword());

        AtomikosDataSourceBean atomikosDbBean = new AtomikosDataSourceBean();
        atomikosDbBean.setXaDataSource(mysqlXADataSource);
        atomikosDbBean.setUniqueResourceName(CommonConstants.DATASOURCE_SAKILA);
        return atomikosDbBean;
    }

    @Bean(name = "sqlSessionFactorySakila")
    @Primary
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSourceSakila") DataSource dataSource) throws Exception {
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
    @Primary
    @Bean(name = "sqlSessionTemplateSakila")
    public SqlSessionTemplate orderSqlSessionTemplate(
            @Qualifier("sqlSessionFactorySakila") SqlSessionFactory sqlSessionFactory) {
        SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory);
        return sqlSessionTemplate;
    }
}
