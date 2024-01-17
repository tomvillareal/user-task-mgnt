package com.cci.usertaskmngt.service;

import com.cci.usertaskmngt.dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto create(UserDto userDto);

    UserDto update(long id, UserDto userDto);

    UserDto findById(long id);
    List<UserDto> findAll();
}
