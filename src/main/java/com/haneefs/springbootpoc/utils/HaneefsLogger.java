package com.haneefs.springbootpoc.utils;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Slf4j
public class HaneefsLogger {


    HaneefsLogger myLogger;
      Logger logger;

    HaneefsLogger(Logger logger){
        this.logger = logger;
    }
    public  static HaneefsLogger createLogger(final Class c){
        HaneefsLogger hLogger = new HaneefsLogger(LoggerFactory.getLogger(c));
        return hLogger;
    }

    public  void info(String requestId,String code,String subCode,String msg, Object ...args){
        logger.info("Request ID: {}, Code: {}, Sub Code: {}, Message: {} and Parameter Values are [{}]",requestId,code,subCode,msg,args);
    }

    public  void info(String requestId,String code,String msg, Object ...args){
        logger.info("Request ID: {}, Code: {}, Message: {} and Parameter Values are [{}]",requestId,code,msg,args);
    }

    public  void info(String requestId,String code,String msg){
        logger.info("Request ID: {}, Code: {}, Message: {} ",requestId,code,msg);
    }

    public  void debug(String requestId,String code,String subCode,String msg, Object ...args){
        logger.debug("Request ID: {}, Code: {}, Sub Code: {}, Message: {} and Parameter Values are [{}]",requestId,code,subCode,msg,args);
    }

    public  void debug(String requestId,String code,String msg, Object ...args){
        logger.debug("Request ID: {}, Code: {}, Message: {} and Parameter Values are [{}]",requestId,code,msg,args);
    }

    public  void error(String requestId, String errorCode,String msg, Object ...args){
        logger.error("Request ID: {}, Error Code: {}, Message: {} and Parameter Values are [{}]",requestId,errorCode,msg,args);
    }
}
