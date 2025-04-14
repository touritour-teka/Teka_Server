package com.teka.shared.auth;

import com.teka.application.auth.service.AuthFacade;
import com.teka.domain.admin.Admin;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@RequiredArgsConstructor
@Component
public class AuthenticationArgumentResolver implements HandlerMethodArgumentResolver {

    private final AuthenticationExtractor authenticationExtractor;
    private final AuthFacade authFacade;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(AuthenticationPrincipal.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        String token = authenticationExtractor.extract(webRequest);
        Admin admin = authFacade.getAdmin(token);

        return admin.getId();
    }
}
