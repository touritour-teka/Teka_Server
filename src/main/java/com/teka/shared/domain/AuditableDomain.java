package com.teka.shared.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class AuditableDomain {
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
}
