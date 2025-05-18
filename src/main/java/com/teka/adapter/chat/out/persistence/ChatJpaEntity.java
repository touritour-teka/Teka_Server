package com.teka.adapter.chat.out.persistence;

import com.teka.adapter.chatroom.out.persistence.ChatRoomJpaEntity;
import com.teka.adapter.user.out.persistence.UserJpaEntity;
import com.teka.domain.chat.Chat;
import com.teka.domain.chat.ChatId;
import com.teka.shared.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tbl_chat")
@Entity
public class ChatJpaEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "user_id")
    private UserJpaEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "chat_room_id")
    private ChatRoomJpaEntity chatRoom;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String message;

    public ChatJpaEntity(UserJpaEntity user, ChatRoomJpaEntity chatRoom, String message) {
        this.user = user;
        this.chatRoom = chatRoom;
        this.message = message;
    }

    public Chat toDomain() {
        return Chat.builder()
                .id(new ChatId(id))
                .user(user.toDomain())
                .chatRoom(chatRoom.toDomain())
                .build();
    }
}
