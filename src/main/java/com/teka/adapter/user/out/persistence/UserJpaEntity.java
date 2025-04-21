package com.teka.adapter.user.out.persistence;

import com.teka.adapter.chatroom.out.persistence.ChatRoomJpaEntity;
import com.teka.domain.chatroom.ChatRoomId;
import com.teka.domain.user.Email;
import com.teka.domain.user.PhoneNumber;
import com.teka.domain.user.User;
import com.teka.domain.user.UserId;
import com.teka.domain.user.type.Language;
import com.teka.domain.user.type.UserType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "tbl_user")
public class UserJpaEntity {

    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = true, length = 30)
    private String username;

    @Column(nullable = false, length = 11)
    private String phoneNumber;

    @Column(nullable = false, length = 255)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = true, length = 30)
    private Language language;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private UserType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name="chat_room_id")
    private ChatRoomJpaEntity chatRoom;

    @Builder
    public UserJpaEntity(Long id, String username, String phoneNumber, String email, Language language, UserType type, ChatRoomJpaEntity chatRoom) {
        this.id = id;
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.language = language;
        this.type = type;
        this.chatRoom = chatRoom;
    }

    public void changeType(UserType type) {
        this.type = type;
    }

    public static UserJpaEntity from(User user, ChatRoomJpaEntity chatRoom) {
        return UserJpaEntity.builder()
                .username(user.getUsername())
                .phoneNumber(user.getPhoneNumber().value())
                .email(user.getEmail().value())
                .language(user.getLanguage())
                .type(user.getType())
                .chatRoom(chatRoom)
                .build();
    }

    public User toDomain() {
        return User.builder()
                .id(new UserId(this.id))
                .username(this.username)
                .phoneNumber(new PhoneNumber(this.phoneNumber))
                .email(new Email(this.email))
                .language(this.language)
                .type(this.type)
                .chatRoomId(new ChatRoomId(this.chatRoom.getId()))
                .build();
    }
}
