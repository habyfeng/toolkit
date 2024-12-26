package com.albert.toolkit.db.service;

import com.albert.toolkit.db.entity.Actor;
import com.albert.toolkit.db.entity.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommonService {
    @Autowired
    private SakilaService sakilaService;

    @Transactional
    public void saveFilmAndActor(Film film, Actor actor) {
        sakilaService.saveActor(actor);
        sakilaService.saveFilm(film);
    }


}
