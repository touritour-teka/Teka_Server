package com.teka.application.user.port.in;

import com.teka.application.user.port.dto.UserInfoDto;
import com.teka.domain.user.UserId;

public interface UserInfoUseCase {
    UserInfoDto execute(UserId userId);
}
