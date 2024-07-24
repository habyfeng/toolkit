package com.albert.toolkit.db.config;

import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * sakila数据库mybatis-plus和spring配置
 */
@Configuration
public class DbSakilaMybatisConfig {

    private static final String MAPPER_PATH = "classpath:/com/albert/toolkit/db/mapper/sakila/*.xml";

    @Bean(name = "sqlSessionFactorySakila")
    @Primary
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dynamicDatasource") DataSource dataSource) throws Exception {
        // 使用mybatis-plus 必须用MybatisSqlSessionFactoryBean，不能用mybatis的SqlSessionFactoryBean
        MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean();
        // 关键点：使用dynamicDatasource
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(MAPPER_PATH));
        return bean.getObject();
    }
}
