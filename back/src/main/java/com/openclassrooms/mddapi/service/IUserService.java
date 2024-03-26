package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.dto.UserDto;
import com.openclassrooms.mddapi.model.User;

import java.util.Optional;

public interface IUserService {
    String authenticateUser(UserDto userDto);

    String createNewUser(UserDto userDto);

    Optional<User> saveUser(UserDto userDto);

    User findById(Long id);

    User getUserByEmail(String email);
}
