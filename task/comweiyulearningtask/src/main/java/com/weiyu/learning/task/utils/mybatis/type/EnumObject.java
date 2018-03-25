package com.weiyu.learning.task.utils.mybatis.type;

import java.io.Serializable;

/**
 * 枚举接口
 * @author: weiyu
 * @date: 2018/3/9
 */
public interface EnumObject<E extends Enum<E>> extends Serializable, Cloneable {

    /**
     * 获取枚举值
     *
     * @return 枚举值
     */
    Object getCode();

    /**
     * 获取枚举描述
     *
     * @return 返回枚举描述
     */
    String getMessage();
}