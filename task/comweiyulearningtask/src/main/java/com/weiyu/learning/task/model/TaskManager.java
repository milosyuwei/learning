package com.weiyu.learning.task.model;

import com.google.common.base.Splitter;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.weiyu.learning.task.dao.taskmanagerdb.TaskConfig;
import com.weiyu.learning.task.dao.taskmanagerdb.mapper.TaskListMapper;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.SetUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author: weiyu
 * @date: 2018/3/8
 */
@Component
public class TaskManager implements InitializingBean {
    private static final Logger logger = LoggerFactory.getLogger(TaskManager.class);
    private static final long HEART_BEAT_PERIOD = 60l * 1000L;


    @Autowired
    TaskListMapper taskListMapper;

    @Autowired
    SchedulerManager schedulerManager;

    private static Map<String, JobConfigInfo> taskMap = Maps.newConcurrentMap();

    private Timer timer;

    @Override
    public void afterPropertiesSet() throws Exception {
        timer = new Timer();

        //周期性 检测
        timer.schedule(new EtlSchedulerHeartBeatUpdater(), 0, HEART_BEAT_PERIOD);
    }


    private class EtlSchedulerHeartBeatUpdater extends TimerTask {
        @Override
        public void run() {
            //获取db
            try {
                //List<Map<String,Object>> dt = jdbcTemplate.queryForList("SELECT * FROM taskmonitordb.taskinfo");
                List<TaskConfig> taskLists = taskListMapper.selectActiveTaskList();
                if(CollectionUtils.isNotEmpty(taskLists)){
                    Set<String> oldTaskNames = taskMap.keySet();

                    Map<String,TaskConfig> newTaskMap = Maps.newConcurrentMap();
                    for(TaskConfig taskInfo:taskLists){
                        taskInfo.setTaskName(taskInfo.getTaskName().trim().toLowerCase());
                        newTaskMap.put(taskInfo.getTaskName(),taskInfo);
                    }
                    Set<String> newTaskNames = newTaskMap.keySet();

                    Set<String> needRemoveTaskNames = Sets.difference(oldTaskNames,newTaskNames);
                    for(String taskName:needRemoveTaskNames){
                        schedulerManager.removeJob(taskMap.get(taskName));
                        taskMap.remove(taskName);
                    }

                    for(String taskName:newTaskMap.keySet()){
                        TaskConfig dbTaskConfigInfo = newTaskMap.get(taskName);

                        if(taskMap.containsKey(taskName)
                                && newTaskMap.get(taskName).getDataChangeLastTime().getTime()
                                   > taskMap.get(taskName).getDataChangeLastTime().getTime()
                                ){

                            //修改
                            JobConfigInfo jobConfigInfo = taskMap.get(taskName);
                            //可以修改的内容
                            jobConfigInfo.setCronExpression(dbTaskConfigInfo.getJobCron());
                            jobConfigInfo.setJobClass(dbTaskConfigInfo.getClassPath());
                            jobConfigInfo.setJobMethod(dbTaskConfigInfo.getProcessMothod());
                            jobConfigInfo.setDataChangeLastTime(dbTaskConfigInfo.getDataChangeLastTime());

                            Map pMap = Maps.newHashMap();
                            List pList = Splitter.on(",").omitEmptyStrings().splitToList(StringUtils.trimToEmpty(dbTaskConfigInfo.getMothodParameter()));
                            for(int i=0;i<pList.size();i++){
                                pMap.put(String.format("p%s",i),pList.get(i));
                            }
                            jobConfigInfo.setJobParams(pMap);

                            schedulerManager.restartJob(taskMap.get(taskName));
                        }else {
                            //新增
                            JobConfigInfo jobConfigInfo = new JobConfigInfo();
                            jobConfigInfo.setJobName("job_" + dbTaskConfigInfo.getTaskName());
                            jobConfigInfo.setJobGroup("job_tasks");
                            jobConfigInfo.setCronExpression(dbTaskConfigInfo.getJobCron());
                            jobConfigInfo.setJobClass(dbTaskConfigInfo.getClassPath());
                            jobConfigInfo.setJobMethod(dbTaskConfigInfo.getProcessMothod());
                            jobConfigInfo.setDataChangeLastTime(dbTaskConfigInfo.getDataChangeLastTime());

                            schedulerManager.addJob(jobConfigInfo);
                            taskMap.put(taskName,jobConfigInfo);
                        }
                    }
                }


            } catch (Exception e) {
                logger.error(ExceptionUtils.getStackTrace(e));
            }
        }
    }

    public static boolean isJobAlreadyScheduled(JobConfigInfo job) {
        return taskMap.containsKey(job);
    }

    /*
    public void monitor() throws SchedulerException {
        logger.info("Task manager begins to monitor each task");
        for (String jobId : taskMap.keySet()) {
            EtlScheduleJob job = taskMap.get(jobId);

            AtomicLong heartBeat = jobHeartBeats.get(jobId);
            if (heartBeat == null) {
                heartBeat = new AtomicLong(System.currentTimeMillis() / HEART_BEAT_PERIOD);
                jobHeartBeats.put(jobId, heartBeat);
            }

            if ((System.currentTimeMillis() - heartBeat.longValue() * HEART_BEAT_PERIOD) > DEATH_TIME_OUT_THRESHOLD) {
                logger.warn("Job {} is dead", job.getJobId());
                if (job.getJobStatus().equals(EtlScheduleJob.JobStatus.ACTIVE)) {
                    logger.warn("Job {} restarts", job.getJobId());
                    etlScheduler.restartJob(job);
                    heartBeat.set(System.currentTimeMillis() / HEART_BEAT_PERIOD);
                    continue;
                }
            }
            logger.info("Job {} is ok", job.getJobId());
        }
    }
    */
}