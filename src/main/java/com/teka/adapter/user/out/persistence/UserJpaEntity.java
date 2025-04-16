package com.teka.adapter.user.out.persistence;

import com.teka.adapter.chatroom.out.persistence.ChatRoomJpaEntity;
import com.teka.domain.user.type.Language;
import com.teka.domain.user.type.UserType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "tbl_user")
public class UserJpaEntity {

    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false, length = 30)
    private String username;

    @Column(nullable = false, length = 11)
    private String phoneNumber;

    @Column(nullable = false, length = 255)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private Language language;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private UserType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name="chat_room_id")
    private ChatRoomJpaEntity chatroom;

    @Builder
    public UserJpaEntity(Long id, String username, String phoneNumber, String email, Language language, UserType type, ChatRoomJpaEntity chatroom) {
        this.id = id;
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.language = language;
        this.type = type;
        this.chatroom = chatroom;
    }
}
