package com.teka.adapter.chat.in.web.dto.response;

import com.teka.application.chat.port.dto.SenderDto;

public record SenderResponse(
        Long id,
        String username
) {
    public static SenderResponse from(SenderDto dto) {
        return new SenderResponse(dto.id().value(), dto.username());
    }
}
