package com.teka.adapter.user.out.persistence;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.teka.domain.user.type.Language;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;

import static com.teka.adapter.user.out.persistence.QUserJpaEntity.userJpaEntity;

@RequiredArgsConstructor
@Repository
public class UserRepositoryImpl implements UserRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Set<Language> findLanguagesByChatRoomId(Long chatRoomId) {
        return new HashSet<>(queryFactory
                .select(userJpaEntity.language)
                .from(userJpaEntity)
                .where(
                        userJpaEntity.chatRoom.id.eq(chatRoomId),
                        userJpaEntity.language.isNotNull()
                )
                .distinct()
                .fetch());
    }
}
