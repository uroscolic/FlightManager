package com.flightmanager.UserService.mapper;

import com.flightmanager.UserService.domain.*;
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

        if(user instanceof Admin) {
            userDto.setRoleType(RoleType.ROLE_ADMIN.toString());
            userDto.setBanned(false);
        }
        else if(user instanceof Manager manager) {
            userDto.setRoleType(RoleType.ROLE_MANAGER.toString());
            userDto.setBanned(manager.getIsBanned());
        }
        else {
            userDto.setRoleType(RoleType.ROLE_CLIENT.toString());
            userDto.setBanned(((Client) user).getIsBanned());
        }

        return userDto;
    }
}
