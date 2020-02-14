package com.lytw13.demo.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 定时任务调度表(TbJob)实体类
 *
 * @author makejava
 * @since 2020-02-13 13:56:44
 */
@Data
public class TbJob implements Serializable {
    /**
    * 任务ID
    */
    private Integer jobId;
    /**
    * 任务名称
    */
    private String jobName;
    /**
    * 调用目标字符串
    */
    private String jobInvokemethod;
    /**
    * cron执行表达式
    */
    private String jobCron;
    /**
    * 状态（0正常 1暂停）
    */
    private String jobStatus;
    /**
    * 创建时间
    */
    private Date jobCreatedate;

}