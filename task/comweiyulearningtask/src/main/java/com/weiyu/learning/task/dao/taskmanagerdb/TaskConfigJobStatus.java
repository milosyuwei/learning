package com.weiyu.learning.task.dao.taskmanagerdb;

import com.weiyu.learning.task.utils.mybatis.type.EnumObject;

public enum TaskConfigJobStatus implements EnumObject<TaskConfigJobStatus> {

    STOP(0, "停止"),
    CTIVE(1, "开启")
    ;

    final int code;
    final String message;
    TaskConfigJobStatus(final int code, final String message){
        this.code = code;
        this.message = message;
    }
    @Override
    public Object getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return null;
    }
}
