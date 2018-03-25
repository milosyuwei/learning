package com.weiyu.learning.task.dao.taskmanagerdb;

import lombok.Data;

import java.util.Date;

/**
 * @author: weiyu
 * @date: 2018/3/9
 */
@Data
public class TaskConfig {
    String taskName;
    String jobCron;
    TaskConfigInstanceModel instanceModel;
    String jobContent;
    TaskConfigJobStatus jobStatus;
    String classPath;
    String processMothod;
    String mothodParameter;
    String remarks;
    Date dataChangeLastTime;
}