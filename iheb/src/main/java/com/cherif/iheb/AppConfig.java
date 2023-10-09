package com.cherif.iheb;

import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;

@Configuration
public class AppConfig {


    @Autowired
    private Step readWriteProductsStep;

    @Autowired
    private Job importProductsJob;

    @Autowired
    private JobLauncher jobLauncher;

    @Scheduled(cron = "* * * * * *")
    public  void run() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        System.out.println("Job Started at :" + new Date());
        JobParameters jobParameters = new JobParametersBuilder().toJobParameters();
        JobExecution jobExecution = jobLauncher.run(importProductsJob, jobParameters);
        System.out.println("Job Status :" + jobExecution.getStatus());
    }
}
