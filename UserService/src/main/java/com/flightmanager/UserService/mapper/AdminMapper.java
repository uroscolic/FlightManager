package com.flightmanager.UserService.mapper;

import com.flightmanager.UserService.domain.Admin;
import com.flightmanager.UserService.domain.Role;
import com.flightmanager.UserService.domain.RoleType;
import com.flightmanager.UserService.dto.AdminDto;
import org.springframework.stereotype.Component;

@Component
public class AdminMapper {


    public AdminDto adminToAdminDto(Admin admin)
    {
        AdminDto adminDto = new AdminDto();
        adminDto.setFirstName(admin.getFirstName());
        adminDto.setLastName(admin.getLastName());
        adminDto.setEmail(admin.getEmail());
        adminDto.setId(admin.getId());
        adminDto.setRole(new Role(RoleType.ROLE_ADMIN));

        return adminDto;
    }


}
