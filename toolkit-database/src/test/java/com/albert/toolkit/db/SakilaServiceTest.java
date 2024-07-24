package com.albert.toolkit.db;

import com.albert.toolkit.db.entity.Actor;
import com.albert.toolkit.db.entity.Film;
import com.albert.toolkit.db.service.SakilaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * junit5使用@ExtendWith代替@RunWith
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class SakilaServiceTest {
    @Autowired
    private SakilaService sakilaService;


    @Test
    public void testQueryActor() {
        int actorId = 11;
        Actor actor = sakilaService.queryActor(actorId);
        Assertions.assertEquals(actorId, actor.getActorId());
    }

    @Test
    public void testInsertFilmAndActor() {
        Actor actor = new Actor();
        actor.setFirstName("fangyi");
        actor.setLastName("he");

        Film film = new Film();
        film.setTitle("big boom");

        int rows = sakilaService.insertFilmAndActor(film, actor);
        Assertions.assertEquals(2, rows);
    }

}
