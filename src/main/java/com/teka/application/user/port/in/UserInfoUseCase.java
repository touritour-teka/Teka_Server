package com.teka.application.user.port.in;

import com.teka.application.user.port.dto.UserDto;
import com.teka.domain.user.UserId;

public interface UserInfoUseCase {
    UserDto execute(UserId userId);
}
