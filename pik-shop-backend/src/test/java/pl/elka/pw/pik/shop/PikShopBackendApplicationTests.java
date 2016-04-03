package pl.elka.pw.pik.shop;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = PikShopBackendApplication.class)
@WebAppConfiguration
public class PikShopBackendApplicationTests {

    @Test
    public void contextLoads() {
        assertTrue(true);
    }
}
