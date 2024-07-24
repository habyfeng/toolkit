package com.albert.toolkit.db;

import com.albert.toolkit.db.entity.City;
import com.albert.toolkit.db.entity.Country;
import com.albert.toolkit.db.service.WorldService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * junit5使用@ExtendWith代替@RunWith
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CountryServiceTest {
    @Autowired
    private WorldService worldService;

    @Resource(name = "dataSourceWorld")
    private DataSource dataSourceWorld;

    @Value("${spring.datasource.world.connection-test-query}")
    private String testQuery;

    @Test
    public void testQueryCountry() {
        List<Country> countries = worldService.queryCountry();
        Assertions.assertNotNull(countries);
    }

    @Test
    public void testConnectionQuery() throws SQLException {
        Connection connection = dataSourceWorld.getConnection();

        try (var statement = connection.createStatement()) {
            statement.execute(testQuery);
        }
        System.out.println(connection.getMetaData().getURL());
        System.out.println(dataSourceWorld.getClass());
    }

    /**
     * 指定Rollback为false，执行完后数据库数据不会回滚
     */
    @Rollback(value = false)
    @Test
    public void testInsertCityAndCountry() {
        City city = new City();
        city.setName("MingZhou");
        city.setCountryCode("ABW");

        Country country = new Country();
        country.setCode("NNNN");
        country.setName("Nexas");

        int rows = worldService.insertCityAndCountry(city, country);
        Assertions.assertEquals(2, rows);
    }
}
