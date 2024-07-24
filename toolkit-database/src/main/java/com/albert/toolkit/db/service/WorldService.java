package com.albert.toolkit.db.service;

import com.albert.toolkit.db.CommonConstants;
import com.albert.toolkit.db.annotation.DbSource;
import com.albert.toolkit.db.entity.City;
import com.albert.toolkit.db.entity.Country;
import com.albert.toolkit.db.mapper.CityMapper;
import com.albert.toolkit.db.mapper.CountryMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class WorldService {
    @Autowired
    private CountryMapper countryMapper;

    @Autowired
    private CityMapper cityMapper;

    @DbSource(value = CommonConstants.DATASOURCE_WORLD)
    public List<Country> queryCountry() {
        LambdaQueryWrapper<Country> queryWrapper = new LambdaQueryWrapper<>();

        return countryMapper.selectList(queryWrapper);
    }

    /**
     * 测试动态数据源注解和Transactional结合使用
     * 关键点：
     * 1. transactionManager使用transactionManagerDynamic
     * 2. 动态数据源切面在Transactional注解之前执行，方法是DynamicDatasourceAspect的Order设为1
     * @param city
     * @param country
     * @return
     */
    @DbSource(value = CommonConstants.DATASOURCE_WORLD)
    @Transactional(transactionManager = "transactionManagerDynamic", propagation = Propagation.REQUIRED)
    public int insertCityAndCountry(City city, Country country) {
        int cityRows = cityMapper.insert(city);
        int countryRows = countryMapper.insert(country);

        return cityRows + countryRows;
    }
}
