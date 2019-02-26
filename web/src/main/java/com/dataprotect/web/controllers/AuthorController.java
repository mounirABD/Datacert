package com.dataprotect.web.controllers;

import com.dataprotect.models.Author;
import com.dataprotect.repository.AuthorRepository;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path = "/api")
public class AuthorController {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private Job job;

    @Autowired
    private JobLauncher jobLauncher;

    @GetMapping("/authors")
    public Iterable<Author> retrieveAllUsers() {
        return authorRepository.findAll();
    }

    @GetMapping("/start")
    public BatchStatus startBatch() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {

        Map<String, JobParameter> map = new HashMap<>();
        map.put("time", new JobParameter(System.currentTimeMillis()));
        JobParameters parameters = new JobParameters(map);
        JobExecution jobExecution = jobLauncher.run(job, parameters);
        System.out.println("Batch is running...");
        while (jobExecution.isRunning()){
            System.out.println("...");
        }

        return jobExecution.getStatus();

    }

}
