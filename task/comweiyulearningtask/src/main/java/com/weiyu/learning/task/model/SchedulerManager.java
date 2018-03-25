package com.weiyu.learning.task.model;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author: weiyu
 * @date: 2018/3/14
 */
@Component
public class SchedulerManager {
    private static final Logger logger = LoggerFactory.getLogger(SchedulerManager.class);


    /*
    @Autowired
    TaskManager taskManager;
    */

    @Autowired
    @Qualifier("schedulerFactoryBean")
    private Scheduler scheduler;




    public void removeJob(JobConfigInfo job) {
        logger.warn("Remove job {}", job.getJobId());
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(job.getJobName(), job.getJobGroup());
            scheduler.pauseTrigger(triggerKey);
            scheduler.unscheduleJob(triggerKey);
            scheduler.deleteJob(job.getJobKey());
        } catch (Exception e) {
            logger.error(ExceptionUtils.getStackTrace(e));
        }
    }

    public void addJob(JobConfigInfo job) throws SchedulerException {
        if (TaskManager.isJobAlreadyScheduled(job)) {
            logger.info("Job {} is Already Scheduled",job.toString() );
            return;
        }

        TriggerKey triggerKey = TriggerKey.triggerKey(job.getJobName(), job.getJobGroup());

        // get trigger. defined in spring.
        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

        // if trigger does not exist. create one
        if (null == trigger) {
            JobDetail jobDetail = JobBuilder.newJob(SchedulerJob.class).withIdentity(job.getJobName(), job.getJobGroup()).build();
            jobDetail.getJobDataMap().put(SchedulerJob.SCHEDULE_JOB, job);
            job.setJobKey(jobDetail.getKey());

            // build a scheduler
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());

            // build a trigger give the cronexpression
            trigger = TriggerBuilder.newTrigger().withIdentity(job.getJobName(), job.getJobGroup()).withSchedule(scheduleBuilder).build();
            scheduler.scheduleJob(jobDetail, trigger);
        } else {
            // if trigger is defined
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());

            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();

            // use the defined trigger to schedule the job
            scheduler.rescheduleJob(triggerKey, trigger);
        }
    }





    public void restartJob(JobConfigInfo job) throws SchedulerException {
        removeJob(job);
        addJob(job);
    }
}
