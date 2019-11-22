package com.yy.server.model.view;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author ChenXiangpeng
 * @ClassName CounselingRegistrationListVO
 * @date：2019/11/13
 * @version: V1.0.0
 * @description：
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CounselingRegistrationListVO {
    /**
     * 预约时间
     */
    private String reservationTime;
    /**
     * 姓名
     */
    private String name;
    /**
     * 带区域码手机号
     */
    private String phone;

    /**
     * 类型：1青少年；2成人
     */
    private Integer type;
    /**
     * 性别：1男；2女
     */
    private String sex;

    /**
     * 年龄范围：1,10~19;2,20~25;3,26~29;4,30~39;5,40~49;6,50岁以上
     */
    private Integer age;
    /**
     * 职业类别：1:学生；2公司职员；3家庭主妇；4其他
     */
    private String job;

    private String question;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    private Date createdTime;
}
