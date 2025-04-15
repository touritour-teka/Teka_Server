package com.teka.adapter.chatroom.in.web;

import com.teka.adapter.chatroom.in.web.dto.request.CreateChatRoomRequest;
import com.teka.application.chatroom.port.in.CreateChatRoomUseCase;
import com.teka.domain.admin.AdminId;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RequiredArgsConstructor
@RequestMapping("/chatrooms")
@RestController
public class ChatRoomController {

    private final CreateChatRoomUseCase createChatRoomUseCase;

    @PostMapping
    public ResponseEntity<Long> create(
            @AuthenticationPrincipal AdminId adminId,
            @RequestBody @Valid CreateChatRoomRequest request
    ) {
        Long id = createChatRoomUseCase.execute(request.toCommand(), adminId);
        return ResponseEntity
                .created(URI.create("/chatrooms/" + id))
                .build();
    }
}
