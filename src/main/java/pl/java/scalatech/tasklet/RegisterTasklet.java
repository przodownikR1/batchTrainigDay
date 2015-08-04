package pl.java.scalatech.tasklet;

import static org.springframework.batch.repeat.RepeatStatus.FINISHED;
import lombok.extern.slf4j.Slf4j;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
@Slf4j
public class RegisterTasklet implements Tasklet {
   
    public RepeatStatus execute(final StepContribution sc, final ChunkContext context) throws Exception {  
        log.info("+++ Register task ..... execute !!! ");
        
        JobParameters jobParams = context.getStepContext().getStepExecution().getJobExecution().getJobParameters();
        log.info("time : {}",jobParams.getDate("time"));
        log.info("test : {}",jobParams.getString("test"));
        ExecutionContext jobExecutionContext = context.getStepContext().getStepExecution().getJobExecution().getExecutionContext();
        String id = Hashing.md5().hashString(jobParams.getString("test"), Charsets.UTF_8).toString();
        log.info("+++ put Id {} to context ",id);
        jobExecutionContext.put("ID", id);
       
        
        
        return FINISHED;
    }
}
