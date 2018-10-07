package com.liaoyb.actuator.myactuator;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liaoyb
 * @date 2018-10-07 16:33
 */
@Endpoint(id = "my-actuator")
public class MyActuatorEndpoint {

    @ReadOperation
    public Map<String, Object> info() {
        //todo 返回监控信息
        return new HashMap<>();
    }
}
