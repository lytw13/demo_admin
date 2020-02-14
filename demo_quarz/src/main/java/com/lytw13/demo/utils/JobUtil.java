package com.lytw13.demo.utils;

import com.lytw13.demo.config.MyJob;
import com.lytw13.demo.model.TbJob;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JobUtil {
    @Autowired
    Scheduler scheduler;

    /**
     * 新建一个任务
     *
     */
    public String addJob(TbJob tbJob) throws Exception  {
        if (!CronExpression.isValidExpression(tbJob.getJobCron())) {
            return "Illegal cron expression";   //表达式格式不正确
        }
        JobKey jobKey = new JobKey(tbJob.getJobName(), "default");
        JobDetail jobDetail = JobBuilder.newJob(MyJob.class).withIdentity(jobKey).build();

        jobDetail.getJobDataMap().put("INVORK_METHOD", tbJob);
        //表达式调度构建器(即任务执行的时间,不立即执行)
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(tbJob.getJobCron()).withMisfireHandlingInstructionFireAndProceed();

        //按新的cronExpression表达式构建一个新的trigger
        CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(tbJob.getJobName(), "default").startAt(new Date())
                .withSchedule(scheduleBuilder).build();
        scheduler.scheduleJob(jobDetail, trigger);
        return "success";
    }
    /**
     * 获取Job状态
     * @param jobName
     * @param jobGroup
     * @return
     * @throws SchedulerException
     */
    public String getJobState(String jobName, String jobGroup) throws SchedulerException {
        TriggerKey triggerKey = new TriggerKey(jobName, jobGroup);
        return scheduler.getTriggerState(triggerKey).name();
    }

    //暂停所有任务
    public void pauseAllJob() throws SchedulerException {
        scheduler.pauseAll();
    }

    //暂停任务
    public String pauseJob(TbJob tbJob) throws SchedulerException {
        JobKey jobKey = new JobKey(tbJob.getJobName(), "default");
        JobDetail jobDetail = scheduler.getJobDetail(jobKey);
        if (jobDetail == null) {
            return "fail";
        }else {
            scheduler.pauseJob(jobKey);
            return "success";
        }

    }

    //恢复所有任务
    public void resumeAllJob() throws SchedulerException {
        scheduler.resumeAll();
    }

    // 恢复某个任务
    public String resumeJob(TbJob tbJob) throws SchedulerException {

        JobKey jobKey = new JobKey(tbJob.getJobName(), "default");
        JobDetail jobDetail = scheduler.getJobDetail(jobKey);
        if (jobDetail == null) {
            return "fail";
        }else {
            scheduler.resumeJob(jobKey);
            return "success";
        }
    }

    //删除某个任务
    public String  deleteJob(TbJob tbJob) throws SchedulerException {
        JobKey jobKey = new JobKey(tbJob.getJobName(), "default");
        JobDetail jobDetail = scheduler.getJobDetail(jobKey);
        if (jobDetail == null ) {
            return "jobDetail is null";
        }else if(!scheduler.checkExists(jobKey)) {
            return "jobKey is not exists";
        }else {
            scheduler.deleteJob(jobKey);
            return "success";
        }

    }

    //修改任务
    public String  modifyJob(TbJob tbJob) throws SchedulerException {
        if (!CronExpression.isValidExpression(tbJob.getJobCron())) {
            return "Illegal cron expression";
        }
        TriggerKey triggerKey = TriggerKey.triggerKey(tbJob.getJobName(),"default");
        JobKey jobKey = new JobKey(tbJob.getJobName(),"default");
        if (scheduler.checkExists(jobKey) && scheduler.checkExists(triggerKey)) {
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            //表达式调度构建器,不立即执行
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(tbJob.getJobCron()).withMisfireHandlingInstructionFireAndProceed();
            //按新的cronExpression表达式重新构建trigger
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey)
                    .withSchedule(scheduleBuilder).build();
            //按新的trigger重新设置job执行
            scheduler.rescheduleJob(triggerKey, trigger);
            return "success";
        }else {
            return "job or trigger not exists";
        }

    }
}
