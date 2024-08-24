import com.albert.toolkit.distx.Application;
import com.albert.toolkit.distx.entity.Country;
import com.albert.toolkit.distx.entity.Film;
import com.albert.toolkit.distx.service.SakilaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * 测试类所在的包和待测试类所在的包不一致的话SpringBootTest需要指定类
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {Application.class})
public class AppTest {
    @Autowired
    private SakilaService sakilaService;
    /**
     */
    @Test
    public void testApp() {
        Country country = new Country();
        country.setCode("NNO");
        country.setName("Nexas");

        Film film = new Film();
        film.setTitle("big boom");
        sakilaService.insertFilmAndCountry(film, country);
    }
}
