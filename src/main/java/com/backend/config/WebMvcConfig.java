package com.backend.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Spring MVC配置类
 * 替代spring-mvc.xml配置
 */
@Configuration
@ComponentScan(basePackages = "com.backend",
    useDefaultFilters = false,
    includeFilters = {
        @ComponentScan.Filter(type = org.springframework.context.annotation.FilterType.ANNOTATION,
            classes = {org.springframework.stereotype.Controller.class,
                      org.springframework.web.bind.annotation.RestController.class,
                      org.springframework.context.annotation.Configuration.class})
    })
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * 配置静态资源处理
     * 对应spring-mvc.xml中的<mvc:default-servlet-handler/>
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable("default");
    }

    /**
     * 配置资源处理器
     * 对应spring-mvc.xml中的<mvc:resources>配置
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 静态资源映射
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/css/**")
                .addResourceLocations("classpath:/static/css/");
        registry.addResourceHandler("/js/**")
                .addResourceLocations("classpath:/static/js/");
        registry.addResourceHandler("/images/**")
                .addResourceLocations("classpath:/static/images/");
    }

    /**
     * 配置文件上传解析器
     * 对应spring-mvc.xml中的<bean id="multipartResolver" class="org.springframework.web.multipart.support.StandardServletMultipartResolver"/>
     * 
     * 注意：文件上传大小限制已在application.yml中通过spring.servlet.multipart配置
     */
    @org.springframework.context.annotation.Bean
    public StandardServletMultipartResolver multipartResolver() {
        return new StandardServletMultipartResolver();
    }
}