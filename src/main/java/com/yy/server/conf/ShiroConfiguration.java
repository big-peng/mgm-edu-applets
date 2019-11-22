package com.yy.server.conf;

import com.yy.server.common.redis.RedisCacheManager;
import com.yy.server.common.redis.RedisSessionDAO;
import com.yy.server.common.shiro.*;
import com.yy.server.filter.AdminFormAuthenticationFilter;
import com.yy.server.filter.DeviceInfoFormAuthenticationFilter;
import com.yy.server.filter.KickoutSessionControlFilter;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authc.pam.AuthenticationStrategy;
import org.apache.shiro.authc.pam.FirstSuccessfulStrategy;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.SessionListener;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.Cookie;
import org.apache.shiro.web.servlet.ShiroHttpSession;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.*;


@Configuration
public class ShiroConfiguration {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${shiro.session.kickoutAfter}")
    private boolean kickoutAfter;

    @Value("${shiro.session.kickout-prefix}")
    private String kickoutPrefixStr;

    @Value("${shiro.session.userMaxSession}")
    private int userMaxSession;

    @Bean(name = "redisCacheManager")
    public RedisCacheManager redisCacheManager() {
        logger.debug("ShiroConfiguration.redisCacheManager()");
        return new RedisCacheManager();
    }

    @Bean(name = "redisSessionDAO")
    public RedisSessionDAO redisSessionDAO() {
        logger.debug("ShiroConfiguration.redisSessionDAO()");
        return new RedisSessionDAO();
    }

    @Bean(name = "customSessionListener")
    public CustomSessionListener customSessionListener() {
        logger.debug("ShiroConfiguration.customSessionListener()");
        return new CustomSessionListener();
    }


    @Bean(name = "deviceInfoShiroRealm")
    public DeviceInfoShiroRealm deviceInfoShiroRealm() {
        logger.debug("ShiroConfiguration.deviceInfoShiroRealm()");
        DeviceInfoShiroRealm deviceInfoShiroRealm = new DeviceInfoShiroRealm();
        deviceInfoShiroRealm.setCacheManager(redisCacheManager());
        deviceInfoShiroRealm.setCredentialsMatcher(deviceInfoHashedCredentialsMatcher());
        return deviceInfoShiroRealm;
    }

    @Bean(name = "customShiroRealm")
    public CustomShiroRealm customShiroRealm() {
        logger.debug("ShiroConfiguration.customShiroRealm()");
        CustomShiroRealm customShiroRealm = new CustomShiroRealm();
        customShiroRealm.setCacheManager(redisCacheManager());
        customShiroRealm.setCredentialsMatcher(customHashedCredentialsMatcher());
        return customShiroRealm;
    }

    @Bean(name = "adminShiroRealm")
    public AdminShiroRealm adminShiroRealm() {
        logger.debug("ShiroConfiguration.adminShiroRealm()");
        AdminShiroRealm adminShiroRealm = new AdminShiroRealm();
        adminShiroRealm.setCacheManager(redisCacheManager());
        adminShiroRealm.setCredentialsMatcher(adminHashedCredentialsMatcher());
        return adminShiroRealm;
    }

    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManage() {
        logger.debug("ShiroConfiguration.getDefaultWebSecurityManage()");
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        Map<String, Object> shiroAuthenticatorRealms = new HashMap<>();
        shiroAuthenticatorRealms.put("customShiroRealm", customShiroRealm());
        shiroAuthenticatorRealms.put("adminShiroRealm", adminShiroRealm());
        shiroAuthenticatorRealms.put("deviceInfoShiroRealm", deviceInfoShiroRealm());


        Collection<Realm> shiroAuthorizerRealms = new ArrayList<Realm>();
        shiroAuthorizerRealms.add(adminShiroRealm());
        shiroAuthorizerRealms.add(customShiroRealm());
        shiroAuthorizerRealms.add(deviceInfoShiroRealm());


        CustomModularRealmAuthenticator customModularRealmAuthenticator = new CustomModularRealmAuthenticator();
        customModularRealmAuthenticator.setDefinedRealms(shiroAuthenticatorRealms);
        customModularRealmAuthenticator.setAuthenticationStrategy(authenticationStrategy());
        securityManager.setAuthenticator(customModularRealmAuthenticator);
        securityManager.setRealms(shiroAuthorizerRealms);
        //注入缓存管理器;
        securityManager.setCacheManager(redisCacheManager());
        securityManager.setSessionManager(defaultWebSessionManager());
        return securityManager;
    }


    @Bean(name = "authorizationAttributeSourceAdvisor")
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
        logger.debug("ShiroConfiguration.authorizationAttributeSourceAdvisor()");
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }


    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(DefaultWebSecurityManager securityManager) {
        logger.debug("ShiroConfiguration.shirFilter()");
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 必须设置 SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        shiroFilterFactoryBean.setLoginUrl("/checkLgoin.json");

        //增加自定义过滤
        Map<String, Filter> filters = new HashMap<>();
        //filters.put("vue",new AdviceFilter4Vue());
        filters.put("kickout", kickoutSessionControlFilter());
        //filters.put("custom", new CustomFormAuthenticationFilter());
        filters.put("admin", new AdminFormAuthenticationFilter());
        filters.put("deviceinfo", new DeviceInfoFormAuthenticationFilter());
        shiroFilterFactoryBean.setFilters(filters);
        //拦截器.
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();

        filterChainDefinitionMap.put("/counseling/registration/form/add", "anon");
        filterChainDefinitionMap.put("/customer/deviceLogin.json", "anon");
        filterChainDefinitionMap.put("/console/login.json", "anon");
        filterChainDefinitionMap.put("/console/upload.json", "anon");
        filterChainDefinitionMap.put("/backstage/banner/**", "anon");
        filterChainDefinitionMap.put("/file/upload/image", "anon");
        filterChainDefinitionMap.put("/common/uploadOpionImg.json", "anon");
        filterChainDefinitionMap.put("/common/**", "anon");
        filterChainDefinitionMap.put("/index.html", "anon");
        filterChainDefinitionMap.put("/favicon.ico", "anon");
        filterChainDefinitionMap.put("/static/fonts/**", "anon");//img
        filterChainDefinitionMap.put("/static/css/**", "anon");//css
        filterChainDefinitionMap.put("/static/js/**", "anon");//js
        filterChainDefinitionMap.put("/doc.html", "anon");
        filterChainDefinitionMap.put("/swagger-ui.html", "anon");
        // filterChainDefinitionMap.put("/companyInfo/*", "anon");
        //filterChainDefinitionMap.put("/customer/deviceLogin", "kickout");
        //filterChainDefinitionMap.put("/customer/**", "deviceinfo");
        filterChainDefinitionMap.put("/console/**", "authc");
        filterChainDefinitionMap.put("/**", "anon");
        //未授权界面;
        //shiroFilterFactoryBean.setUnauthorizedUrl("/back/403");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    @Bean(name = "authenticationStrategy")
    public AuthenticationStrategy authenticationStrategy() {
        logger.debug("ShiroConfiguration.authenticationStrategy()");
        return new FirstSuccessfulStrategy();
    }

    @Bean(name = "customHashedCredentialsMatcher")
    public HashedCredentialsMatcher customHashedCredentialsMatcher() {
        logger.debug("ShiroConfiguration.customHashedCredentialsMatcher()");
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        hashedCredentialsMatcher.setHashIterations(3);
        return hashedCredentialsMatcher;
    }


    @Bean(name = "adminHashedCredentialsMatcher")
    public HashedCredentialsMatcher adminHashedCredentialsMatcher() {
        logger.debug("ShiroConfiguration.adminHashedCredentialsMatcher()");
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        hashedCredentialsMatcher.setHashIterations(2);
        return hashedCredentialsMatcher;
    }


    @Bean(name = "deviceInfoHashedCredentialsMatcher")
    public HashedCredentialsMatcher deviceInfoHashedCredentialsMatcher() {
        logger.debug("ShiroConfiguration.deviceInfoHashedCredentialsMatcher()");
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        hashedCredentialsMatcher.setHashIterations(1);
        return hashedCredentialsMatcher;
    }


    @Bean(name = "sessionManager")
    public DefaultWebSessionManager defaultWebSessionManager() {
        logger.debug("ShiroConfiguration.defaultWebSessionManager()");
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        //用户信息必须是序列化格式，要不创建用户信息创建不过去，此坑很大，
        sessionManager.setSessionDAO(redisSessionDAO());//如不想使用REDIS可注释此行
        Collection<SessionListener> sessionListeners = new ArrayList<>();
        sessionListeners.add(customSessionListener());
        sessionManager.setSessionListeners(sessionListeners);
        //单位为毫秒（1秒=1000毫秒） 3600000毫秒为1个小时
        sessionManager.setSessionValidationInterval(3600000 * 24);
        //3600000 milliseconds = 1 hour
        sessionManager.setGlobalSessionTimeout(3600000 * 24);
        //是否删除无效的，默认也是开启
        sessionManager.setDeleteInvalidSessions(true);
        //是否开启 检测，默认开启
        sessionManager.setSessionValidationSchedulerEnabled(true);
        //创建会话Cookie
        Cookie cookie = new SimpleCookie(ShiroHttpSession.DEFAULT_SESSION_ID_NAME);
        cookie.setName("WEBID");
        cookie.setHttpOnly(true);
        sessionManager.setSessionIdCookie(cookie);
        return sessionManager;
    }


    @ConditionalOnMissingBean
    @Bean(name = "defaultAdvisorAutoProxyCreator")
    //@DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
        logger.debug("ShiroConfiguration.getDefaultAdvisorAutoProxyCreator()");
        DefaultAdvisorAutoProxyCreator daap = new DefaultAdvisorAutoProxyCreator();
        daap.setProxyTargetClass(true);
        return daap;
    }


    public KickoutSessionControlFilter kickoutSessionControlFilter() {
        KickoutSessionControlFilter kickoutSessionControlFilter = new KickoutSessionControlFilter();
        kickoutSessionControlFilter.setCacheManager(redisCacheManager(), kickoutPrefixStr);
        kickoutSessionControlFilter.setSessionManager(getDefaultWebSecurityManage());
        kickoutSessionControlFilter.setMaxSession(userMaxSession);
        kickoutSessionControlFilter.setKickoutAfter(kickoutAfter);
        return kickoutSessionControlFilter;
    }

}
