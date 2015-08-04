package pl.java.scalatech.listener;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.StepExecution;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TraceJobExecutionListener implements JobExecutionListener {


  @Override
  public void beforeJob(JobExecution jobExecution) {
  }

  @Override
  public void afterJob(JobExecution jobExecution) {
    log.info("Job '" + jobExecution.getJobInstance().getJobName() + "'");
    log.info("  Started     : {}" , jobExecution.getStartTime());
    log.info("  Finished    : {}" , jobExecution.getEndTime());
    log.info("  Exit-Code   : {}" , jobExecution.getExitStatus().getExitCode());
    log.info("  Status      : {}" , jobExecution.getStatus());

    for (StepExecution stepExecution : jobExecution.getStepExecutions()) {
      log.info("Step  {}" , stepExecution.getStepName());
      log.info("  Status:  {}" , stepExecution.getStatus());
      log.info("  Exit-Code {}: " , stepExecution.getExitStatus().getExitCode());
      log.info("  WriteCount: {} " , stepExecution.getWriteCount());
      log.info("  Commits: {}" , stepExecution.getCommitCount());
      log.info("  SkipCount: {} " , stepExecution.getSkipCount());
      log.info("  Rollbacks: {}" , stepExecution.getRollbackCount());
      log.info("  Filter: {}" , stepExecution.getFilterCount());
     
    }
  }
}