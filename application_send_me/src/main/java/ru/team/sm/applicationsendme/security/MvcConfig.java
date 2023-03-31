package ru.team.sm.applicationsendme.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Value("${upload.path}")
    private String uploadPath;

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/user/profile").setViewName("profile");
        registry.addViewController("/admin/profile").setViewName("profile");
        registry.addViewController("/user/users/all").setViewName("users");
        registry.addViewController("/user/*").setViewName("oneUser");
        registry.addViewController("/user/single/chat/*").setViewName("singleChatUser");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/images/**")
                .addResourceLocations(uploadPath);
    }
}
