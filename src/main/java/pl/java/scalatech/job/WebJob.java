package pl.java.scalatech.job;

import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class WebJob {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job job() {
        return jobBuilderFactory.get("firstTaskletJob").start(step()).build();
    }

    @Bean
    public Step step() {
        return stepBuilderFactory.get("step1").tasklet(helloTasklet()).build();
    }

    /*
     * <bean id="jobLauncher"
     * class="org.springframework.batch.core.launch.support.SimpleJobLauncher">
     * <property name="jobRepository" ref="jobRepository" />
     * <property name="taskExecutor">
     * <bean class="org.springframework.core.task.SimpleAsyncTaskExecutor" />
     * </property>
     * </bean>
     */
    /*
     * @Bean
     * JobLauncher jobLauncher(JobRepository jobRepository) {
     * SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
     * jobLauncher.setJobRepository(jobRepository);
     * jobLauncher.setTaskExecutor(new TaskExecutorAdapter(Executors.newCachedThreadPool()));
     * return jobLauncher;
     * }
     */

    @Bean
    public Tasklet helloTasklet() {
        return new Tasklet() {

            @Override
            public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                Thread.sleep(TimeUnit.SECONDS.toSeconds(10));
                log.info("+++ execute tasklet...");
                return RepeatStatus.FINISHED;
            }
        };
    }

}
