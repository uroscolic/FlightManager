package com.flightmanager.UserService.service.impl;

import com.flightmanager.UserService.domain.Client;
import com.flightmanager.UserService.domain.Manager;
import com.flightmanager.UserService.domain.RoleType;
import com.flightmanager.UserService.domain.User;
import com.flightmanager.UserService.dto.*;
import com.flightmanager.UserService.mapper.AdminMapper;
import com.flightmanager.UserService.mapper.ClientMapper;
import com.flightmanager.UserService.mapper.ManagerMapper;
import com.flightmanager.UserService.mapper.UserMapper;
import com.flightmanager.UserService.repository.UserRepository;
import com.flightmanager.UserService.security.service.TokenService;
import com.flightmanager.UserService.service.IUserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Getter
@Setter
@Service
@Transactional
@AllArgsConstructor
public class UserServiceImpl implements IUserService {

    private ClientMapper clientMapper;
    private AdminMapper adminMapper;
    private ManagerMapper managerMapper;
    private UserMapper userMapper;
    private UserRepository userRepository;
    private TokenService tokenService;


    @Override
    public Page<UserDto> findAll(Pageable pageable) {
        return userRepository.findAll(pageable).map(userMapper::userToUserDto);
    }

    @Override
    public ClientDto findClientById(Long id) {
        User user = userRepository.findById(id).orElseThrow(()-> new RuntimeException("Client not found"));
        if(user instanceof Client client){
            return clientMapper.clientToClientDto(client);
        }
        throw new RuntimeException("Client not found");
    }

    @Override
    public ClientDto findClientByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(()-> new RuntimeException("Client not found"));
        if(user instanceof Client client){
            return clientMapper.clientToClientDto(client);
        }
        throw new RuntimeException("Client not found");
    }

    @Override
    public ManagerDto findManagerById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Manager not found"));
        if(user instanceof Manager manager){
            return managerMapper.managerToManagerDto(manager);
        }
        throw new RuntimeException("Client not found");
    }

    @Override
    public Page<UserDto> findAllOfRole(Pageable pageable, RoleType roleType) {
        return userRepository.findByRoleType(roleType, pageable).map(userMapper::userToUserDto);
    }


    @Override
    public ClientDto registerClient(ClientCreateDto clientCreateDto) {
        Client client = clientMapper.clientCreateDtoToClient(clientCreateDto);
        userRepository.save(client);
        return clientMapper.clientToClientDto(client);
    }

    @Override
    public ManagerDto registerManager(ManagerCreateDto managerCreateDto) {
        Manager manager = managerMapper.managerCreateDtoToManager(managerCreateDto);
        userRepository.save(manager);
        return managerMapper.managerToManagerDto(manager);
    }

    @Override
    public ClientDto banClient(ClientBanDto clientBanDto) {
        User user = userRepository.findByEmail(clientBanDto.getEmail()).orElseThrow(() -> new RuntimeException("Client not found"));
        if(user instanceof Client client){
            client = clientMapper.clientBanDtoToClient(client, clientBanDto);
            userRepository.save(client);
            return clientMapper.clientToClientDto(client);
        }
        throw new RuntimeException("Client not found");

    }

    @Override
    public ManagerDto banManager(ManagerBanDto managerBanDto) {
        User user = userRepository.findByEmail(managerBanDto.getEmail()).orElseThrow(() -> new RuntimeException("Manager not found"));
        if(user instanceof Manager manager){
            manager = managerMapper.managerBanDtoToManager(manager, managerBanDto);
            userRepository.save(manager);
            return managerMapper.managerToManagerDto(manager);
        }
        throw new RuntimeException("Manager not found");
    }

    @Override
    public TokenResponseDto login(TokenRequestDto tokenRequestDto) {
        User user = userRepository
                .findByEmailAndPassword(tokenRequestDto.getEmail(), tokenRequestDto.getPassword())
                .orElseThrow(() -> new RuntimeException(String
                        .format("User with username: %s and password: %s not found.", tokenRequestDto.getEmail(),
                                tokenRequestDto.getPassword())));

        Claims claims = Jwts.claims();
        claims.put("id", user.getId());
        claims.put("role", user.getRole().getRoleType());
        claims.put("email", user.getEmail());

        if(user instanceof Client client && client.getIsBanned()){
            throw new RuntimeException("Client is banned");
        }
        else if (user instanceof Manager manager && manager.getIsBanned()){
            throw new RuntimeException("Manager is banned");
        }

        return new TokenResponseDto(tokenService.generate(claims));
    }

    @Override
    public void incrementReservationCount(IncrementBookCountDto incrementBookCountDto) {
        User user = userRepository.findById(incrementBookCountDto.getUserId()).orElseThrow(() -> new RuntimeException("Client not found"));
        if(user instanceof Client client){
            client.setNumberOfBookings(client.getNumberOfBookings() + 1);
            userRepository.save(user);
        }
        else {
            throw new RuntimeException("Client not found");
        }

    }
    @Override
    public void decrementReservationCount(DecrementBookCountDto decrementBookCountDto) {
        User user = userRepository.findById(decrementBookCountDto.getUserId()).orElseThrow(() -> new RuntimeException("Client not found"));
        if(user instanceof Client client){
            client.setNumberOfBookings(client.getNumberOfBookings() - 1);
            userRepository.save(user);
        }
        else {
            throw new RuntimeException("Client not found");
        }
    }
}
