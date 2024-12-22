package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Spring Boot 애플리케이션의 주 진입점을 나타내는 어노테이션
// @Configuration, @EnableAutoConfiguration, @ComponentScan을 포함합니다.
@SpringBootApplication
public class OllamaAiApplication {

    // 애플리케이션의 메인 메서드
    public static void main(String[] args) {
        // Spring 애플리케이션을 실행합니다.
        // OllamaAiApplication 클래스를 구성 클래스로 사용하고,
        // 명령줄 인수를 전달합니다.
        SpringApplication.run(OllamaAiApplication.class, args);
    }
}
