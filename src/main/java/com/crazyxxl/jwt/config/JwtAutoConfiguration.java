package com.crazyxxl.jwt.config;

import com.crazyxxl.jwt.filter.JwtFilter;
import com.crazyxxl.jwt.utils.JwtUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtAutoConfiguration implements BeanFactoryAware {
    private BeanFactory beanFactory;

    @Bean
    @ConditionalOnMissingBean
    public JwtProperties jwtProperties() {
        return new JwtProperties();
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(name = "jwt.enabled", matchIfMissing = true)
    public FilterRegistrationBean JWtFilter(JwtProperties jwtProperties){
        JwtUtils.key = jwtProperties.getJwtKey();
        System.out.println("-----------------------Jwt Info-------------------------");
        System.out.println(jwtProperties.getJwtKey());
        System.out.println(jwtProperties.getUrlPatterns());
        System.out.println(jwtProperties.getAuthFailPath());
        System.out.println(jwtProperties.getHeaderStartTag());
        System.out.println(jwtProperties.getClaimsInfo());
        System.out.println("-----------------------Jwt Info-------------------------");
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new JwtFilter(jwtProperties.getAuthFailPath(),jwtProperties.getHeaderStartTag(), jwtProperties.getClaimsInfo()));
        filterRegistrationBean.addUrlPatterns(jwtProperties.getUrlPatterns());
        return filterRegistrationBean;
    }
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }
}
