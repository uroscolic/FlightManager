package com.flightmanager.UserService.service;

import com.flightmanager.UserService.domain.RoleType;
import com.flightmanager.UserService.dto.*;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;

public interface IUserService {

    Page<UserDto> findAll(Pageable pageable);
    ClientDto findClientById(Long id);
    ClientDto findClientByEmail(String email);
    ManagerDto findManagerById(Long id);
    Page<UserDto> findAllOfRole(Pageable pageable, RoleType roleType);
    ClientDto registerClient(ClientCreateDto clientCreateDto);
    ManagerDto registerManager(ManagerCreateDto managerCreateDto);
    ClientDto banClient(ClientBanDto clientBanDto);
    ManagerDto banManager(ManagerBanDto managerBanDto);
    ManagerDto changeManagerPassword(ManagerChangePasswordDto managerChangePasswordDto);
    TokenResponseDto login(TokenRequestDto tokenRequestDto);
    void incrementReservationCount(IncrementBookCountDto incrementReservationCountDto);
    void decrementReservationCount(DecrementBookCountDto decrementReservationCountDto);

}
