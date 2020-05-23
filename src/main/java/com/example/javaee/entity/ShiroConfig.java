package com.example.javaee.entity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author: Yangfn
 * @Description:
 * @Date: 23:38 2020/5/22
 */
@Configuration
public class ShiroConfig {

    /**
     * 配置Shiro的Web过滤器，拦截浏览器请求并交给SecurityManager处理
     * @return
     */
//    @Bean
//    public ShiroFilterFactoryBean webFilter(){
//
//        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
//
//        //配置拦截链 使用LinkedHashMap,因为LinkedHashMap是有序的，shiro会根据添加的顺序进行拦截
//        // Map<K,V> K指的是拦截的url V值的是该url是否拦截
//        Map<String,String> filterChainMap = new LinkedHashMap<String,String>(16);
//
//        //authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问,先配置anon再配置authc。
//        filterChainMap.put("/login","anon");
//        filterChainMap.put("/**", "authc");
//
//        //设置拦截请求后跳转的URL.
//        shiroFilterFactoryBean.setLoginUrl("/login");
//        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainMap);
//        return shiroFilterFactoryBean;
//    }
}
