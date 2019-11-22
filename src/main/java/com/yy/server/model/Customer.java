package com.yy.server.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

public class Customer implements IAppIdModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
     * 带区域码手机号
     */
    @Column(name = "all_phone")
    private String allPhone;

    /**
     * 是否有效 1：有效 0：无效
     */
    private Boolean state;

    /**
     * 邀请码
     */
    @Column(name = "invite_code")
    private String inviteCode;

    /**
     * 国家id
     */
    @Column(name = "country_id")
    private Integer countryId;

    /**
     * 盐
     */
    private String salt;

    /**
     * 语言
     */
    private String language;

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

    /**
     * 认证状态:0 未认证；1 已认证
     */
    @Column(name = "certificate_state")
    private Boolean certificateState;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 注册方式：0手机号1邮箱
     */
    @Column(name = "reg_way")
    private Integer regWay;

    /**
     * 自己的的邀请码
     */
    @Column(name = "self_invite")
    private String selfInvite;

    @Column(name = "app_id")
    private String appId;

    private String identity;

    /**
     * 地区ID
     */
    @Column(name = "area_id")
    private Integer areaId;

    /**
     * 性别：0未知：1男；2女
     */
    private String sex;

    /**
     * 姓名
     */
    private String name;

    /**
     * 年龄范围：1,10~19;2,20~25;3,26~29;4,30~39;5,40~49;6,50岁以上
     */
    @Column(name = "age_range")
    private String ageRange;

    /**
     * 职业类别：1:学生；2公司职员；3家庭主妇；4其他
     */
    @Column(name = "job_type")
    private String jobType;

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
     * 获取带区域码手机号
     *
     * @return all_phone - 带区域码手机号
     */
    public String getAllPhone() {
        return allPhone;
    }

    /**
     * 设置带区域码手机号
     *
     * @param allPhone 带区域码手机号
     */
    public void setAllPhone(String allPhone) {
        this.allPhone = allPhone;
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
     * 获取邀请码
     *
     * @return invite_code - 邀请码
     */
    public String getInviteCode() {
        return inviteCode;
    }

    /**
     * 设置邀请码
     *
     * @param inviteCode 邀请码
     */
    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    /**
     * 获取国家id
     *
     * @return country_id - 国家id
     */
    public Integer getCountryId() {
        return countryId;
    }

    /**
     * 设置国家id
     *
     * @param countryId 国家id
     */
    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
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

    /**
     * 获取语言
     *
     * @return language - 语言
     */
    public String getLanguage() {
        return language;
    }

    /**
     * 设置语言
     *
     * @param language 语言
     */
    public void setLanguage(String language) {
        this.language = language;
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
     * 获取认证状态:0 未认证；1 已认证
     *
     * @return certificate_state - 认证状态:0 未认证；1 已认证
     */
    public Boolean getCertificateState() {
        return certificateState;
    }

    /**
     * 设置认证状态:0 未认证；1 已认证
     *
     * @param certificateState 认证状态:0 未认证；1 已认证
     */
    public void setCertificateState(Boolean certificateState) {
        this.certificateState = certificateState;
    }

    /**
     * 获取邮箱
     *
     * @return email - 邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置邮箱
     *
     * @param email 邮箱
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取注册方式：0手机号1邮箱
     *
     * @return reg_way - 注册方式：0手机号1邮箱
     */
    public Integer getRegWay() {
        return regWay;
    }

    /**
     * 设置注册方式：0手机号1邮箱
     *
     * @param regWay 注册方式：0手机号1邮箱
     */
    public void setRegWay(Integer regWay) {
        this.regWay = regWay;
    }

    /**
     * 获取自己的的邀请码
     *
     * @return self_invite - 自己的的邀请码
     */
    public String getSelfInvite() {
        return selfInvite;
    }

    /**
     * 设置自己的的邀请码
     *
     * @param selfInvite 自己的的邀请码
     */
    public void setSelfInvite(String selfInvite) {
        this.selfInvite = selfInvite;
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
     * @return identity
     */
    public String getIdentity() {
        return identity;
    }

    /**
     * @param identity
     */
    public void setIdentity(String identity) {
        this.identity = identity;
    }

    /**
     * 获取地区ID
     *
     * @return area_id - 地区ID
     */
    public Integer getAreaId() {
        return areaId;
    }

    /**
     * 设置地区ID
     *
     * @param areaId 地区ID
     */
    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    /**
     * 获取性别：0未知：1男；2女
     *
     * @return sex - 性别：0未知：1男；2女
     */
    public String getSex() {
        return sex;
    }

    /**
     * 设置性别：0未知：1男；2女
     *
     * @param sex 性别：0未知：1男；2女
     */
    public void setSex(String sex) {
        this.sex = sex;
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
     * 获取年龄范围：1,10~19;2,20~25;3,26~29;4,30~39;5,40~49;6,50岁以上
     *
     * @return age_range - 年龄范围：1,10~19;2,20~25;3,26~29;4,30~39;5,40~49;6,50岁以上
     */
    public String getAgeRange() {
        return ageRange;
    }

    /**
     * 设置年龄范围：1,10~19;2,20~25;3,26~29;4,30~39;5,40~49;6,50岁以上
     *
     * @param ageRange 年龄范围：1,10~19;2,20~25;3,26~29;4,30~39;5,40~49;6,50岁以上
     */
    public void setAgeRange(String ageRange) {
        this.ageRange = ageRange;
    }

    /**
     * 获取职业类别：1:学生；2公司职员；3家庭主妇；4其他
     *
     * @return job_type - 职业类别：1:学生；2公司职员；3家庭主妇；4其他
     */
    public String getJobType() {
        return jobType;
    }

    /**
     * 设置职业类别：1:学生；2公司职员；3家庭主妇；4其他
     *
     * @param jobType 职业类别：1:学生；2公司职员；3家庭主妇；4其他
     */
    public void setJobType(String jobType) {
        this.jobType = jobType;
    }
}