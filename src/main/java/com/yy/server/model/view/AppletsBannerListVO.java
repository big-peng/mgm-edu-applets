package com.yy.server.model.view;

import lombok.Data;

/**
 * @author ChenXiangpeng
 * @ClassName AppletsBannerListVO
 * @date：2019/11/19
 * @version: V1.0.0
 * @description：
 */
@Data
public class AppletsBannerListVO {
    private Integer id;
    /**
     * banner名称
     */
    private String name;

    /**
     * banner图片url
     */
    private String imgUrl;
    /**
     * 描述
     */
    private String remark;
}
