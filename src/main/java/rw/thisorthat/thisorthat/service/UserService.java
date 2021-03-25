package rw.thisorthat.thisorthat.service;

import java.util.List;

import rw.thisorthat.thisorthat.model.dto.UserDto;

public interface UserService {
    UserDto createUser(UserDto userDto);
    List<UserDto> getUsers(int page, int limit);
    UserDto getUserById(String userId) throws Exception;
}
