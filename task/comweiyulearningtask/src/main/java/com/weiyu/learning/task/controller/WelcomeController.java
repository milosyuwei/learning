package com.weiyu.learning.task.controller;

import com.weiyu.learning.task.dao.taskmanagerdb.TaskConfig;
import com.weiyu.learning.task.dao.taskmanagerdb.mapper.TaskListMapper;
import com.weiyu.learning.task.dao.taskmanagerlogdb.mapper.TaskLogMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Created by weiyu on 2018/1/19.
 */
@RestController
public class WelcomeController {
    private static final Logger logger = LoggerFactory.getLogger(WelcomeController.class);

    @Autowired
    TaskListMapper taskListMapper;
    @Autowired
    TaskLogMapper taskLogMapper;

    @RequestMapping
    public String index(){
        try {
            List<TaskConfig> lst = taskListMapper.selectActiveTaskList();
            logger.info(lst.toString());
            List<Map> log = taskLogMapper.selectTaskLog();

        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }
}
