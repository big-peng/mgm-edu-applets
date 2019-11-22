package com.yy.server.common.shiro;

import com.alibaba.fastjson.JSONObject;
import com.yy.server.common.LoginEnum;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.util.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Map;

public class CustomModularRealmAuthenticator extends ModularRealmAuthenticator {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private Map<String, Object> definedRealms;

    @Override
    protected AuthenticationInfo doMultiRealmAuthentication(Collection<Realm> realms, AuthenticationToken token) {
        return super.doMultiRealmAuthentication(realms, token);
    }

    @Override
    protected AuthenticationInfo doSingleRealmAuthentication(Realm realm, AuthenticationToken token) {
        // 如果该realms不支持(不能验证)当前token
        if (!realm.supports(token)) {
            throw new ShiroException("token错误!");
        }
        AuthenticationInfo info = null;
        try {
            info = realm.getAuthenticationInfo(token);
            if (info == null) {
                throw new ShiroException("token不存在!");
            }
            //logger.info("AuthenticationInfo token={}", JSONObject.toJSONString(info));
        } catch (Exception e) {
            throw e;
        }
        return info;
    }


    @Override
    protected AuthenticationInfo doAuthenticate(AuthenticationToken authenticationToken) throws AuthenticationException {
        this.assertRealmsConfigured();
        Realm realm = null;
        CustomerAuthenticationToken token = (CustomerAuthenticationToken) authenticationToken;
        //判断是否是后台用户
        if (LoginEnum.ADMIN.toString().equals(token.getLoginType())) {
            realm = (Realm) this.definedRealms.get("adminShiroRealm");
        } else if (LoginEnum.DEVICE.toString().equals(token.getLoginType())) {
            realm = (Realm) this.definedRealms.get("deviceInfoShiroRealm");
        } else {
            realm = (Realm) this.definedRealms.get("customShiroRealm");
        }
        return this.doSingleRealmAuthentication(realm, authenticationToken);
    }


    @Override
    protected void assertRealmsConfigured() throws IllegalStateException {
        this.definedRealms = this.getDefinedRealms();
        if (CollectionUtils.isEmpty(this.definedRealms)) {
            throw new ShiroException("值传递错误!");
        }
    }

    public Map<String, Object> getDefinedRealms() {
        return this.definedRealms;
    }

    public void setDefinedRealms(Map<String, Object> definedRealms) {
        this.definedRealms = definedRealms;
    }

}
