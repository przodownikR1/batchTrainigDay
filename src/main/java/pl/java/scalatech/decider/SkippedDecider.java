package pl.java.scalatech.decider;


import lombok.extern.slf4j.Slf4j;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;
import org.springframework.batch.core.job.flow.JobExecutionDecider;


@Slf4j
public class SkippedDecider implements JobExecutionDecider {
    
	@Override
	public FlowExecutionStatus decide(JobExecution jobExecution,
			StepExecution stepExecution) {
     JobParameters params = jobExecution.getJobParameters();
     if("true".equals(params.getString("error"))) {
     log.info("DDD Decider -> skipped");
     return   new FlowExecutionStatus("SKIPPED");
     }
     log.info("DDD Decider -> completed");
     return new FlowExecutionStatus(BatchStatus.COMPLETED.name());
	}

}
