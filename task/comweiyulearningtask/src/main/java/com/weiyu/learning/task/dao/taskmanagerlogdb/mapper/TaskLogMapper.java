package com.weiyu.learning.task.dao.taskmanagerlogdb.mapper;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Repository
public interface TaskLogMapper {
    List<Map> selectTaskLog();

    int insert();
}
