package com.weiyu.pattern.reactor;

/**
 * event处理器的抽象类
 * Created by weiyu on 2018/1/8.
 */
public abstract class EventHandler {
    private InputSource source;
    public abstract void handle(Event event);

    public InputSource getSource() {
        return source;
    }

    public void setSource(InputSource source) {
        this.source = source;
    }

}
