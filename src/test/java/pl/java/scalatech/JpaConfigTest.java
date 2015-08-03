package pl.java.scalatech;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.java.scalatech.config.BatchConfig;
import pl.java.scalatech.config.JpaConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { BatchConfig.class, JpaConfig.class })
public class JpaConfigTest {
    @Test
    public void shouldBootstrap() {

    }

}
