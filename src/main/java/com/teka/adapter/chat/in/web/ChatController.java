package com.teka.adapter.chat.in.web;

import com.teka.adapter.chat.in.web.dto.request.ChatRequest;
import com.teka.adapter.chat.in.web.dto.response.ChatResponse;
import com.teka.application.auth.port.out.TokenProvider;
import com.teka.application.auth.service.AuthFacade;
import com.teka.application.chat.port.in.ChatSubValidationUseCase;
import com.teka.application.chat.port.in.QueryChatUseCase;
import com.teka.application.chat.port.in.SendChatUseCase;
import com.teka.application.chat.port.in.UploadImageUseCase;
import com.teka.domain.auth.type.Authority;
import com.teka.domain.user.UserId;
import com.teka.shared.config.properties.JwtProperties;
import com.teka.shared.constants.FileConstant;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/chat")
@Controller
public class ChatController {

    private final ChatSubValidationUseCase chatSubValidationUseCase;
    private final SendChatUseCase sendChatUseCase;
    private final QueryChatUseCase queryChatUseCase;
    private final TokenProvider tokenProvider;
    private final AuthFacade authFacade;
    private final JwtProperties jwtProperties;
    private final UploadImageUseCase uploadImageUseCase;

    // TODO
    //  - JWT 인증 방식 간소화 필요(Principal 이용)
    @SubscribeMapping("/chat/{chatroom-uuid}/{language}")
    public String subscribe(
            @Header("Authorization") String token,
            @DestinationVariable(value = "chatroom-uuid") String chatRoomUuid
    ) {
        token = token.replace(jwtProperties.getPrefix(), "").trim();
        if (tokenProvider.getAuthority(token) == Authority.USER) {
            UserId userId = authFacade.getUser(token).getId();
            chatSubValidationUseCase.execute(userId, chatRoomUuid);
            return "success";
        }
        return "fail";
    }

    @ResponseBody
    @PostMapping("/{chatroom-uuid}")
    public ResponseEntity<Void> sendChat(
            @AuthenticationPrincipal UserId userId,
            @PathVariable(name = "chatroom-uuid") String chatRoomUuid,
            @RequestBody @Valid ChatRequest request
    ) {
        sendChatUseCase.execute(userId, chatRoomUuid, request.toCommand());

        return ResponseEntity
                .noContent()
                .build();
    }

    @ResponseBody
    @GetMapping("/{chatroom-uuid}")
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

    @ResponseBody
    @PostMapping("/image")
    public ResponseEntity<Void> uploadImage(
            @AuthenticationPrincipal UserId ignoredUserId,
            @RequestPart("image") MultipartFile file
    ) throws IOException {
        String filename = uploadImageUseCase.execute(file);
        return ResponseEntity
                .created(URI.create(FileConstant.UPLOAD_DIR + filename))
                .build();
    }
}
