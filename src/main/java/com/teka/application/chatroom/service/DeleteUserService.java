package com.teka.application.chatroom.service;

import com.teka.application.chatroom.port.in.DeleteUserUseCase;
import com.teka.application.user.port.out.DeleteUserPort;
import com.teka.domain.user.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DeleteUserService implements DeleteUserUseCase {

    private final DeleteUserPort deleteUserPort;

    @Override
    public void execute(List<UserId> userIdList) {
        userIdList.forEach(deleteUserPort::deleteByUserId);
    }
}
