package com.teka.adapter.chatroom.in.web;

import com.teka.adapter.chatroom.in.web.dto.request.CreateChatRoomRequest;
import com.teka.adapter.chatroom.in.web.dto.response.ChatRoomResponse;
import com.teka.application.chatroom.port.in.CreateChatRoomUseCase;
import com.teka.application.chatroom.port.in.QueryAllChatRoomUseCase;
import com.teka.domain.admin.AdminId;
import com.teka.domain.chatroom.type.ChatRoomStatus;
import com.teka.shared.response.CommonResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/chatrooms")
@RestController
public class ChatRoomController {

    private final CreateChatRoomUseCase createChatRoomUseCase;
    private final QueryAllChatRoomUseCase queryAllChatRoomUseCase;

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

    @GetMapping
    public ResponseEntity<CommonResponse<List<ChatRoomResponse>>> queryAll(
            @AuthenticationPrincipal AdminId ignoredAdminId,
            @RequestParam(name = "status") List<ChatRoomStatus> statusList
    ) {
        List<ChatRoomResponse> response = queryAllChatRoomUseCase.execute(statusList).stream()
                .map(ChatRoomResponse::from)
                .toList();

        return ResponseEntity
                .ok()
                .body(CommonResponse.ok(response));
    }
}
