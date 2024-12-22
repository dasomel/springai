package org.example.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.StreamingChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// Spring MVC 컨트롤러 클래스
@Controller
public class ChatController {

    private final StreamingChatModel streamingChatModel;
    // 비동기 작업을 위한 단일 스레드 실행기
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    public ChatController(StreamingChatModel streamingChatModel) {
        this.streamingChatModel = streamingChatModel;
    }

    // 홈 페이지 요청 처리
    @GetMapping("/")
    public String homePage() {
        return "chat";
    }

    // 채팅 페이지 요청 처리
    @GetMapping("/chat")
    public String chatPage() {
        return "chat";
    }

    // 채팅 API 엔드포인트: Server-Sent Events 사용
    @GetMapping(value = "/api/chat", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter chatApi(@RequestParam(required = false) String message) {
        SseEmitter emitter = new SseEmitter(180000L);

        if (message == null || message.isEmpty()) {
            try {
                emitter.send(SseEmitter.event().data("메시지를 입력해주세요."));
                emitter.complete();
            } catch (IOException e) {
                emitter.completeWithError(e);
            }
            return emitter;
        }

        executorService.execute(() -> {
            try {
                Prompt prompt = new Prompt(new UserMessage(message));
                streamingChatModel.stream(prompt).subscribe(
                        response -> {
                            try {
                                emitter.send(SseEmitter.event().data("response:" + response.getResult().getOutput().getContent()));
                            } catch (IOException e) {
                                emitter.completeWithError(e);
                            }
                        },
                        emitter::completeWithError,
                        emitter::complete
                );
            } catch (Exception e) {
                emitter.completeWithError(e);
            }
        });

        return emitter;
    }

}
