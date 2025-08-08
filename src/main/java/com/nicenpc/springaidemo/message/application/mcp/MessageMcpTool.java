package com.nicenpc.springaidemo.message.application.mcp;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class MessageMcpTool {

    private final WebClient webClient = WebClient.create();

    @Tool(name = "send-message", description = "發送訊息")
    public String sendMessage(String message) {
        return webClient
                .post()
                .uri("http://localhost:8080") // 目標 URL
                .bodyValue(new PekoMessage(message)) // 傳送 JSON body
                .retrieve()
                .bodyToMono(String.class)
                .block(); // 同步等待回應
    }
}
