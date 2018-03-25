package com.weiyu.learning.task.dao.taskmanagerdb.mapper;

import com.weiyu.learning.task.dao.taskmanagerdb.TaskConfig;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: weiyu
 * @date: 2018/3/9
 */
@Repository
public interface TaskListMapper {
    List<TaskConfig> selectActiveTaskList();
}
