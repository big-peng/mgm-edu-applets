/*
 * Copyright (c) 2017 <l_iupeiyu@qq.com> All rights reserved.
 */

package com.yy.server.filter;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class AdminFormAuthenticationFilter extends FormAuthenticationFilter {

    @Override
    public void setLoginUrl(String loginUrl) {
        super.setLoginUrl("/customer/accounterr");
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request,
                                     ServletResponse response) throws Exception {
        Subject subject = getSubject(request, response);
        if (!subject.isAuthenticated() && !subject.isRemembered()) {
            return true;
        }

//        if (subject.getPrincipal() == null) {//表示没有登录，重定向到登录页面
//            saveRequest(request);
//            WebUtils.issueRedirect(request, response, LOGIN_URL);
//        } else {
//            if (StringUtils.hasText(UNAUTHORIZED_URL)) {//如果有未授权页面跳转过去
//                WebUtils.issueRedirect(request, response, UNAUTHORIZED_URL);
//            } else {//否则返回401未授权状态码
//                WebUtils.toHttp(response).sendError(HttpServletResponse.SC_UNAUTHORIZED);
//            }
//        }
        if(subject != null){
            return true;
        }
        return false;
    }

}
