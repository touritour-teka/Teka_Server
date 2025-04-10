package com.teka.adapter.admin.in.web;

import com.teka.adapter.admin.in.web.dto.request.SignUpAdminRequest;
import com.teka.application.admin.port.in.SignUpAdminUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/admins")
@RestController
public class AdminController {

    private final SignUpAdminUseCase signUpAdminUseCase;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void signUp(@RequestBody @Valid SignUpAdminRequest request) {
        signUpAdminUseCase.execute(request.toCommand());
    }
}
