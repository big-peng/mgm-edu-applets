package com.yy.server.model.po;

import java.util.Date;
import javax.persistence.*;

@Table(name = "cust_register")
public class CustRegister {
    @Id
    private Integer id;

    /**
     * 用户名
     */
    private String account;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 是否有效 1：有效 0：无效
     */
    private Boolean state;

    /**
     * 来源 :0手机app 1后台
     */
    private Integer origin;

    /**
     * 备注
     */
    private String remark;

    @Column(name = "created_time")
    private Date createdTime;

    @Column(name = "updated_time")
    private Date updatedTime;

    @Column(name = "app_id")
    private String appId;

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
     * 年龄范围：1,10~19;2,20~25;3,26~29;4,30~39;5,40~49;6,50岁以上
     */
    private Integer age;

    /**
     * 职业类别：1:学生；2公司职员；3家庭主妇；4其他
     */
    private String job;

    /**
     * 类型：1青少年；2成人
     */
    private Integer type;

    private String question;

    /**
     * 预约时间
     */
    @Column(name = "reservation_time")
    private String reservationTime;

    /**
     * 盐
     */
    private String salt;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取用户名
     *
     * @return account - 用户名
     */
    public String getAccount() {
        return account;
    }

    /**
     * 设置用户名
     *
     * @param account 用户名
     */
    public void setAccount(String account) {
        this.account = account;
    }

    /**
     * 获取用户密码
     *
     * @return password - 用户密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置用户密码
     *
     * @param password 用户密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取是否有效 1：有效 0：无效
     *
     * @return state - 是否有效 1：有效 0：无效
     */
    public Boolean getState() {
        return state;
    }

    /**
     * 设置是否有效 1：有效 0：无效
     *
     * @param state 是否有效 1：有效 0：无效
     */
    public void setState(Boolean state) {
        this.state = state;
    }

    /**
     * 获取来源 :0手机app 1后台
     *
     * @return origin - 来源 :0手机app 1后台
     */
    public Integer getOrigin() {
        return origin;
    }

    /**
     * 设置来源 :0手机app 1后台
     *
     * @param origin 来源 :0手机app 1后台
     */
    public void setOrigin(Integer origin) {
        this.origin = origin;
    }

    /**
     * 获取备注
     *
     * @return remark - 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * @return created_time
     */
    public Date getCreatedTime() {
        return createdTime;
    }

    /**
     * @param createdTime
     */
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    /**
     * @return updated_time
     */
    public Date getUpdatedTime() {
        return updatedTime;
    }

    /**
     * @param updatedTime
     */
    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    /**
     * @return app_id
     */
    public String getAppId() {
        return appId;
    }

    /**
     * @param appId
     */
    public void setAppId(String appId) {
        this.appId = appId;
    }

    /**
     * 获取姓名
     *
     * @return name - 姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置姓名
     *
     * @param name 姓名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取性别：1男；2女
     *
     * @return sex - 性别：1男；2女
     */
    public String getSex() {
        return sex;
    }

    /**
     * 设置性别：1男；2女
     *
     * @param sex 性别：1男；2女
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * 获取带区域码手机号
     *
     * @return phone - 带区域码手机号
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置带区域码手机号
     *
     * @param phone 带区域码手机号
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取年龄范围：1,10~19;2,20~25;3,26~29;4,30~39;5,40~49;6,50岁以上
     *
     * @return age - 年龄范围：1,10~19;2,20~25;3,26~29;4,30~39;5,40~49;6,50岁以上
     */
    public Integer getAge() {
        return age;
    }

    /**
     * 设置年龄范围：1,10~19;2,20~25;3,26~29;4,30~39;5,40~49;6,50岁以上
     *
     * @param age 年龄范围：1,10~19;2,20~25;3,26~29;4,30~39;5,40~49;6,50岁以上
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * 获取职业类别：1:学生；2公司职员；3家庭主妇；4其他
     *
     * @return job - 职业类别：1:学生；2公司职员；3家庭主妇；4其他
     */
    public String getJob() {
        return job;
    }

    /**
     * 设置职业类别：1:学生；2公司职员；3家庭主妇；4其他
     *
     * @param job 职业类别：1:学生；2公司职员；3家庭主妇；4其他
     */
    public void setJob(String job) {
        this.job = job;
    }

    /**
     * 获取类型：1青少年；2成人
     *
     * @return type - 类型：1青少年；2成人
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置类型：1青少年；2成人
     *
     * @param type 类型：1青少年；2成人
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * @return question
     */
    public String getQuestion() {
        return question;
    }

    /**
     * @param question
     */
    public void setQuestion(String question) {
        this.question = question;
    }

    /**
     * 获取预约时间
     *
     * @return reservation_time - 预约时间
     */
    public String getReservationTime() {
        return reservationTime;
    }

    /**
     * 设置预约时间
     *
     * @param reservationTime 预约时间
     */
    public void setReservationTime(String reservationTime) {
        this.reservationTime = reservationTime;
    }

    /**
     * 获取盐
     *
     * @return salt - 盐
     */
    public String getSalt() {
        return salt;
    }

    /**
     * 设置盐
     *
     * @param salt 盐
     */
    public void setSalt(String salt) {
        this.salt = salt;
    }
}