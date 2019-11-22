package com.yy.server.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.*;
import java.util.Date;

/**
 * @author ChenXiangpeng
 * @ClassName CounselingRegistrationAddDTO
 * @date：2019/11/13
 * @version: V1.0.0
 * @description：心理咨询登记添加DTO
 */
@Data
public class CounselingRegistrationAddDTO {
    /**
     * 姓名
     */
    @NotBlank(message = "请输入姓名")
    @Size(message = "姓名输入过长",max = 16)
    private String name;

    /**
     * 性别：1男；2女
     */
    @NotBlank(message = "请选择性别")
    private String sex;

    /**
     * 带区域码手机号
     */
    @NotBlank(message = "请输入手机号")
    @Size(max = 11,min = 11,message = "请输入11位手机号")
    private String phone;

    /**
     * 年龄范围：1,10~19;2,20~25;3,26~29;4,30~39;5,40~49;6,50岁以上
     */
    @NotNull(message = "请输入年龄")
    @Max(value = 99,message = "请输入正确的年龄")
    @Min(value = 0,message = "请输入正确的年龄")
    private Integer age;

    /**
     * 职业类别：1:学生；2公司职员；3家庭主妇；4其他
     */
    @NotBlank(message = "请输入职业类别")
    @Size(message = "职业类别输入过长",max = 16)
    private String job;

    /**
     * 类型：1青少年；2成人
     */
    private Integer type;

    @NotBlank(message = "请输入困惑")
    @Size(message = "困惑输入过长",max = 128)
    private String question;

    /**
     * 预约时间
     */
    @NotNull(message = "请选择预约时间")
    private String reservationTime;
}
