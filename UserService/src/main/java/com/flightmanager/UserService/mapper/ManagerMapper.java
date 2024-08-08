package com.flightmanager.UserService.mapper;

import com.flightmanager.UserService.domain.Manager;
import com.flightmanager.UserService.domain.RoleType;
import com.flightmanager.UserService.dto.ManagerDto;
import com.flightmanager.UserService.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ManagerMapper {

    private RoleRepository roleRepository;

    public ManagerDto managerToManagerDto(Manager manager)
    {
        ManagerDto managerDto = new ManagerDto();
        managerDto.setFirstName(manager.getFirstName());
        managerDto.setLastName(manager.getLastName());
        managerDto.setEmail(manager.getEmail());
        managerDto.setId(manager.getId());
        managerDto.setIsBanned(manager.getIsBanned());
        manager.setRole(roleRepository.findRoleByRoleType(RoleType.ROLE_MANAGER).get());
        return managerDto;
    }

}
