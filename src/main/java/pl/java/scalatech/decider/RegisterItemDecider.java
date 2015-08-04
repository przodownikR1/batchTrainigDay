package pl.java.scalatech.decider;

import lombok.extern.slf4j.Slf4j;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;
import org.springframework.batch.core.job.flow.JobExecutionDecider;
import org.springframework.batch.item.ExecutionContext;

@Slf4j
public class RegisterItemDecider implements JobExecutionDecider {
    

    @Override
    public FlowExecutionStatus decide(JobExecution jobExecution, StepExecution stepExecution) {
        log.info("+++  jobExecutionContext {} ", jobExecution.getExecutionContext());
        ExecutionContext jobExecutionContext = stepExecution.getJobExecution().getExecutionContext();
        log.info("___ {}",jobExecutionContext);
        JobParameters params = jobExecution.getJobParameters();
        if("true".equals(params.getString("skipRegister"))) {
            log.info("DDD selected options -> COMPLETED WITH SKIPS"); 
            return new FlowExecutionStatus("COMPLETED WITH SKIPS");
        }
        log.info("DDD selected options -> COMPLETED");
        return FlowExecutionStatus.COMPLETED;
    }

}
