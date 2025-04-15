package com.teka.adapter.auth.in.web;

import com.teka.adapter.auth.in.web.dto.request.LogInAdminRequest;
import com.teka.adapter.auth.in.web.dto.response.TokenResponse;
import com.teka.application.auth.port.dto.TokenDto;
import com.teka.application.auth.port.in.LogInAdminUseCase;
import com.teka.application.auth.port.in.LogOutAdminUseCase;
import com.teka.application.auth.port.in.RefreshTokenUseCase;
import com.teka.domain.admin.AdminId;
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

    @PostMapping
    public ResponseEntity<CommonResponse<TokenResponse>> logIn(@RequestBody @Valid LogInAdminRequest request) {
        TokenDto dto = logInAdminUseCase.execute(request.toCommand());
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

    @DeleteMapping
    public ResponseEntity<Void> logOut(
            @AuthenticationPrincipal AdminId adminId
    ) {
        logOutAdminUseCase.execute(adminId);
        return ResponseEntity
                .noContent()
                .build();
    }
}
