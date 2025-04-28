package com.teka.application.user.service;

import com.teka.application.user.exception.UserNotFoundException;
import com.teka.application.user.port.dto.UserInfoDto;
import com.teka.application.user.port.in.UserInfoUseCase;
import com.teka.application.user.port.out.FindUserPort;
import com.teka.domain.user.User;
import com.teka.domain.user.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserInfoService implements UserInfoUseCase {

    private final FindUserPort findUserPort;

    @Override
    public UserInfoDto execute(UserId userId) {
        User user = findUserPort.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        return UserInfoDto.from(user);
    }
}
