package com.teka.adapter.chat.in.web;

import com.teka.adapter.chat.in.web.dto.request.ChatRequest;
import com.teka.adapter.chat.in.web.dto.response.ChatResponse;
import com.teka.application.chat.port.in.ChatMessageUseCase;
import com.teka.application.chat.port.in.QueryChatUseCase;
import com.teka.domain.user.UserId;
import com.teka.shared.response.CommonResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/chat")
@RestController
public class ChatController {

    private final ChatMessageUseCase chatMessageUseCase;
    private final QueryChatUseCase queryChatUseCase;

    @PostMapping("/room/{chatroom-uuid}")
    public void chatMessage(
            @AuthenticationPrincipal UserId userId,
            @PathVariable(name = "chatroom-uuid") String chatRoomUuid,
            @RequestBody @Valid ChatRequest request
    ) {
        chatMessageUseCase.execute(userId, chatRoomUuid, request.toCommand());
    }

    @GetMapping("/room/{chatroom-uuid}")
    public ResponseEntity<CommonResponse<List<ChatResponse>>> queryMessages(
            @AuthenticationPrincipal UserId userId,
            @PathVariable(name = "chatroom-uuid") String chatRoomUuid,
            @RequestParam(name = "cursor", required = false) Long cursor,
            @RequestParam(name = "size", defaultValue = "30") int size
    ) {
        List<ChatResponse> responseList = queryChatUseCase.execute(userId, chatRoomUuid, cursor, size)
                .stream()
                .map(ChatResponse::from)
                .toList();

        return ResponseEntity
                .ok()
                .body(CommonResponse.ok(responseList));
    }
}
