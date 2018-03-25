package com.weiyu.learning.task.dao.taskmanagerdb;

import com.weiyu.learning.task.utils.mybatis.type.EnumObject;

public enum TaskConfigInstanceModel implements EnumObject<TaskConfigJobStatus> {
    Single(0, "Single"),
    Miult(1, "Miult")
    ;

    int code;
    String message;

    TaskConfigInstanceModel(int code,String message){
        this.code = code;
        this.message = message;
    }

    @Override
    public Object getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
