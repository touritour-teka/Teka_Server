package com.teka.shared.auth;

import com.teka.application.auth.exception.error.AuthorityMismatchException;
import com.teka.application.auth.port.out.TokenProvider;
import com.teka.application.auth.service.AuthFacade;
import com.teka.domain.admin.Admin;
import com.teka.domain.admin.AdminId;
import com.teka.domain.auth.type.Authority;
import com.teka.domain.user.User;
import com.teka.domain.user.UserId;
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
    private final TokenProvider tokenProvider;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(AuthenticationPrincipal.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        String token = authenticationExtractor.extract(webRequest);
        Authority authority = tokenProvider.getAuthority(token);
        Class<?> parameterType = parameter.getParameterType();

        if (parameterType.equals(AdminId.class) && authority == Authority.ADMIN) {
            Admin admin = authFacade.getAdmin(token);
            return admin.getId();
        } else if (parameterType.equals(UserId.class) && authority == Authority.USER) {
            User user = authFacade.getUser(token);
            return user.getId();
        } else {
            throw new AuthorityMismatchException();
        }
    }
}
