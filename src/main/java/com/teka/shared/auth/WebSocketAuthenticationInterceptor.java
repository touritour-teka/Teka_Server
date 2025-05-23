package com.teka.shared.auth;

import com.teka.application.auth.port.out.TokenProvider;
import com.teka.domain.auth.type.Authority;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@Component
public class WebSocketAuthenticationInterceptor implements ChannelInterceptor {

    private final TokenProvider tokenProvider;
    private final WebSocketAuthenticationExtractor webSocketAuthenticationExtractor;

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
        StompCommand command = accessor.getCommand();

        if (command == StompCommand.CONNECT) {
            String token = webSocketAuthenticationExtractor.extract(accessor);

            String uuid = tokenProvider.getUuid(token);
            Authority authority = tokenProvider.getAuthority(token);
            List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(authority.name()));
            Principal principal = new UsernamePasswordAuthenticationToken(uuid, null, authorities);
            accessor.setUser(principal);
        }
        return message;
    }
}
