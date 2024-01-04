package com.example.junior_portal.data.mapper;

import com.example.junior_portal.dtos.dto.UserDto;
import com.example.junior_portal.model.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDto(User user);

    User toModel(UserDto userDto);

    List<UserDto> toDtoList(List<User> list);

    List<User> toModelList(List<UserDto> list);
}
