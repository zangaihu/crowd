package com.sh.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("/");
        registry.addResourceHandler("/static/**")
                .addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX+"/static/");


    }

    /**
     * 多模块的jsp访问，默认是src/main/webapp，但是多模块的目录只设置yml文件不行
     *
     * @return
     */
    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setViewClass(org.springframework.web.servlet.view.JstlView.class);
        // jsp目录
        resolver.setPrefix("/WEB-INF/jsp/");
        // 后缀
        resolver.setSuffix(".jsp");
        return resolver;
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
;
        registry.addViewController("/admin/to/login/page.html").setViewName("admin-login");
        registry.addViewController("/admin/to/main/page.html").setViewName("admin-main");
        registry.addViewController("/admin/to/add/page.html").setViewName("admin-add");
        registry.addViewController("/admin/to/edit/page.html").setViewName("admin-add");
        registry.addViewController("/role/to/page.html").setViewName("role-page");
        registry.addViewController("/menu/to/page.html").setViewName("menu-page");



        }

    //@Override
    //public void addInterceptors(InterceptorRegistry registry) {
    //
    //  registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**")
    //          .excludePathPatterns("/admin/to/login/page.html")
    //          .excludePathPatterns("/admin/do/login.html")
    //          .excludePathPatterns("/admin/do/logout/page.html");
    //}


}
