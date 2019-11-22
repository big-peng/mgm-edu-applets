package com.yy.server.model.view;

import com.yy.server.model.IAppIdModel;

import java.util.Date;
import java.util.List;

public class AiDiagnoseTemplateResultVO extends PageVO implements IAppIdModel {

    private Integer id;

    /**
     * 名称
     */
    private String name;

    /**
     * 描述1
     */
    private String des1;

    /**
     * 描述2
     */
    private String desc2;

    /**
     * 描述2分隔符（*或者，）
     */
    private String desc2Split;

    /**
     * 图片URL
     */
    private String imageUrl;

    /**
     * 护理对策
     */
    private String nursePlan;

    private Date createTime;

    /**
     * 删除标识:Y删除：N未删除
     */
    private String delFlag;

    /**
     * 备注
     */
    private String remark;

    /**
     * 序号
     */
    private Integer orderId;

    private String appId;


    private List<AiProductResultVO> aiProductResultList;

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
     * 获取名称
     *
     * @return name - 名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置名称
     *
     * @param name 名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取描述1
     *
     * @return des1 - 描述1
     */
    public String getDes1() {
        return des1;
    }

    /**
     * 设置描述1
     *
     * @param des1 描述1
     */
    public void setDes1(String des1) {
        this.des1 = des1;
    }

    /**
     * 获取描述2
     *
     * @return desc2 - 描述2
     */
    public String getDesc2() {
        return desc2;
    }

    /**
     * 设置描述2
     *
     * @param desc2 描述2
     */
    public void setDesc2(String desc2) {
        this.desc2 = desc2;
    }

    /**
     * 获取描述2分隔符（*或者，）
     *
     * @return desc2_split - 描述2分隔符（*或者，）
     */
    public String getDesc2Split() {
        return desc2Split;
    }

    /**
     * 设置描述2分隔符（*或者，）
     *
     * @param desc2Split 描述2分隔符（*或者，）
     */
    public void setDesc2Split(String desc2Split) {
        this.desc2Split = desc2Split;
    }

    /**
     * 获取图片URL
     *
     * @return image_url - 图片URL
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * 设置图片URL
     *
     * @param imageUrl 图片URL
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    /**
     * 获取护理对策
     *
     * @return nurse_plan - 护理对策
     */
    public String getNursePlan() {
        return nursePlan;
    }

    /**
     * 设置护理对策
     *
     * @param nursePlan 护理对策
     */
    public void setNursePlan(String nursePlan) {
        this.nursePlan = nursePlan;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取删除标识:Y删除：N未删除
     *
     * @return del_flag - 删除标识:Y删除：N未删除
     */
    public String getDelFlag() {
        return delFlag;
    }

    /**
     * 设置删除标识:Y删除：N未删除
     *
     * @param delFlag 删除标识:Y删除：N未删除
     */
    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
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
     * 获取序号
     *
     * @return order_id - 序号
     */
    public Integer getOrderId() {
        return orderId;
    }

    /**
     * 设置序号
     *
     * @param orderId 序号
     */
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    @Override
    public String getAppId() {
        return appId;
    }

    @Override
    public void setAppId(String appId) {
        this.appId = appId;
    }

    public List<AiProductResultVO> getAiProductResultList() {
        return aiProductResultList;
    }

    public void setAiProductResultList(List<AiProductResultVO> aiProductResultList) {
        this.aiProductResultList = aiProductResultList;
    }
}