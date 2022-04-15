package com.example.userservice.mapper;

import com.example.userservice.dto.UserDto;
import com.example.userservice.model.User;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Named("userToUserDto")
    UserDto userToUserDto(User entity);

    User userDtoToUser(UserDto dto);

    @IterableMapping(qualifiedByName = "userToUserDto")
    List<UserDto> allToDto(List<User> users);
}
