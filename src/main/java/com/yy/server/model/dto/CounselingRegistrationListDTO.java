package com.yy.server.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yy.server.model.view.PageVO;
import lombok.Data;

import java.util.Date;

/**
 * @author ChenXiangpeng
 * @ClassName CounselingRegistrationListDTO
 * @date：2019/11/13
 * @version: V1.0.0
 * @description：心理咨询登记列表DTO
 */
@Data
public class CounselingRegistrationListDTO extends PageVO {
    /**
     * 姓名
     */
    private String name;

    /**
     * 性别：1男；2女
     */
    private String sex;

    /**
     * 带区域码手机号
     */
    private String phone;

    /**
     * 登记时间开始
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    private Date createdTimeStart;
    /**
     * 登记时间结束
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    private Date createdTimeEnd;

    /**
     * 预约时间开始
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    private Date reservationTimeStart;
    /**
     * 预约时间结束
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    private Date reservationTimeEnd;
}
