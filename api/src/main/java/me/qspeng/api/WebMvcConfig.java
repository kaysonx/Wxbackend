package me.qspeng.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import me.qspeng.api.advice.UserIdentifyInterceptor;

import java.util.Arrays;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/META-INF/resources/")
                .addResourceLocations("file:/Users/qspeng/wx-videos/");
    }

    @Bean
    public UserIdentifyInterceptor getIdentifyInterceptor() {
        return new UserIdentifyInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getIdentifyInterceptor())
                    .addPathPatterns("/user/**")
                    .excludePathPatterns(Arrays.asList("/user/login", "/user/register"));
    }
}
