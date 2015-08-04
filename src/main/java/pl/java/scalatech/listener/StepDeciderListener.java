package pl.java.scalatech.listener;

import lombok.extern.slf4j.Slf4j;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;

@Slf4j
public class StepDeciderListener implements StepExecutionListener {

    @Override
    public void beforeStep(StepExecution stepExecution) {
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {

        JobParameters params = stepExecution.getJobParameters();
        if ("true".equals(params.getString("skipRegister"))) {
            log.info("$$$ selected options -> COMPLETED WITH SKIPS");
            return new ExitStatus("COMPLETED WITH SKIPS");
        }
        log.info("$$$ selected options -> COMPLETED");
        return ExitStatus.COMPLETED;

    }
}
