package com.flightmanager.UserService.service;

import com.flightmanager.UserService.domain.RoleType;
import com.flightmanager.UserService.domain.User;
import com.flightmanager.UserService.dto.*;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;

public interface IUserService {

    Page<UserDto> findAll(Pageable pageable);
    ClientDto findClientById(Long id);
    ManagerDto findManagerById(Long id);
    Page<UserDto> findAllOfRole(Pageable pageable, RoleType roleType);
    ClientDto registerClient(ClientCreateDto clientDto);
    ManagerDto registerManager(ManagerCreateDto managerDto);
    ClientDto banClient(ClientBanDto clientBanDto);
    ManagerDto banManager(ManagerBanDto managerBanDto);
    TokenResponseDto login(TokenRequestDto tokenRequestDto);
}
