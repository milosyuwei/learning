package com.weiyu.learning.property.myconfig;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author: weiyu
 * @date: 2018/2/9
 */
@Configuration
@ConfigurationProperties(prefix = "remote", ignoreUnknownFields = false)
@PropertySource("classpath:remote.properties")
@Component
public class RemoteProperties {
    private String uploadFilesUrl;

    public String getUploadFilesUrl() {
        return uploadFilesUrl;
    }

    public void setUploadFilesUrl(String uploadFilesUrl) {
        this.uploadFilesUrl = uploadFilesUrl;
    }

    public String getUploadPicUrl() {
        return uploadPicUrl;
    }

    public void setUploadPicUrl(String uploadPicUrl) {
        this.uploadPicUrl = uploadPicUrl;
    }

    private String uploadPicUrl;
}
