package com.teka.adapter.auth.in.web;

import com.teka.adapter.auth.in.web.dto.request.LogInAdminRequest;
import com.teka.adapter.auth.in.web.dto.request.LogInUserRequest;
import com.teka.adapter.auth.in.web.dto.response.TokenResponse;
import com.teka.application.auth.port.dto.TokenDto;
import com.teka.application.auth.port.in.*;
import com.teka.domain.admin.AdminId;
import com.teka.domain.user.UserId;
import com.teka.shared.response.CommonResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/auth")
@RestController
public class AuthController {

    private final LogInAdminUseCase logInAdminUseCase;
    private final RefreshTokenUseCase refreshTokenUseCase;
    private final LogOutAdminUseCase logOutAdminUseCase;
    private final LogInUserUseCase logInUserUseCase;
    private final LogOutUserUseCase logOutUserUseCase;

    @PostMapping("/admin")
    public ResponseEntity<CommonResponse<TokenResponse>> logInAdmin(@RequestBody @Valid LogInAdminRequest request) {
        TokenDto dto = logInAdminUseCase.execute(request.toCommand());
        TokenResponse response = TokenResponse.builder()
                .accessToken(dto.accessToken())
                .refreshToken(dto.refreshToken())
                .build();

        return ResponseEntity
                .ok()
                .body(CommonResponse.ok(response));
    }

    @PostMapping("/user/{chatroom-uuid}")
    public ResponseEntity<CommonResponse<TokenResponse>> logInUser(
            @RequestBody @Valid LogInUserRequest request,
            @PathVariable(name = "chatroom-uuid") String chatRoomUuid
    ) {
        TokenDto dto = logInUserUseCase.execute(chatRoomUuid, request.toCommand());
        TokenResponse response = TokenResponse.builder()
                .accessToken(dto.accessToken())
                .refreshToken(dto.refreshToken())
                .build();

        return ResponseEntity
                .ok()
                .body(CommonResponse.ok(response));
    }

    @PatchMapping
    public ResponseEntity<CommonResponse<TokenResponse>> refreshToken(@RequestHeader("Refresh-Token") @NotBlank String refreshToken) {
        TokenDto dto = refreshTokenUseCase.execute(refreshToken);
        TokenResponse response = TokenResponse.builder()
                .accessToken(dto.accessToken())
                .build();

        return ResponseEntity
                .ok()
                .body(CommonResponse.ok(response));
    }

    @DeleteMapping("/admin")
    public ResponseEntity<Void> logOutAdmin(
            @AuthenticationPrincipal AdminId adminId
    ) {
        logOutAdminUseCase.execute(adminId);
        return ResponseEntity
                .noContent()
                .build();
    }

    @DeleteMapping("/user")
    public ResponseEntity<Void> logOutUser(
            @AuthenticationPrincipal UserId userId
    ) {
        logOutUserUseCase.execute(userId);

        return ResponseEntity
                .noContent()
                .build();
    }
}
