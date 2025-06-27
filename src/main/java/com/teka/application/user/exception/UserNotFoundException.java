package com.teka.application.user.exception;

import com.teka.application.user.exception.error.UserApplicationErrorProperty;
import com.teka.shared.error.TekaException;

public class UserNotFoundException extends TekaException {
  public UserNotFoundException() {
    super(UserApplicationErrorProperty.USER_NOT_FOUND);
  }
}
