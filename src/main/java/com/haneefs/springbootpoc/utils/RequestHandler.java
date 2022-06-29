package com.haneefs.springbootpoc.utils;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.util.UUID;

@Component
@RequestScope
public class RequestHandler {

    private String requestId;

    public RequestHandler(){
        requestId = UUID.randomUUID().toString();
    }

    public String getRequestId(){
        return requestId;
    }

}
