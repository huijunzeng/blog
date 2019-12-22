package com.teeya.user.service;

import com.teeya.user.entity.UserEntity;

public interface UserService {

    UserEntity selectByUsername(String username);
}
