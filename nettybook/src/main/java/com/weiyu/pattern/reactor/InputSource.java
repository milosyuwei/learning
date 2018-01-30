package com.weiyu.pattern.reactor;

/**
 * 输入对象，reactor模式中处理的原始输入对象
 * Created by weiyu on 2018/1/8.
 */
public class InputSource {
    private Object data;
    private long id;

    public InputSource(Object data, long id) {
        this.data = data;
        this.id = id;
    }

    @Override
    public String toString() {
        return "InputSource{" +
                "data=" + data +
                ", id=" + id +
                '}';
    }

}
