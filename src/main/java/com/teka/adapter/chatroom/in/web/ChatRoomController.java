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
    private final DeleteChatRoomUseCase deleteChatRoomUseCase;
    private final RegisterUserUseCase registerUserUseCase;
    private final DeleteUserUseCase deleteUserUseCase;
    private final ChangeUserTypeUseCase changeUserTypeUseCase;
    private final OpenChatRoomUseCase openChatRoomUseCase;

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
            @AuthenticationPrincipal AdminId adminId,
            @RequestParam(name = "status") List<ChatRoomStatus> statusList
    ) {
        List<ChatRoomSimpleResponse> response = queryAllChatRoomUseCase.execute(statusList, adminId).stream()
                .map(ChatRoomSimpleResponse::from)
                .toList();
        return ResponseEntity
                .ok()
                .body(CommonResponse.ok(response));
    }

    @GetMapping("/{chatRoomId}")
    public ResponseEntity<CommonResponse<ChatRoomResponse>> queryById(
            @AuthenticationPrincipal AdminId adminId,
            @PathVariable(name = "chatRoomId") Long chatRoomId
    ) {
        ChatRoomResponse response = ChatRoomResponse.from(queryChatRoomUseCase.execute(new ChatRoomId(chatRoomId), adminId));
        return ResponseEntity
                .ok()
                .body(CommonResponse.ok(response));
    }

    @PatchMapping("/{chatRoomId}/open")
    public ResponseEntity<Void> openChatRoom(
            @AuthenticationPrincipal AdminId adminId,
            @PathVariable(name = "chatRoomId") Long chatRoomId
    ) {
        openChatRoomUseCase.execute(new ChatRoomId(chatRoomId), adminId);
        return ResponseEntity
                .noContent()
                .build();
    }

    @PatchMapping("/{chatRoomId}/close")
    public ResponseEntity<Void> closeChatRoom(
            @AuthenticationPrincipal AdminId adminId,
            @PathVariable(name = "chatRoomId") Long chatRoomId
    ) {
        closeChatRoomUseCase.execute(new ChatRoomId(chatRoomId), adminId);
        return ResponseEntity
                .noContent()
                .build();
    }

    @DeleteMapping("/{chatRoomId}")
    public ResponseEntity<Void> delete(
            @AuthenticationPrincipal AdminId adminId,
            @PathVariable Long chatRoomId
    ) {
        deleteChatRoomUseCase.execute(new ChatRoomId(chatRoomId), adminId);
        return ResponseEntity
                .noContent()
                .build();
    }

    @PostMapping("/{chatRoomId}/users")
    public ResponseEntity<Void> registerUsers(
            @AuthenticationPrincipal AdminId adminId,
            @PathVariable(name = "chatRoomId") Long chatRoomId,
            @RequestBody @Valid List<RegisterUserRequest> request
    ) {
        List<RegisterUserCommand> commandList = request.stream()
                .map(RegisterUserRequest::toCommand)
                .toList();
        registerUserUseCase.execute(commandList, new ChatRoomId(chatRoomId), adminId);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

    @DeleteMapping("/{chatRoomId}/users")
    public ResponseEntity<Void> deleteUser(
            @AuthenticationPrincipal AdminId adminId,
            @PathVariable(name = "chatRoomId") Long chatRoomId,
            @RequestBody List<Long> request
    ) {
        deleteUserUseCase.execute(request.stream().map(UserId::new).toList(), new ChatRoomId(chatRoomId), adminId);
        return ResponseEntity
                .noContent()
                .build();
    }

    @PatchMapping("/{chatRoomId}/users")
    public ResponseEntity<Void> changeUserType(
            @AuthenticationPrincipal AdminId adminId,
            @PathVariable(name = "chatRoomId") Long chatRoomId,
            @RequestBody @Valid List<ChangeUserTypeRequest> request
    ) {
        changeUserTypeUseCase.execute(request.stream().map(ChangeUserTypeRequest::toCommand).toList(), new ChatRoomId(chatRoomId), adminId);
        return ResponseEntity
                .noContent()
                .build();
    }
}
