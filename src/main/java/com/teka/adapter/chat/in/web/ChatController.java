package com.teka.adapter.chat.in.web;

import com.teka.adapter.chat.in.web.dto.request.ChatRequest;
import com.teka.application.chat.port.in.ChatMessageUseCase;
import com.teka.domain.user.UserId;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/chat")
@RestController
public class ChatController {

    private final ChatMessageUseCase chatMessageUseCase;

    @PostMapping("/room/{chatroom-uuid}")
    public void chatMessage(
            @AuthenticationPrincipal UserId userId,
            @PathVariable(name = "chatroom-uuid") String chatRoomUuid,
            @RequestBody @Valid ChatRequest request
    ) {
        chatMessageUseCase.execute(userId, chatRoomUuid, request.toCommand());
    }
}
