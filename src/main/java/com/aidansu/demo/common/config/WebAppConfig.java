package com.aidansu.demo.common.config;

import com.aidansu.demo.common.interceptor.VisitorInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * Created by aidan on 17-3-7.
 */

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.aidansu.demo.dao","com.aidansu.demo.service","com.aidansu.demo.controller"})
@Import({DataBaseConfig.class})
public class WebAppConfig extends WebMvcConfigurerAdapter {

    /**
     * 静态资源过滤
     */
    @Override
    public void addResourceHandlers( ResourceHandlerRegistry registry) {
        super.addResourceHandlers(registry);
        registry.addResourceHandler("/img/**").addResourceLocations("/img/");
        registry.addResourceHandler("/css/**").addResourceLocations("/css/");
        registry.addResourceHandler("/js/**").addResourceLocations("/js/");
    }

    /**
     * JSP视图解析器
     */
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        resolver.setExposeContextBeansAsAttributes(true);
        return resolver;
    }

    /**
     * 将对于静态资源的请求转发到Servlet容器的默认处理静态资源的servlet
     * 因为将spring的拦截模式设置为"/"时会对静态资源进行拦截
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    /**
     * 添加拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new VisitorInterceptor()).addPathPatterns("/user-list/**");
    }

}
