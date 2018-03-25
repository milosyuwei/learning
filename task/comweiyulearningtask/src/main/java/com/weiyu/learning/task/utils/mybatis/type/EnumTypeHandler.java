package com.weiyu.learning.task.utils.mybatis.type;

import org.apache.commons.lang.Validate;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Mybatis自定义转换枚举类型
 *
 * @author: weiyu
 * @date: 2018/3/9
 * E extends Enum<E> & EnumObject<E>
 * 由于EnumObject<E extends Enum> 所以这里必须 E extends Enum<E> & EnumObject<E>
 */
public class EnumTypeHandler<E extends Enum<E> & EnumObject<E>> extends BaseTypeHandler<E> {
    private final Class<E> clazz;

    /**
     * 根据枚举类构建枚举转换器
     *
     * @param clazz 枚举类
     */
    public EnumTypeHandler(Class<E> clazz) {
        Validate.notNull(clazz);

        this.clazz = clazz;
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, E parameter, JdbcType jdbcType) throws SQLException {
        ps.setObject(i, parameter.getCode());
    }

    @Override
    public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
        Object code = rs.getObject(columnName);
        if (rs.wasNull()) {
            return null;
        }
        else {
            return getEnumByCode(code);
        }
    }

    @Override
    public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        // 根据数据库存储类型决定获取类型
        Object code = rs.getObject(columnIndex);
        if (rs.wasNull()) {
            return null;
        }
        else {
            return getEnumByCode(code);
        }
    }

    @Override
    public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        Object code = cs.getObject(columnIndex);
        if (cs.wasNull()) {
            return null;
        }
        else {
            return getEnumByCode(code);
        }
    }

    /**
     * 枚举类型转换
     *
     * @param code  枚举代号
     * @return      枚举对象
     */
    private E getEnumByCode(Object code) {
        E eo = EnumObjects.getEnum(code, clazz);
        if (eo == null) {
            throw new IllegalArgumentException("Enumeration not find code(" + code + ") in the class(" + clazz + ")");
        }
        return eo;
    }
}
