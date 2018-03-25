package com.weiyu.learning.task.utils.mybatis.type;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.lang.Validate;

import java.util.Objects;

/**
 * 枚举工具类
 * @author: weiyu
 * @date: 2018/3/9
 */
public class EnumObjects {
    /**
     * 禁止实例化
     */
    private EnumObjects() {
        super();
    }

    /**
     * 根据枚举值获取到枚举对象
     *
     * @param code  枚举值
     * @param clazz 枚举类型
     *
     * @return 返回枚举对象，不存在则返回{@code null}
     */
    public static <E extends Enum<E> & EnumObject<E>> E getEnum(Object code, Class<E> clazz) {
        Validate.notNull(code);
        Validate.notNull(clazz);

        E[] enums = clazz.getEnumConstants();
        for (E eo : enums) {
            if (Objects.equals(eo.getCode(), code)) {
                return eo;
            }

            Object value = ConvertUtils.convert(code, eo.getCode().getClass());
            if (Objects.equals(eo.getCode(), value)) {
                return eo;
            }
        }
        return null;
    }
}
