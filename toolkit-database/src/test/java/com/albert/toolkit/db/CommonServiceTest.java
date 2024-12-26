package com.albert.toolkit.db;

import com.albert.toolkit.db.entity.Actor;
import com.albert.toolkit.db.entity.Film;
import com.albert.toolkit.db.service.CommonService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CommonServiceTest {

    @Autowired
    private CommonService commonService;

    @Rollback(value = false)
    @Test
    public void testTxService() {
        Actor actor = new Actor();
        actor.setFirstName("jam");
        actor.setLastName("ben");

        Film film = new Film();
        film.setTitle("small boom");
        film.setReleaseYear("10000");

        commonService.saveFilmAndActor(film, actor);
    }
}
