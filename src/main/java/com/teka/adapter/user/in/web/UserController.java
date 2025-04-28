package com.teka.adapter.user.in.web;

import com.teka.adapter.user.in.web.dto.request.ChangeLanguageRequest;
import com.teka.adapter.user.in.web.dto.response.UserInfoResponse;
import com.teka.application.user.port.in.ChangeLanguageUseCase;
import com.teka.application.user.port.in.UserInfoUseCase;
import com.teka.domain.user.UserId;
import com.teka.shared.response.CommonResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/users")
@RestController
public class UserController {

    private final ChangeLanguageUseCase changeLanguageUseCase;
    private final UserInfoUseCase userInfoUseCase;

    @GetMapping
    public ResponseEntity<CommonResponse<UserInfoResponse>> getUserInfo(
            @AuthenticationPrincipal UserId userId
    ) {
        UserInfoResponse response = UserInfoResponse.from(userInfoUseCase.execute(userId));

        return ResponseEntity
                .ok()
                .body(CommonResponse.ok(response));
    }

    @PatchMapping
    public ResponseEntity<Void> changeLanguage(
            @AuthenticationPrincipal UserId userId,
            @RequestBody @Valid ChangeLanguageRequest request
    ) {
        changeLanguageUseCase.execute(userId, request.toCommand());
        return ResponseEntity
                .noContent()
                .build();
    }
}
