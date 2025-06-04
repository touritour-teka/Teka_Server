package com.teka.adapter.chat.out.persistence;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.teka.domain.chatroom.ChatRoom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.teka.adapter.chat.out.persistence.QChatJpaEntity.chatJpaEntity;

@RequiredArgsConstructor
@Repository
public class ChatRepositoryImpl implements ChatRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<ChatJpaEntity> findChats(ChatRoom chatRoom, Long cursor, int size) {
        return queryFactory
                .selectFrom(chatJpaEntity)
                .where(
                        chatJpaEntity.chatRoom.id.eq(chatRoom.getId().value()),
                        cursor != null ? chatJpaEntity.id.lt(cursor) : null
                )
                .orderBy(
                        chatJpaEntity.createdAt.desc(),
                        chatJpaEntity.id.desc()
                )
                .limit(size)
                .fetch();
    }
}