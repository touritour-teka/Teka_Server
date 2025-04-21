package com.teka.adapter.admin.in.web;

import com.teka.adapter.admin.in.web.dto.request.SignUpAdminRequest;
import com.teka.application.admin.port.in.SignUpAdminUseCase;
import com.teka.application.admin.port.in.ValidateAdminUsernameUseCase;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RequiredArgsConstructor
@RequestMapping("/admins")
@RestController
public class AdminController {

    private final SignUpAdminUseCase signUpAdminUseCase;
    private final ValidateAdminUsernameUseCase validateAdminUsernameUseCase;

    @PostMapping
    public ResponseEntity<Long> signUp(@RequestBody @Valid SignUpAdminRequest request) {
        Long id = signUpAdminUseCase.execute(request.toCommand());
        return ResponseEntity
                .created(URI.create("/admins/" + id))
                .build();
    }

    @GetMapping("/validate")
    public ResponseEntity<Void> validateAdminUsername(@RequestParam @NotBlank String username) {
        validateAdminUsernameUseCase.execute(username);
        return ResponseEntity.ok().build();
    }
}
