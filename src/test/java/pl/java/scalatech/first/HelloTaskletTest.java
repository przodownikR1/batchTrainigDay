package pl.java.scalatech.first;

import java.util.Date;
import java.util.Map;

import javax.batch.operations.JobRestartException;

import lombok.extern.slf4j.Slf4j;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.java.scalatech.config.BatchConfig;
import pl.java.scalatech.config.JpaConfig;
import pl.java.scalatech.job.FirstJob;

import com.google.common.collect.Maps;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { JpaConfig.class, BatchConfig.class, FirstJob.class })
@ActiveProfiles("first")
public class HelloTaskletTest {

    @Autowired
    private Job job;
    @Autowired
    private JobLauncher jobLauncher; //dzieki  @EnableBatchProcessing

    @Test
    public void shouldTaskletWork() throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException,
            JobParametersInvalidException, org.springframework.batch.core.repository.JobRestartException {

        Map<String, JobParameter> params = Maps.newHashMap(); //za każdym razem musi byc tworzony inny zestaw parametrow
        params.put("time", new JobParameter(new Date()));
        params.put("message", new JobParameter("Hello przodownik"));
        params.put("file", new JobParameter("customers.csv.zip"));
        params.put("dir", new JobParameter("/tmp"));
        JobExecution execution = jobLauncher.run(job, new JobParameters(params));
        log.info("Exit Status :  {}", execution.getExitStatus());
        Assert.assertEquals(ExitStatus.COMPLETED, execution.getExitStatus());

    }

}