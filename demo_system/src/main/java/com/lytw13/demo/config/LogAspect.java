package com.lytw13.demo.config;

import com.lytw13.demo.OperType;
import com.lytw13.demo.annotation.Log;
import com.lytw13.demo.model.TbLog;
import com.lytw13.demo.service.TbLogService;
import com.lytw13.demo.utils.IPUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Component
@Aspect
public class LogAspect {
    @Autowired
    TbLogService tbLogService;

    private long startTime;
    private long endTime;
    Logger logger;


    @Pointcut("@annotation(com.lytw13.demo.annotation.Log)")
    public void recordLog() {

    }
    
    @Before(value = "recordLog()")
    public void beforeLog(JoinPoint joinPoint) {
        startTime = System.currentTimeMillis();
    }

    //后置通知
    @AfterReturning(value = "recordLog()",returning = "obj")
    public void afterLogs(JoinPoint joinPoint,Object obj) {
        saveLog(joinPoint);
        logger.info("后置通知。。。。，返回值："+obj);
    }

    //最终通知
    @After(value = "recordLog()")
    public void finnallyLog(JoinPoint joinPoint) {
        Object target = joinPoint.getTarget();
        logger = LoggerFactory.getLogger(target.getClass());
        logger.info("最终通知。。。。");
    }

    //异常通知
    @AfterThrowing(value = "recordLog()",throwing="e")
    public void throwLog(JoinPoint joinPoint,Exception e) {
        saveLog(joinPoint);
        logger.info("异常通知。。。。,异常信息："+e.getMessage());
    }

    public void saveLog(JoinPoint joinPoint) {
        TbLog tbLog = new TbLog();
        Object target = joinPoint.getTarget();
        logger = LoggerFactory.getLogger(target.getClass());
        String name = joinPoint.getSignature().getName();
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Log annotation = methodSignature.getMethod().getAnnotation(Log.class);
        String comment = annotation.value();
        tbLog.setLogOperComment(comment);
        //获取request
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        tbLog.setLogOperIp(IPUtil.getIpAddr(request));
        endTime = System.currentTimeMillis();
        tbLog.setLogOperConsume(endTime-startTime);
        tbLog.setLogOperMethod(name);
        String method = request.getMethod();
        tbLog.setLogOperRequest(method);
        if(name.equalsIgnoreCase("add") || name.equalsIgnoreCase("addData")) {
            tbLog.setLogOperType(OperType.INSERT.getCode());
        }else if(name.equalsIgnoreCase("delete") || name.equalsIgnoreCase("deleteData")) {
            tbLog.setLogOperType(OperType.DELETE.getCode());
        } else if(name.equalsIgnoreCase("update") || name.equalsIgnoreCase("modifyData")) {
            tbLog.setLogOperType(OperType.UPDATE.getCode());
        }
        tbLogService.insert(tbLog);
    }
}
