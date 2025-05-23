package com.teka.adapter.chat.in.web;

import com.teka.adapter.chat.in.web.dto.request.ChatRequest;
import com.teka.adapter.chat.in.web.dto.response.ChatResponse;
import com.teka.application.auth.port.out.TokenProvider;
import com.teka.application.auth.service.AuthFacade;
import com.teka.application.chat.port.in.ChatMessageUseCase;
import com.teka.application.chat.port.in.QueryChatUseCase;
import com.teka.application.chat.service.ChatSubValidationService;
import com.teka.domain.auth.type.Authority;
import com.teka.domain.user.UserId;
import com.teka.shared.response.CommonResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/chat")
@Controller
public class ChatController {

    private final ChatSubValidationService chatSubValidationService;
    private final ChatMessageUseCase chatMessageUseCase;
    private final QueryChatUseCase queryChatUseCase;
    private final TokenProvider tokenProvider;
    private final AuthFacade authFacade;

    @SubscribeMapping("/chat/room/{chatroom-uuid}")
    public String subscribe(
            @Header("Authorization") String token,
            @DestinationVariable(value = "chatroom-uuid") String chatRoomUuid
    ) {
        token = token.replace("Bearer ", "");
        if (tokenProvider.getAuthority(token) == Authority.USER) {
            UserId userId = authFacade.getUser(token).getId();
            chatSubValidationService.execute(userId, chatRoomUuid);
            return "success";
        }
        return "fail";
    }

    @ResponseBody
    @PostMapping("/room/{chatroom-uuid}")
    public void chatMessage(
            @AuthenticationPrincipal UserId userId,
            @PathVariable(name = "chatroom-uuid") String chatRoomUuid,
            @RequestBody @Valid ChatRequest request
    ) {
        chatMessageUseCase.execute(userId, chatRoomUuid, request.toCommand());
    }

    @ResponseBody
    @GetMapping("/room/{chatroom-uuid}")
    public ResponseEntity<CommonResponse<List<ChatResponse>>> queryMessages(
            @AuthenticationPrincipal UserId userId,
            @PathVariable(name = "chatroom-uuid") String chatRoomUuid,
            @RequestParam(name = "cursor", required = false) Long cursor,
            @RequestParam(name = "size", defaultValue = "30") int size
    ) {
        List<ChatResponse> responseList = queryChatUseCase.execute(userId, chatRoomUuid, cursor, size)
                .stream()
                .map(dto -> ChatResponse.from(dto, null))
                .toList();

        return ResponseEntity
                .ok()
                .body(CommonResponse.ok(responseList));
    }
}
