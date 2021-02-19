package com.lc.homepage.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * (TLogRecord)实体类
 *
 * @author makejava
 * @since 2020-10-15 09:47:50
 */
@Data
public class TLogRecord implements Serializable {
    private static final long serialVersionUID = -72552101509839275L;
    /**
     * 日志记录主键id
     */
    private String pkRecordId;
    /**
     * 日志记录操作功能模块
     */
    private String recordModule;
    /**
     * 操作类型
     */
    private String recordType;
    /**
     * 操作描述
     */
    private String recordDesc;
    /**
     * 请求参数
     */
    private String recordRequParam;
    /**
     * 返回参数
     */
    private String recordRespParam;
    /**
     * 操作员id
     */
    private String recordUserId;
    /**
     * 操作员姓名
     */
    private String recordUserName;
    /**
     * 操作方法
     */
    private String recordMethod;
    /**
     * 请求url
     */
    private String recordUrl;
    /**
     * ip
     */
    private String recordIp;
    /**
     * 日志创建日期
     */
    private Date recordTime;
    /**
     * 操作版本号
     */
    private String recordVersion;
}