package com.weiyu.learning.task.model;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * @author: weiyu
 * @date: 2018/3/14
 */
public class SchedulerJob implements Job {
    public static final String SCHEDULE_JOB = "scheduleJob";

    private static final Logger logger = LoggerFactory.getLogger(SchedulerJob.class);

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        try {
            //先从Spring中获取，如果无法获取，则反射实例化

            JobConfigInfo etlScheduleJob = (JobConfigInfo) context.getMergedJobDataMap().get(SCHEDULE_JOB);
            logger.info("Task " + etlScheduleJob.getJobName() + " begins at {}", new Date());



            logger.info("Task name = [{}]", etlScheduleJob.getJobName(), new Date());
        } catch (Exception e) {
            logger.warn(e.getMessage());
        }
    }
}
