package com.teka.adapter.chatroom.in.web;

import com.teka.adapter.chatroom.in.web.dto.request.ChangeUserTypeRequest;
import com.teka.adapter.chatroom.in.web.dto.request.CreateChatRoomRequest;
import com.teka.adapter.chatroom.in.web.dto.request.RegisterUserRequest;
import com.teka.adapter.chatroom.in.web.dto.response.ChatRoomResponse;
import com.teka.adapter.chatroom.in.web.dto.response.ChatRoomSimpleResponse;
import com.teka.application.chatroom.port.in.*;
import com.teka.application.chatroom.port.in.command.RegisterUserCommand;
import com.teka.domain.admin.AdminId;
import com.teka.domain.chatroom.ChatRoomId;
import com.teka.domain.chatroom.type.ChatRoomStatus;
import com.teka.domain.user.UserId;
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
    private final QueryChatRoomUseCase queryChatRoomUseCase;
    private final CloseChatRoomUseCase closeChatRoomUseCase;
    private final RegisterUserUseCase registerUserUseCase;
    private final DeleteUserUseCase deleteUserUseCase;
    private final ChangeUserTypeUseCase changeUserTypeUseCase;

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
    public ResponseEntity<CommonResponse<List<ChatRoomSimpleResponse>>> queryAll(
            @AuthenticationPrincipal AdminId ignoredAdminId,
            @RequestParam(name = "status") List<ChatRoomStatus> statusList
    ) {
        List<ChatRoomSimpleResponse> response = queryAllChatRoomUseCase.execute(statusList).stream()
                .map(ChatRoomSimpleResponse::from)
                .toList();
        return ResponseEntity
                .ok()
                .body(CommonResponse.ok(response));
    }

    @GetMapping("/{chatRoomId}")
    public ResponseEntity<CommonResponse<ChatRoomResponse>> queryById(
            @AuthenticationPrincipal AdminId ignoredAdminId,
            @PathVariable(name = "chatRoomId") Long chatRoomId
    ) {
        ChatRoomResponse response = ChatRoomResponse.from(queryChatRoomUseCase.execute(new ChatRoomId(chatRoomId)));
        return ResponseEntity
                .ok()
                .body(CommonResponse.ok(response));
    }

    @PatchMapping("/{chatRoomId}/close")
    public ResponseEntity<Void> closeChatRoom(
            @AuthenticationPrincipal AdminId ignoredAdminId,
            @PathVariable(name = "chatRoomId") Long chatRoomId
    ) {
        closeChatRoomUseCase.execute(new ChatRoomId(chatRoomId));
        return ResponseEntity
                .noContent()
                .build();
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

    @DeleteMapping("/users")
    public ResponseEntity<Void> deleteUser(
            @AuthenticationPrincipal AdminId ignoredAdminId,
            @RequestBody List<Long> request
    ) {
        deleteUserUseCase.execute(request.stream().map(UserId::new).toList());
        return ResponseEntity
                .noContent()
                .build();
    }

    @PatchMapping("/users")
    public ResponseEntity<Void> changeUserType(
            @AuthenticationPrincipal AdminId ignoredAdminId,
            @RequestBody @Valid List<ChangeUserTypeRequest> request
    ) {
        changeUserTypeUseCase.execute(request.stream().map(ChangeUserTypeRequest::toCommand).toList());
        return ResponseEntity
                .noContent()
                .build();
    }
}
