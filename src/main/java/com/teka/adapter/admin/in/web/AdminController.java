package com.teka.adapter.admin.in.web;

import com.teka.adapter.admin.in.web.dto.request.SignUpAdminRequest;
import com.teka.application.admin.port.in.SignUpAdminUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RequiredArgsConstructor
@RequestMapping("/admins")
@RestController
public class AdminController {

    private final SignUpAdminUseCase signUpAdminUseCase;

    @PostMapping
    public ResponseEntity<Long> signUp(@RequestBody @Valid SignUpAdminRequest request) {
        Long id = signUpAdminUseCase.execute(request.toCommand());
        return ResponseEntity
                .created(URI.create("/admins/" + id))
                .build();
    }
}
