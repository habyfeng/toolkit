package com.albert.toolkit.distx.service;

import com.albert.toolkit.distx.entity.City;
import com.albert.toolkit.distx.entity.Country;
import com.albert.toolkit.distx.mapper.world.CityMapper;
import com.albert.toolkit.distx.mapper.world.CountryMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class WorldService {
    @Autowired
    private CountryMapper countryMapper;

    @Autowired
    private CityMapper cityMapper;

    public List<Country> queryCountry() {
        LambdaQueryWrapper<Country> queryWrapper = new LambdaQueryWrapper<>();

        return countryMapper.selectList(queryWrapper);
    }

    @Transactional
    public int insertCityAndCountry(City city, Country country) {
        int cityRows = cityMapper.insert(city);
        int countryRows = countryMapper.insert(country);

        return cityRows + countryRows;
    }
}
