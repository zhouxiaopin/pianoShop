package cn.zcp.bgmange.frame.config.authority;


import cn.zcp.bgmange.frame.factory.FilterChainDefinitionMapBuilder;
import cn.zcp.bgmange.frame.realms.ShiroRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authc.pam.AtLeastOneSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.filter.DelegatingFilterProxy;

import java.util.HashSet;
import java.util.Set;

/**
 * shiro权限框架配置
 */
@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)//等价    <aop:config proxy-target-class="true"/>
public class ShiroConfiguration {


//    @Bean
//    public ShiroRealm jdbcRealm(){
//        ShiroRealm shiroRealm = new ShiroRealm();
//        return shiroRealm;
//    }


    @Bean("securityManager")
    public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager =  new DefaultWebSecurityManager();
//        securityManager.setCacheManager();
//        securityManager.setAuthenticator();
        Set<Realm> realms = new HashSet<>();
        realms.add(jdbcRealm());
        securityManager.setRealms(realms);

        return securityManager;
    }
    //权限认证器
    @Bean("authenticator")
    public ModularRealmAuthenticator modularRealmAuthenticator(){
        ModularRealmAuthenticator modularRealmAuthenticator =  new ModularRealmAuthenticator();
        modularRealmAuthenticator.setAuthenticationStrategy(new AtLeastOneSuccessfulStrategy());
        return modularRealmAuthenticator;
    }

    /**
     * 3. 配置 Realm
        3.1 直接配置实现了 org.apache.shiro.realm.Realm 接口的 bean
     * @return
     */
    @Bean("jdbcRealm")
    public ShiroRealm jdbcRealm(){
        ShiroRealm shiroRealm =  new ShiroRealm();
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");
        hashedCredentialsMatcher.setHashIterations(1024);
        shiroRealm.setCredentialsMatcher(hashedCredentialsMatcher);
        return shiroRealm;
    }

    /**
     *  4. 配置 LifecycleBeanPostProcessor. 可以自定的来调用配置在 Spring IOC 容器中 shiro bean 的生命周期方法.
     */
    @Bean("lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
        LifecycleBeanPostProcessor lifecycleBeanPostProcessor = new LifecycleBeanPostProcessor();
        return lifecycleBeanPostProcessor;
    }

    /**
     * 5. 启用 IOC 容器中使用 shiro 的注解. 但必须在配置了 LifecycleBeanPostProcessor 之后才可以使用.
     * @return
     */
    @Bean()
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setBeanName("lifecycleBeanPostProcessor");
        return defaultAdvisorAutoProxyCreator;
    }
    @Bean()
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Autowired @Qualifier("securityManager") SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();

        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    /**
     *
     6. 配置 ShiroFilter.
     6.1 id 必须和 web.xml 文件中配置的 DelegatingFilterProxy 的 <filter-name> 一致.
     若不一致, 则会抛出: NoSuchBeanDefinitionException. 因为 Shiro 会来 IOC 容器中查找和 <filter-name> 名字对应的 filter bean.
     * @param securityManager
     * @return
     */
    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();


        shiroFilterFactoryBean.setSecurityManager(securityManager);
        shiroFilterFactoryBean.setLoginUrl("/admin/initLogin");
        shiroFilterFactoryBean.setSuccessUrl("/admin/index");
        shiroFilterFactoryBean.setUnauthorizedUrl("/admin/initLogin");

        FilterChainDefinitionMapBuilder filterChainDefinitionMapBuilder = new FilterChainDefinitionMapBuilder();
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMapBuilder.buildFilterChainDefinitionMap());

        return shiroFilterFactoryBean;
    }

    /**
     * 1. 配置  Shiro 的 shiroFilter.
     2. DelegatingFilterProxy 实际上是 Filter 的一个代理对象. 默认情况下, Spring 会到 IOC 容器中查找和
     <filter-name> 对应的 filter bean. 也可以通过 targetBeanName 的初始化参数来配置 filter bean 的 id.
     * @return
     */
    @Bean
    public FilterRegistrationBean filterRegistrationBean(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new DelegatingFilterProxy("shiroFilter"));
        filterRegistrationBean.addInitParameter("targetFilterLifecycle","true");
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
    }
}
