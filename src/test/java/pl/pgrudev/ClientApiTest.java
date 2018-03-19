package pl.pgrudev;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import pl.pgrudev.nextbike.Connector;
import pl.pgrudev.nextbike.model.Universe;
import pl.pgrudev.spring.TestConfig;

import java.net.MalformedURLException;

@Configuration
@ComponentScan(basePackages = {"pl.pgrudev.spring"})

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ContextConfiguration(classes = {TestConfig.class})
public class ClientApiTest {

    @Autowired
    private ApplicationContext applicationContext;

    private Connector connector;


    @BeforeAll
    void setup() throws MalformedURLException {
        applicationContext = new AnnotationConfigApplicationContext(ClientApiTest.class);

        String path = this.getClass().getClassLoader().getResource("nextbike-official.json").toString();

        this.connector = new Connector(path);
    }


    @Test
    void parseUniverseTest() throws Exception {

        Universe universe = connector.downloadNewData(Connector.Type.UNIVERSE, "");

        Assert.assertEquals(universe.getCountries().size(), 82);
        Assert.assertEquals(universe.getCountries().get(14).getCityList().size(), 2);
    }
}
