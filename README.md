# Spring AI Ollama 채팅 애플리케이션

이 프로젝트는 Spring Boot와 Spring AI를 사용하여 Ollama AI 모델과 대화할 수 있는 웹 애플리케이션입니다.

## 기능
- Ollama AI 모델과 실시간 채팅
- 서버 전송 이벤트(SSE)를 통한 스트리밍 응답
- 사용자 친화적인 웹 인터페이스

## 기술 스택
- Java 17
- Spring Boot 3.3.2
- Spring AI 1.0.0-M4
- Thymeleaf
- HTML/CSS/JavaScript

## 프로젝트 구조
- `OllamaAiApplication.java`: Spring Boot 애플리케이션의 메인 클래스
- `ChatController.java`: 채팅 기능을 처리하는 컨트롤러
- `chat.html`: 채팅 인터페이스 HTML 템플릿
- `chat-styles.css`: 채팅 인터페이스 스타일시트
- `chat-script.js`: 클라이언트 측 JavaScript 로직

## 설정
1. Ollama AI 모델을 로컬에서 실행하고 있는지 확인하세요.
```shell
ollama run llama3.2
```

2. `application.properties` 또는 `application.yml` 파일에 Ollama 설정을 추가하세요:

```yaml
ai:
  ollama:
    base-url: http://localhost:11434
    chat:
      options:
        model: llama3.2
        temperature: 0.7
```

## 실행 방법
1. 프로젝트를 클론합니다.
2. Maven을 사용하여 의존성을 설치합니다: `mvn install`
3. 애플리케이션을 실행합니다: `mvn spring-boot:run`
4. 웹 브라우저에서 `http://localhost:8080`에 접속합니다.

## 사용 방법
1. 채팅 인터페이스에서 메시지를 입력합니다.
2. '전송' 버튼을 클릭하거나 Enter 키를 누릅니다.
3. AI의 응답을 실시간으로 확인합니다.

## 주의사항
- 이 프로젝트는 Spring AI의 마일스톤 버전(1.0.0-M4)을 사용합니다. 정식 릴리스 버전으로 업데이트할 때 API 변경 사항에 주의하세요.
- Ollama AI 모델의 성능과 응답 시간은 사용 중인 하드웨어에 따라 다를 수 있습니다.