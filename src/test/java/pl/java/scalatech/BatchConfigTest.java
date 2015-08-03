package pl.java.scalatech;

import java.sql.SQLException;

import lombok.extern.slf4j.Slf4j;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.java.scalatech.config.BatchConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = BatchConfig.class)
@Slf4j
public class BatchConfigTest {

    @Autowired
    private JobLauncher jobLauncher;
    @Autowired
    private JobRepository jobRepository;

    // @Autowired
    //  private DataSource dataSource;

    @Test
    public void shouldBootstrapTest() throws SQLException {
        Assertions.assertThat(jobLauncher).isNotNull();
        Assertions.assertThat(jobRepository).isNotNull();
        // Assertions.assertThat(dataSource).isNotNull();
        // log.info("dataSource config : {}", dataSource.getConnection().getClientInfo());
    }
}
