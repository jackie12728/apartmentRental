package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Override
    public void addCorsMappings(CorsRegistry registry) {
        // 這裡的 "/**" 是對所有請求路徑設置 CORS
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:5173")  // 允許的前端地址
                .allowedMethods("GET", "POST", "PUT", "DELETE")  // 允許的 HTTP 方法
                .allowedHeaders("*")  // 允許所有標頭
                .allowCredentials(true)  // 允許攜帶 Cookie
                .maxAge(3600);  // 設置預檢請求的有效時間（單位：秒）
    }
}
