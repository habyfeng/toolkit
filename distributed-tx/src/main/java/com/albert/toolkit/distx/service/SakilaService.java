package com.albert.toolkit.distx.service;

import com.albert.toolkit.distx.entity.Country;
import com.albert.toolkit.distx.entity.Film;
import com.albert.toolkit.distx.mapper.world.CountryMapper;
import com.albert.toolkit.distx.mapper.sakila.FilmMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 */
@Service
public class SakilaService {

    @Autowired
    private FilmMapper filmMapper;

    @Autowired
    private CountryMapper countryMapper;


    /**
     * 向sakila库插入Film数据，向world库插入Country数据，以测试跨数据库事务
     * @param film
     * @param country
     * @return
     */
    @Transactional
    public int insertFilmAndCountry(Film film, Country country) {
        int filmRows = filmMapper.insert(film);
        int countryRows = countryMapper.insert(country);

        return filmRows + countryRows;
    }

}
