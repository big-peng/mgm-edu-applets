package com.yy.server.model;

import java.io.Serializable;

/**
 * appId 唯一标识
 */
public interface IAppIdModel extends Serializable {

    String getAppId();

    void setAppId(String appId);
}
