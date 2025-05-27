package com.teka.shared.config;

import com.teka.shared.auth.AuthenticationArgumentResolver;
import com.teka.shared.constants.FileConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Paths;
import java.util.List;

@RequiredArgsConstructor
@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final AuthenticationArgumentResolver authenticationArgumentResolver;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(authenticationArgumentResolver);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String path = Paths.get(System.getProperty("user.dir"), FileConstant.UPLOAD_DIR).toUri().toString();
        registry.addResourceHandler("/chat/images/**")
                .addResourceLocations(path);
    }
}
