package com.lytw13.demo.config;

import com.lytw13.demo.model.TbJob;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.Method;

public class MyJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext){
        JobDataMap jobDataMap = jobExecutionContext.getMergedJobDataMap();
//        TbJob job = (TbJob) jobDataMap.get("INVORK_METHOD");    ????
        TbJob job = new TbJob();
        BeanUtils.copyProperties(jobDataMap.get("INVORK_METHOD"),
                job);
        String invokemethod = job.getJobInvokemethod();
        String[] split = invokemethod.split("\\.");
        int i = invokemethod.indexOf(".");

        try {
            Class<?> clazz = Class.forName("com.lytw13.demo.controller."+split[0]);
            Method method = clazz.getMethod(split[1],Integer.class);
           method.invoke(clazz.newInstance(),1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
