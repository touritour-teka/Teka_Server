package com.teka.adapter.auth.in.web;

import com.teka.adapter.auth.in.web.dto.request.LogInAdminRequest;
import com.teka.adapter.auth.in.web.dto.response.TokenResponse;
import com.teka.application.auth.port.dto.TokenDto;
import com.teka.application.auth.port.in.LogInAdminUseCase;
import com.teka.shared.response.CommonResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/auth")
@RestController
public class AuthController {

    private final LogInAdminUseCase logInAdminUseCase;

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
}
