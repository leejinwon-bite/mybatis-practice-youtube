package com.codingrecipe.board.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private String resourcePath = "/upload/**"; // project 에서 사용할 상대 경로

    private String savePath = "file:///C:/Users/huge4/upload"; // 파일 저장용 절대 경로

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 리소스 핸들러를 추가하여 파일 업로드 경로를 매핑합니다.
        registry.addResourceHandler(resourcePath)
                .addResourceLocations(savePath); // file:/// 로 시작하는 절대 경로
    }
}
