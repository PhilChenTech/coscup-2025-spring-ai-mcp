package com.nicenpc.springaidemo;

import com.nicenpc.springaidemo.book.application.mcp.BookMcpTool;
import com.nicenpc.springaidemo.message.application.mcp.MessageMcpTool;
import org.springframework.ai.support.ToolCallbacks;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class SpringAiDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringAiDemoApplication.class, args);
    }

    @Bean
    public List<ToolCallback> tools(BookMcpTool bookMcpTool, MessageMcpTool messageMcpTool) {
        return List.of(ToolCallbacks.from(bookMcpTool, messageMcpTool));
    }

}
