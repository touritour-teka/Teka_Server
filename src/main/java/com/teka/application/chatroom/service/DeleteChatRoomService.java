package com.teka.application.chatroom.service;

import com.teka.application.chatroom.exception.ChatRoomNotFoundException;
import com.teka.application.chatroom.port.in.DeleteChatRoomUseCase;
import com.teka.application.chatroom.port.out.DeleteChatRoomPort;
import com.teka.application.chatroom.port.out.FindChatRoomPort;
import com.teka.domain.admin.AdminId;
import com.teka.domain.chatroom.ChatRoomId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DeleteChatRoomService implements DeleteChatRoomUseCase {

    private final DeleteChatRoomPort deleteChatRoomPort;
    private final FindChatRoomPort findChatRoomPort;

    // FIXME
    //  - ChatRoom에 User가 소속되어있는 경우 영속성 문제로 삭제되지 않는 오류 발생
    //  - ChatRoom을 삭제할 경우 소속된 유저와 채팅기록까지 모두 삭제하도록 변경해야함
    @Override
    public void execute(ChatRoomId chatRoomId, AdminId adminId) {
        findChatRoomPort.findById(chatRoomId)
                .orElseThrow(ChatRoomNotFoundException::new)
                .isAdmin(adminId);
        deleteChatRoomPort.deleteById(chatRoomId);
    }
}
