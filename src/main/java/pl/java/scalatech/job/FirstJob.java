package pl.java.scalatech.job;

import lombok.extern.slf4j.Slf4j;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import pl.java.scalatech.tasklet.UnzipTasklet;

@Configuration
@Slf4j
@ComponentScan(basePackages = { "pl.java.scalatech.tasklet" })
@Profile("first")
public class FirstJob {
    /*
     * @Autowired
     * private Tasklet system;
     */

    /*
     * @Autowired
     * private UnzipTasklet unzipTasklet;
     */

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job job(Step step) {
        return jobBuilderFactory.get("firstTaskletJob").start(step).build();
    }

    @Bean
    public Step step(Tasklet tasklet) {
        return stepBuilderFactory.get("step1").tasklet(tasklet).build();
    }

    /*
     * @Bean
     * @StepScope
     * public FirstTasklet tasklet(@Value("#{jobParameters['message']}") String message) {
     * FirstTasklet tasklet = new FirstTasklet();
     * tasklet.setMessage(message);
     * return tasklet;
     * }
     */

    @Bean
    @StepScope
    public UnzipTasklet unzipTasklet(@Value("#{jobParameters[file]}") String file, @Value("#{jobParameters[dir]}") String destination) {
        log.info("++++  {}   {}", file, destination);
        return new UnzipTasklet(file, destination);
    }
    /*
     * <bean id="unzipTasklet" class="io.spring.messagejoblaunch.tasklet.UnzipTasklet" scope="step">
     * <constructor-arg index="0" value="#{jobParameters['input.file.name']}"/>
     * <constructor-arg index="1" value="${unprocessed.images.dir}"/>
     * </bean>
     */

}
