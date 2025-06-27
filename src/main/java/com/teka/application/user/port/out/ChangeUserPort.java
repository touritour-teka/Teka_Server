package com.teka.application.user.port.out;

import com.teka.domain.user.UserId;
import com.teka.domain.user.type.Language;
import com.teka.domain.user.type.UserType;

public interface ChangeUserPort {
    void changeUserType(UserId userId, UserType type);
    void changeUsername(UserId userId, String name);
    void changeLanguage(UserId userId, Language language);
}
