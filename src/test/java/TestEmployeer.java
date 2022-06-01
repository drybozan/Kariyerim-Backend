import com.example.config.AppConfig;
import com.example.config.ApplicationInitializer;
import com.example.config.WebConfig;
import com.example.dataAcces.CityDao;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationInitializer.class, AppConfig.class, WebConfig.class})
@Transactional
@WebAppConfiguration
public class TestEmployeer {

    @Autowired
    CityDao cityDao;

    @Test
    public void existsCandidate() {
        Assert.assertEquals(cityDao.getAll().size(),14);
    }

}