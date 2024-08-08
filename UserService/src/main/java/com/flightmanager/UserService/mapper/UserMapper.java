package com.flightmanager.UserService.mapper;

import com.flightmanager.UserService.domain.Role;
import com.flightmanager.UserService.domain.RoleType;
import com.flightmanager.UserService.domain.User;
import com.flightmanager.UserService.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDto userToUserDto(User user)
    {
        UserDto userDto = new UserDto();
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setId(user.getId());

        if(user.getRole().getRoleType().equals(RoleType.ROLE_ADMIN))
            userDto.setRole(new Role(RoleType.ROLE_ADMIN));
        else if(user.getRole().getRoleType().equals(RoleType.ROLE_MANAGER))
            userDto.setRole(new Role(RoleType.ROLE_MANAGER));
        else
            userDto.setRole(new Role(RoleType.ROLE_CLIENT));

        return userDto;
    }
}
