package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.theokanning.openai.service.OpenAiService;
import com.theokanning.openai.completion.chat.ChatCompletionChoice;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.completion.chat.ChatMessageRole;

@RestController
public class ChatCompletionController {

    @Value("${openai.model}")
    private String model;

    @Value("${openai.api.key}")
    private String openaiApiKey;

    @PostMapping("/ai-chat")
    public String chat(@RequestBody String prompt) {
        OpenAiService service = new OpenAiService(openaiApiKey);

        final List<ChatMessage> messages = new ArrayList<>();
        final ChatMessage systemMessage = new ChatMessage(
                ChatMessageRole.USER.value(), prompt);
        messages.add(systemMessage);

        ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest
                .builder()
                .model(model)
                .messages(messages)
                .maxTokens(250)
                .build();

        List<ChatCompletionChoice> choices = service
                .createChatCompletion(chatCompletionRequest).getChoices();

        if (choices == null || choices.isEmpty()) {
            return "No response";
        }

        return choices.get(0).getMessage().getContent();
    }

}
