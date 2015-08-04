package pl.java.scalatech.tasklet;

import java.util.Map;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

@Slf4j
public class Tasklet2 implements Tasklet {
    @Setter
    private String message;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        log.info("+++ tasklet 2222222222222222222222         step executed ... {}", message);
        JobParameters jobParameters = chunkContext.getStepContext().getStepExecution().getJobParameters();

        if (jobParameters.isEmpty()) {
            log.info("++++ No job parameters!");
        } else {
            log.info("+++  Job parameters:");
            for (Map.Entry<String, JobParameter> param : jobParameters.getParameters().entrySet()) {
                log.info("++++  name:  {} , value :  {} , type : {}", param.getKey(), param.getValue().getValue(), param.getValue().getType());
            }
        }
        return RepeatStatus.FINISHED;
    }
}
