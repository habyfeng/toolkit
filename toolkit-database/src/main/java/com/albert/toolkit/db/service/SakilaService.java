package com.albert.toolkit.db.service;

import com.albert.toolkit.db.CommonConstants;
import com.albert.toolkit.db.annotation.DbSource;
import com.albert.toolkit.db.entity.Actor;
import com.albert.toolkit.db.entity.Film;
import com.albert.toolkit.db.mapper.ActorMapper;
import com.albert.toolkit.db.mapper.FilmMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 动态数据库使用样例
 */
@Service
public class SakilaService {

    @Autowired
    private ActorMapper actorMapper;

    @Autowired
    private FilmMapper filmMapper;

    /**
     * 不显式指定数据库，使用默认的数据库
     */
    @DbSource
    public Actor queryActor(int actorId) {
        LambdaQueryWrapper<Actor> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Actor::getActorId, actorId);

        return actorMapper.selectOne(queryWrapper);
    }

    /**
     * 显式指定数据库
     */
    @DbSource(CommonConstants.DATASOURCE_SAKILA)
    public int insertActor(Actor actor) {
        return actorMapper.insert(actor);
    }

    @Transactional
    public int insertFilmAndActor(Film film, Actor actor) {
        int filmRows = filmMapper.insert(film);
        int actorRows = actorMapper.insert(actor);

        return filmRows + actorRows;
    }

}
