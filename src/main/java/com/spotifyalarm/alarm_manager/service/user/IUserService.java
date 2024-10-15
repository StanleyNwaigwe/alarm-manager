package com.spotifyalarm.alarm_manager.service.user;

import com.spotifyalarm.alarm_manager.dto.UserDto;

import java.util.Optional;

public interface IUserService {
    UserDto registerUser(UserDto userDto);
    Optional<UserDto> findUserByUsername(String username);
    UserDto updateUser(Long id, UserDto userDto);
    void deleteUser(Long id);
}