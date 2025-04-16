package com.teka.adapter.chatroom.in.web;

import com.teka.adapter.chatroom.in.web.dto.request.CreateChatRoomRequest;
import com.teka.adapter.chatroom.in.web.dto.request.RegisterUserRequest;
import com.teka.adapter.chatroom.in.web.dto.response.ChatRoomResponse;
import com.teka.application.chatroom.port.in.CreateChatRoomUseCase;
import com.teka.application.chatroom.port.in.QueryAllChatRoomUseCase;
import com.teka.application.chatroom.port.in.RegisterUserUseCase;
import com.teka.application.chatroom.port.in.command.RegisterUserCommand;
import com.teka.domain.admin.AdminId;
import com.teka.domain.chatroom.ChatRoomId;
import com.teka.domain.chatroom.type.ChatRoomStatus;
import com.teka.shared.response.CommonResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    private final RegisterUserUseCase registerUserUseCase;

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

    @PostMapping("/{chatRoomId}/users")
    public ResponseEntity<Void> registerUsers(
            @AuthenticationPrincipal AdminId ignoredAdminId,
            @PathVariable(name = "chatRoomId") Long chatRoomId,
            @RequestBody @Valid List<RegisterUserRequest> request
    ) {
        List<RegisterUserCommand> commandList = request.stream()
                .map(RegisterUserRequest::toCommand)
                .toList();
        registerUserUseCase.execute(commandList, new ChatRoomId(chatRoomId));

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }
}
