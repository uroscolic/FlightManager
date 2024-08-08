package com.flightmanager.UserService.service.impl;

import com.flightmanager.UserService.domain.RoleType;
import com.flightmanager.UserService.dto.*;
import com.flightmanager.UserService.service.IUserService;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;

@Getter
@Setter
@Service
@Transactional
public class UserServiceImpl implements IUserService {
    @Override
    public Page<UserDto> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public ClientDto findClientById(Long id) {
        return null;
    }

    @Override
    public ManagerDto findManagerById(Long id) {
        return null;
    }

    @Override
    public Page<UserDto> findAllOfRole(Pageable pageable, RoleType roleType) {
        return null;
    }

    @Override
    public ClientDto registerClient(ClientCreateDto clientDto) {
        return null;
    }

    @Override
    public ManagerDto registerManager(ManagerCreateDto managerDto) {
        return null;
    }

    @Override
    public ClientDto banClient(ClientBanDto clientBanDto) {
        return null;
    }

    @Override
    public ManagerDto banManager(ManagerBanDto managerBanDto) {
        return null;
    }

    @Override
    public TokenResponseDto login(TokenRequestDto tokenRequestDto) {
        return null;
    }
}
