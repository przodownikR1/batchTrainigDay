package pl.java.scalatech.controller;

import java.util.Map;
import java.util.UUID;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
// jee7
// import javax.batch.runtime.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Maps;

@RestController
public class JobLauncherRestController {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job webJob;

    @RequestMapping("/ping")
    public String ping() {
        return "pong";
    }

    @RequestMapping(value = "/restJob", method = RequestMethod.GET)
    public ResponseEntity<String> run() throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException,
            JobParametersInvalidException {
        Map<String, JobParameter> params = Maps.newHashMap();
        params.put("id_job", new JobParameter(UUID.randomUUID().toString()));
        JobExecution je = jobLauncher.run(webJob, new JobParameters(params));
        return ResponseEntity.ok(je.getExitStatus().getExitCode());
    }

}
