package com.yy.server.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author ChenXiangpeng
 * @ClassName AppletsBannerListDTO
 * @date：2019/11/19
 * @version: V1.0.0
 * @description：
 */
@Data
public class AppletsBannerListDTO {
    @NotBlank(message = "AppId不能为空")
    private String appId;
}
