package com.wangtao.thymeleaf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * @author : wangtao
 * @date : 2018/1/10 17:31
 * 可以添加指定路径对应的页面
 * 注解配置的不受影响
 */
@Configuration
public class MyWebMvcConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
//        super.addViewControllers(registry);
        registry.addViewController("/bbb").setViewName("test");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/chat").setViewName("chat");
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/action").setViewName("angulartest");
        registry.addViewController("/view1").setViewName("view1");
        registry.addViewController("/view2").setViewName("view2");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }

    @Bean
    public LocaleResolver localeResolver() {
//        SessionLocaleResolver slr = new SessionLocaleResolver();
        // 默认语言
//        slr.setDefaultLocale(Locale.US);

        AcceptHeaderLocaleResolver resolver = new AcceptHeaderLocaleResolver();
        List<Locale> locales = new ArrayList<>(2);
        locales.add(Locale.US);
        locales.add(Locale.CHINA);
        resolver.setSupportedLocales(locales);
        return resolver;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        // 参数名
        lci.setParamName("lang");
        return lci;
    }

}
