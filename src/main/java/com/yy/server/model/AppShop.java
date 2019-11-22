package com.yy.server.model;

import javax.persistence.*;
import java.util.Date;

@Table(name = "app_shop")
public class AppShop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 店铺唯一标识
     */
    private String zid;

    /**
     * 店铺名称
     */
    @Column(name = "shop_name")
    private String shopName;

    /**
     * 店铺类型:1自营；2加盟；3其它
     */
    @Column(name = "shop_type")
    private String shopType;

    /**
     * 店铺地址
     */
    @Column(name = "shop_address")
    private String shopAddress;

    /**
     * 店铺营业时间
     */
    @Column(name = "shop_hours")
    private String shopHours;

    /**
     * 店铺电话
     */
    private String telephone;

    /**
     * 店铺描述
     */
    @Column(name = "shop_desc")
    private String shopDesc;

    /**
     * 店铺关键字
     */
    @Column(name = "shop_keywords")
    private String shopKeywords;

    /**
     * 店铺状态:0,关闭；1营业；2暂停
     */
    @Column(name = "shop_status")
    private Byte shopStatus;

    /**
     * 地区ID
     */
    @Column(name = "area_id")
    private Integer areaId;

    /**
     * 百度经度坐标
     */
    @Column(name = "baidu_longitude")
    private String baiduLongitude;

    /**
     * 百度纬度坐标
     */
    @Column(name = "baidu_latitude")
    private String baiduLatitude;

    /**
     * 高德经度坐标
     */
    @Column(name = "gaode_longitude")
    private String gaodeLongitude;

    /**
     * 高德纬度坐标
     */
    @Column(name = "gaode_latitude")
    private String gaodeLatitude;

    /**
     * 百度地图位置
     */
    @Column(name = "baidu_point")
    private String baiduPoint;

    /**
     * 高德地图位置
     */
    @Column(name = "gaode_point")
    private String gaodePoint;

    /**
     * 公司id
     */
    @Column(name = "company_id")
    private Integer companyId;

    /**
     * 注册验证码
     */
    @Column(name = "sms_reg_code")
    private String smsRegCode;

    /**
     * 短信验证码
     */
    @Column(name = "sms_validation_code")
    private String smsValidationCode;

    /**
     * 必填邀请码
     */
    @Column(name = "must_invite")
    private Boolean mustInvite;

    /**
     * 邮件签名
     */
    @Column(name = "mail_title")
    private String mailTitle;

    /**
     * 理财显示
     */
    @Column(name = "show_financing")
    private Boolean showFinancing;

    /**
     * 自动激活显示
     */
    @Column(name = "show_auto_invest")
    private Boolean showAutoInvest;

    /**
     * 状态：1有效；0无效
     */
    private Boolean state;

    /**
     * 协议id
     */
    @Column(name = "protocl_zid")
    private String protoclZid;

    /**
     * 下载页
     */
    @Column(name = "download_page")
    private String downloadPage;

    @Column(name = "created_time")
    private Date createdTime;

    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 创建人
     */
    @Column(name = "created_user")
    private String createdUser;

    /**
     * 修改人
     */
    @Column(name = "updated_user")
    private String updatedUser;

    /**
     * 二维码1标题
     */
    @Column(name = "qrcode1_title_url")
    private String qrcode1TitleUrl;

    /**
     * 二维码1url
     */
    @Column(name = "qrcode1_image_url")
    private String qrcode1ImageUrl;

    /**
     * 二维码2标题
     */
    @Column(name = "qrcode2_title_url")
    private String qrcode2TitleUrl;

    /**
     * 二维码2URL
     */
    @Column(name = "qrcode2_image_url")
    private String qrcode2ImageUrl;

    /**
     * 二维码扩展字段标题
     */
    @Column(name = "ext_code_title_url")
    private String extCodeTitleUrl;

    /**
     * 二维码扩展字段URL
     */
    @Column(name = "ext_code_image_url")
    private String extCodeImageUrl;

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
     * 获取店铺唯一标识
     *
     * @return zid - 店铺唯一标识
     */
    public String getZid() {
        return zid;
    }

    /**
     * 设置店铺唯一标识
     *
     * @param zid 店铺唯一标识
     */
    public void setZid(String zid) {
        this.zid = zid;
    }

    /**
     * 获取店铺名称
     *
     * @return shop_name - 店铺名称
     */
    public String getShopName() {
        return shopName;
    }

    /**
     * 设置店铺名称
     *
     * @param shopName 店铺名称
     */
    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    /**
     * 获取店铺类型:1自营；2加盟；3其它
     *
     * @return shop_type - 店铺类型:1自营；2加盟；3其它
     */
    public String getShopType() {
        return shopType;
    }

    /**
     * 设置店铺类型:1自营；2加盟；3其它
     *
     * @param shopType 店铺类型:1自营；2加盟；3其它
     */
    public void setShopType(String shopType) {
        this.shopType = shopType;
    }

    /**
     * 获取店铺地址
     *
     * @return shop_address - 店铺地址
     */
    public String getShopAddress() {
        return shopAddress;
    }

    /**
     * 设置店铺地址
     *
     * @param shopAddress 店铺地址
     */
    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }

    /**
     * 获取店铺营业时间
     *
     * @return shop_hours - 店铺营业时间
     */
    public String getShopHours() {
        return shopHours;
    }

    /**
     * 设置店铺营业时间
     *
     * @param shopHours 店铺营业时间
     */
    public void setShopHours(String shopHours) {
        this.shopHours = shopHours;
    }

    /**
     * 获取店铺电话
     *
     * @return telephone - 店铺电话
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * 设置店铺电话
     *
     * @param telephone 店铺电话
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    /**
     * 获取店铺描述
     *
     * @return shop_desc - 店铺描述
     */
    public String getShopDesc() {
        return shopDesc;
    }

    /**
     * 设置店铺描述
     *
     * @param shopDesc 店铺描述
     */
    public void setShopDesc(String shopDesc) {
        this.shopDesc = shopDesc;
    }

    /**
     * 获取店铺关键字
     *
     * @return shop_keywords - 店铺关键字
     */
    public String getShopKeywords() {
        return shopKeywords;
    }

    /**
     * 设置店铺关键字
     *
     * @param shopKeywords 店铺关键字
     */
    public void setShopKeywords(String shopKeywords) {
        this.shopKeywords = shopKeywords;
    }

    /**
     * 获取店铺状态:0,关闭；1营业；2暂停
     *
     * @return shop_status - 店铺状态:0,关闭；1营业；2暂停
     */
    public Byte getShopStatus() {
        return shopStatus;
    }

    /**
     * 设置店铺状态:0,关闭；1营业；2暂停
     *
     * @param shopStatus 店铺状态:0,关闭；1营业；2暂停
     */
    public void setShopStatus(Byte shopStatus) {
        this.shopStatus = shopStatus;
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
     * 获取百度经度坐标
     *
     * @return baidu_longitude - 百度经度坐标
     */
    public String getBaiduLongitude() {
        return baiduLongitude;
    }

    /**
     * 设置百度经度坐标
     *
     * @param baiduLongitude 百度经度坐标
     */
    public void setBaiduLongitude(String baiduLongitude) {
        this.baiduLongitude = baiduLongitude;
    }

    /**
     * 获取百度纬度坐标
     *
     * @return baidu_latitude - 百度纬度坐标
     */
    public String getBaiduLatitude() {
        return baiduLatitude;
    }

    /**
     * 设置百度纬度坐标
     *
     * @param baiduLatitude 百度纬度坐标
     */
    public void setBaiduLatitude(String baiduLatitude) {
        this.baiduLatitude = baiduLatitude;
    }

    /**
     * 获取高德经度坐标
     *
     * @return gaode_longitude - 高德经度坐标
     */
    public String getGaodeLongitude() {
        return gaodeLongitude;
    }

    /**
     * 设置高德经度坐标
     *
     * @param gaodeLongitude 高德经度坐标
     */
    public void setGaodeLongitude(String gaodeLongitude) {
        this.gaodeLongitude = gaodeLongitude;
    }

    /**
     * 获取高德纬度坐标
     *
     * @return gaode_latitude - 高德纬度坐标
     */
    public String getGaodeLatitude() {
        return gaodeLatitude;
    }

    /**
     * 设置高德纬度坐标
     *
     * @param gaodeLatitude 高德纬度坐标
     */
    public void setGaodeLatitude(String gaodeLatitude) {
        this.gaodeLatitude = gaodeLatitude;
    }

    /**
     * 获取百度地图位置
     *
     * @return baidu_point - 百度地图位置
     */
    public String getBaiduPoint() {
        return baiduPoint;
    }

    /**
     * 设置百度地图位置
     *
     * @param baiduPoint 百度地图位置
     */
    public void setBaiduPoint(String baiduPoint) {
        this.baiduPoint = baiduPoint;
    }

    /**
     * 获取高德地图位置
     *
     * @return gaode_point - 高德地图位置
     */
    public String getGaodePoint() {
        return gaodePoint;
    }

    /**
     * 设置高德地图位置
     *
     * @param gaodePoint 高德地图位置
     */
    public void setGaodePoint(String gaodePoint) {
        this.gaodePoint = gaodePoint;
    }

    /**
     * 获取公司id
     *
     * @return company_id - 公司id
     */
    public Integer getCompanyId() {
        return companyId;
    }

    /**
     * 设置公司id
     *
     * @param companyId 公司id
     */
    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    /**
     * 获取注册验证码
     *
     * @return sms_reg_code - 注册验证码
     */
    public String getSmsRegCode() {
        return smsRegCode;
    }

    /**
     * 设置注册验证码
     *
     * @param smsRegCode 注册验证码
     */
    public void setSmsRegCode(String smsRegCode) {
        this.smsRegCode = smsRegCode;
    }

    /**
     * 获取短信验证码
     *
     * @return sms_validation_code - 短信验证码
     */
    public String getSmsValidationCode() {
        return smsValidationCode;
    }

    /**
     * 设置短信验证码
     *
     * @param smsValidationCode 短信验证码
     */
    public void setSmsValidationCode(String smsValidationCode) {
        this.smsValidationCode = smsValidationCode;
    }

    /**
     * 获取必填邀请码
     *
     * @return must_invite - 必填邀请码
     */
    public Boolean getMustInvite() {
        return mustInvite;
    }

    /**
     * 设置必填邀请码
     *
     * @param mustInvite 必填邀请码
     */
    public void setMustInvite(Boolean mustInvite) {
        this.mustInvite = mustInvite;
    }

    /**
     * 获取邮件签名
     *
     * @return mail_title - 邮件签名
     */
    public String getMailTitle() {
        return mailTitle;
    }

    /**
     * 设置邮件签名
     *
     * @param mailTitle 邮件签名
     */
    public void setMailTitle(String mailTitle) {
        this.mailTitle = mailTitle;
    }

    /**
     * 获取理财显示
     *
     * @return show_financing - 理财显示
     */
    public Boolean getShowFinancing() {
        return showFinancing;
    }

    /**
     * 设置理财显示
     *
     * @param showFinancing 理财显示
     */
    public void setShowFinancing(Boolean showFinancing) {
        this.showFinancing = showFinancing;
    }

    /**
     * 获取自动激活显示
     *
     * @return show_auto_invest - 自动激活显示
     */
    public Boolean getShowAutoInvest() {
        return showAutoInvest;
    }

    /**
     * 设置自动激活显示
     *
     * @param showAutoInvest 自动激活显示
     */
    public void setShowAutoInvest(Boolean showAutoInvest) {
        this.showAutoInvest = showAutoInvest;
    }

    /**
     * 获取状态：1有效；0无效
     *
     * @return state - 状态：1有效；0无效
     */
    public Boolean getState() {
        return state;
    }

    /**
     * 设置状态：1有效；0无效
     *
     * @param state 状态：1有效；0无效
     */
    public void setState(Boolean state) {
        this.state = state;
    }

    /**
     * 获取协议id
     *
     * @return protocl_zid - 协议id
     */
    public String getProtoclZid() {
        return protoclZid;
    }

    /**
     * 设置协议id
     *
     * @param protoclZid 协议id
     */
    public void setProtoclZid(String protoclZid) {
        this.protoclZid = protoclZid;
    }

    /**
     * 获取下载页
     *
     * @return download_page - 下载页
     */
    public String getDownloadPage() {
        return downloadPage;
    }

    /**
     * 设置下载页
     *
     * @param downloadPage 下载页
     */
    public void setDownloadPage(String downloadPage) {
        this.downloadPage = downloadPage;
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
     * @return update_time
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取创建人
     *
     * @return created_user - 创建人
     */
    public String getCreatedUser() {
        return createdUser;
    }

    /**
     * 设置创建人
     *
     * @param createdUser 创建人
     */
    public void setCreatedUser(String createdUser) {
        this.createdUser = createdUser;
    }

    /**
     * 获取修改人
     *
     * @return updated_user - 修改人
     */
    public String getUpdatedUser() {
        return updatedUser;
    }

    /**
     * 设置修改人
     *
     * @param updatedUser 修改人
     */
    public void setUpdatedUser(String updatedUser) {
        this.updatedUser = updatedUser;
    }

    /**
     * 获取二维码1标题
     *
     * @return qrcode1_title_url - 二维码1标题
     */
    public String getQrcode1TitleUrl() {
        return qrcode1TitleUrl;
    }

    /**
     * 设置二维码1标题
     *
     * @param qrcode1TitleUrl 二维码1标题
     */
    public void setQrcode1TitleUrl(String qrcode1TitleUrl) {
        this.qrcode1TitleUrl = qrcode1TitleUrl;
    }

    /**
     * 获取二维码1url
     *
     * @return qrcode1_image_url - 二维码1url
     */
    public String getQrcode1ImageUrl() {
        return qrcode1ImageUrl;
    }

    /**
     * 设置二维码1url
     *
     * @param qrcode1ImageUrl 二维码1url
     */
    public void setQrcode1ImageUrl(String qrcode1ImageUrl) {
        this.qrcode1ImageUrl = qrcode1ImageUrl;
    }

    /**
     * 获取二维码2标题
     *
     * @return qrcode2_title_url - 二维码2标题
     */
    public String getQrcode2TitleUrl() {
        return qrcode2TitleUrl;
    }

    /**
     * 设置二维码2标题
     *
     * @param qrcode2TitleUrl 二维码2标题
     */
    public void setQrcode2TitleUrl(String qrcode2TitleUrl) {
        this.qrcode2TitleUrl = qrcode2TitleUrl;
    }

    /**
     * 获取二维码2URL
     *
     * @return qrcode2_image_url - 二维码2URL
     */
    public String getQrcode2ImageUrl() {
        return qrcode2ImageUrl;
    }

    /**
     * 设置二维码2URL
     *
     * @param qrcode2ImageUrl 二维码2URL
     */
    public void setQrcode2ImageUrl(String qrcode2ImageUrl) {
        this.qrcode2ImageUrl = qrcode2ImageUrl;
    }

    /**
     * 获取二维码扩展字段标题
     *
     * @return ext_code_title_url - 二维码扩展字段标题
     */
    public String getExtCodeTitleUrl() {
        return extCodeTitleUrl;
    }

    /**
     * 设置二维码扩展字段标题
     *
     * @param extCodeTitleUrl 二维码扩展字段标题
     */
    public void setExtCodeTitleUrl(String extCodeTitleUrl) {
        this.extCodeTitleUrl = extCodeTitleUrl;
    }

    /**
     * 获取二维码扩展字段URL
     *
     * @return ext_code_image_url - 二维码扩展字段URL
     */
    public String getExtCodeImageUrl() {
        return extCodeImageUrl;
    }

    /**
     * 设置二维码扩展字段URL
     *
     * @param extCodeImageUrl 二维码扩展字段URL
     */
    public void setExtCodeImageUrl(String extCodeImageUrl) {
        this.extCodeImageUrl = extCodeImageUrl;
    }
}