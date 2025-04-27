package com.teka.adapter.user.in.web;

import com.teka.adapter.user.in.web.dto.request.ChangeLanguageRequest;
import com.teka.application.user.port.in.ChangeLanguageUseCase;
import com.teka.domain.user.UserId;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/users")
@RestController
public class UserController {

    private final ChangeLanguageUseCase changeLanguageUseCase;

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
