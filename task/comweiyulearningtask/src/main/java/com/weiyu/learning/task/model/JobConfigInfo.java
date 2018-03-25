package com.weiyu.learning.task.model;

import com.weiyu.learning.task.dao.taskmanagerdb.TaskConfigJobStatus;
import lombok.Data;
import lombok.ToString;
import org.quartz.JobKey;

import java.lang.reflect.Method;
import java.util.*;

/**
 * @author: weiyu
 * @date: 2018/3/8
 */
@Data
@ToString(exclude = {"dataChangeLastTime", "jobParams"})
public class JobConfigInfo {
    private String jobId;
    private String jobName;
    private String jobGroup;

    private String cronExpression;
    private String description;
    private JobKey jobKey;
    private String jobClass;
    private String jobMethod;
    private Map<String,String> jobParams;

    TaskConfigJobStatus jobStatus;
    Date dataChangeLastTime;
}
