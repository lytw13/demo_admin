package com.lytw13.demo.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 日志表(TbLog)实体类
 *
 * @author lytw13
 * @since 2020-02-12 13:17:16
 */
@Data
public class TbLog implements Serializable {

    private Integer logId;
    
    private String logOperRequest;
    
    private String logOperMethod;
    
    private Integer logOperType;

    private String logOperComment;
    
    private String logOperIp;
    
    private Long logOperConsume;
    
    private Date logOperDate;
    
    private Integer logStatus;

}