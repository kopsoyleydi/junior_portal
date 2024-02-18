package com.example.junior_portal.data.mapper;

import com.example.junior_portal.dtos.dto.UserDto;
import com.example.junior_portal.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final PermissionMapper permissionMapper;

    public UserDto toDto(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setUsername(user.getUsername());
        userDto.setPictureLink(user.getPictureLink());
        userDto.setPermissions(permissionMapper.toDtoList(user.getPermissions()));
        return userDto;
    };

    public User toModel(UserDto userDto){
        User user = new User();
        user.setId(userDto.getId());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setUsername(userDto.getUsername());
        user.setPictureLink(userDto.getPictureLink());
        user.setPermissions(permissionMapper.toModelList(userDto.getPermissions()));
        return user;
    };

    public List<UserDto> toDtoList(List<User> users) {
        return users.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public List<User> toModelList(List<UserDto> userDtos) {
        return userDtos.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
