package com.teka.shared.auth;

import com.teka.application.auth.port.out.TokenProvider;
import com.teka.application.chatroom.port.out.FindChatRoomPort;
import com.teka.application.user.port.out.FindUserPort;
import com.teka.domain.auth.type.Authority;
import com.teka.domain.chatroom.ChatRoom;
import com.teka.domain.user.User;
import com.teka.domain.user.UserId;
import com.teka.shared.constants.WebSocketConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class WebSocketAuthenticationInterceptor implements ChannelInterceptor {

    private final TokenProvider tokenProvider;
    private final WebSocketAuthenticationExtractor webSocketAuthenticationExtractor;
    private final FindUserPort findUserPort;
    private final FindChatRoomPort findChatRoomPort;

    @Override
    @Transactional(readOnly = true)
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
        StompCommand command = accessor.getCommand();

        if (List.of(StompCommand.CONNECT, StompCommand.SUBSCRIBE, StompCommand.SEND).contains(command)) {
            String token = webSocketAuthenticationExtractor.extract(accessor);

            String uuid = tokenProvider.getUuid(token);
            Authority authority = tokenProvider.getAuthority(token);
            List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(authority.name()));
            Principal principal = new UsernamePasswordAuthenticationToken(uuid, null, authorities);
            accessor.setUser(principal);

            if (StompCommand.SUBSCRIBE == command) {
                if (!canSubscribe(principal, accessor.getDestination())) {
                    throw new MessagingException("Not authorized to subscribe to destination: " + accessor.getDestination());
                }
            }
        }
        return message;
    }

    private boolean canSubscribe(Principal principal, String destination) {
        if (principal == null || destination == null) {
            return false;
        }
        if (!destination.startsWith(WebSocketConstant.SUBSCRIBE_ENDPOINT)) {
            return false;
        }

        Authentication authentication = (Authentication) principal;
        boolean isUser = authentication.getAuthorities().stream()
                .anyMatch(ga -> ga.getAuthority().equals(Authority.USER.name()));

        if (isUser) {
            UserId userId = new UserId(Long.parseLong(principal.getName()));
            User user = findUserPort.findById(userId)
                    .orElseThrow(() -> new MessagingException("User not found"));

            UUID chatRoomUuid = UUID.fromString(destination.substring(WebSocketConstant.SUBSCRIBE_ENDPOINT.length()));
            ChatRoom chatRoom = findChatRoomPort.findByUuid(chatRoomUuid)
                    .orElseThrow(() -> new MessagingException("Chat room not found"));

            return user.getChatRoomId().value().equals(chatRoom.getId().value());
        }
        return false;
    }
}
