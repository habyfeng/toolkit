package com.albert.toolkit.db.config;

import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * world数据库mybatis-plus和spring配置
 *
 * @author hoyo
 * @since 2024-07-20
 */
@Configuration
public class DbWorldMybatisConfig {

    private static final String MAPPER_PATH = "classpath:/com/albert/toolkit/db/mapper/world/*.xml";

    @Bean(name = "sqlSessionFactoryWorld")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dynamicDatasource") DataSource dataSource) throws Exception {
        // 使用mybatis-plus 必须用MybatisSqlSessionFactoryBean，不能用mybatis的SqlSessionFactoryBean
        MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean();
        // 关键点：使用dynamicDatasource
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(MAPPER_PATH));
        return bean.getObject();
    }

    @Bean(name = "transactionManagerDynamic")
    public DataSourceTransactionManager dataSourceTransactionManager(@Qualifier("dynamicDatasource") DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }

}
