package org.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

// Spring 설정 클래스임을 나타내는 어노테이션
@Configuration
public class WebConfig implements WebMvcConfigurer {

    // 정적 리소스 핸들러를 추가하기 위해 WebMvcConfigurer의 메서드를 오버라이드
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")  // "/static/**" 패턴의 URL 요청을 처리
                .addResourceLocations("classpath:/static/")  // 실제 리소스가 위치한 경로 지정
                .setCachePeriod(3600)  // 리소스 캐시 기간을 3600초(1시간)로 설정
                .resourceChain(true)  // 최적화된 리소스 체인 활성화
                .addResolver(new PathResourceResolver());  // 요청된 리소스의 물리적 위치를 확인하는 리졸버 추가
    }
}
