package com.wu.demo.fileupload.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Classname MyPicConfig
 * @Description TODO    新增加一个类用来添加虚拟路径映射
 * @Date 2020/4/16 17:07
 * @Created by wangzhan
 */

@Configuration
public class MyPicConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/img/**").addResourceLocations("file:D:/IDEA/save_date/FileUploadDemo-master/src/main/resources/static/img/");
    }
}
