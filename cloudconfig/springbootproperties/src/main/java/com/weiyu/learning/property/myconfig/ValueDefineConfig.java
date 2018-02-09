package com.weiyu.learning.property.myconfig;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author: weiyu
 * @date: 2018/2/9
 */
@PropertySource("classpath:value.properties")  //配置文件路径
@Component
public class ValueDefineConfig {

    @Value("${milos.num}")
    String num;

    /**
     * Using Spring EL:
     */
    @Value("#{'${milos.names}'.split(',')}")
    List<String> names;


    Date date;
    boolean isSuccess;

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public Date getDate() {
        return date;
    }

    @Value("${milos.date}")
    public void setDate(String date) throws Exception{

        this.date = DateUtils.parseDate(date,"yyyy-MM-dd","yyyy-MM-dd hh:mm:ss");
    }

    public boolean getIsSuccess() {
        return isSuccess;
    }

    @Autowired
    public void setIsSuccess(@Value("${milos.isSuccess}") String isSuccess) throws Exception{
        this.isSuccess = Boolean.parseBoolean(isSuccess);
    }

    public List<String> getNames() {
        return names;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }
}
