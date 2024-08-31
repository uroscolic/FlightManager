package com.flightmanager.UserService.mapper;

import com.flightmanager.UserService.domain.Manager;
import com.flightmanager.UserService.domain.RoleType;
import com.flightmanager.UserService.dto.ManagerBanDto;
import com.flightmanager.UserService.dto.ManagerChangePasswordDto;
import com.flightmanager.UserService.dto.ManagerCreateDto;
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
        managerDto.setRoleType(RoleType.ROLE_MANAGER.toString());
        return managerDto;
    }

    public Manager managerCreateDtoToManager(ManagerCreateDto managerCreateDto)
    {
        Manager manager = new Manager();
        manager.setFirstName(managerCreateDto.getFirstName());
        manager.setLastName(managerCreateDto.getLastName());
        manager.setEmail(managerCreateDto.getEmail());
        manager.setPassword(managerCreateDto.getPassword());
        manager.setRole(roleRepository.findRoleByRoleType(RoleType.ROLE_MANAGER).get());
        manager.setIsBanned(false);
        return manager;
    }

    public Manager managerBanDtoToManager(Manager manager, ManagerBanDto managerBanDto)
    {
        manager.setIsBanned(managerBanDto.isBanned());
        return manager;
    }

    public Manager managerChangePasswordDtoToManager(Manager manager, ManagerChangePasswordDto managerChangePasswordDto)
    {
        manager.setPassword(managerChangePasswordDto.getNewPassword());
        return manager;
    }

}
