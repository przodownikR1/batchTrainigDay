package pl.java.scalatech.tasklet;

import static org.springframework.batch.repeat.RepeatStatus.FINISHED;
import lombok.extern.slf4j.Slf4j;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

@Slf4j
public class ReportTasklet implements Tasklet {

    @Override
    public RepeatStatus execute(final StepContribution sc, final ChunkContext context) throws Exception {
        log.info("+++ Report tasklet ..... execute !!! ");
        log.info("+++ StepContribution :  {} ", sc);
        log.info("+++  ChunkContext  :  {}  -> jobName  : {} ", context, context.getStepContext().getJobName());
        log.info("+++  StepContext :  jobParameters :  {} , stepExecution : {} , stepName :  {} ", context.getStepContext().getJobParameters(), context
                .getStepContext().getStepExecution(), context.getStepContext().getStepName());
        JobParameters jobParams = context.getStepContext().getStepExecution().getJobExecution().getJobParameters();
        log.info("time : {}", jobParams.getDate("time"));
        log.info("test : {}", jobParams.getString("test"));
        Thread.sleep(3000);
        return FINISHED;
    }

}
