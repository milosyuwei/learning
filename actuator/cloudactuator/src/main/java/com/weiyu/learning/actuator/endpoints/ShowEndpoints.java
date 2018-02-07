package com.weiyu.learning.actuator.endpoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.AbstractEndpoint;
import org.springframework.boot.actuate.endpoint.Endpoint;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * List All The Endpoints
 * 通过 http://localhost:31011/admin/showEndpoints 可以看到所有的 endpoint 信息
 */
@Component
public class ShowEndpoints  extends AbstractEndpoint<List<Endpoint>> {
    private List<Endpoint> endpoints;

    @Autowired
    public ShowEndpoints(List<Endpoint> endpoints) {
        super("showEndpoints");
        this.endpoints = endpoints;
    }

    public List<Endpoint> invoke() {
        return this.endpoints;
    }
}
