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
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Slf4j
@Component("first")
@Profile("first")
public class FirstTasklet implements Tasklet {
    @Setter
    private String message;
    
    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        log.info("+++ tasklet step executed ... {}",message);
        JobParameters jobParameters = chunkContext.getStepContext().getStepExecution().getJobParameters();
        
        if (jobParameters.isEmpty()) {
            log.info("++++ No job parameters!");
        } else {
            log.info("+++  Job parameters:");
            for (Map.Entry<String, JobParameter> param : jobParameters.getParameters().entrySet()) {
                log.info("++++  name:  {} , value :  {} , type : {}",param.getKey(), param.getValue().getValue(), param.getValue().getType());
            }
        }
        return RepeatStatus.FINISHED;
    }

}
