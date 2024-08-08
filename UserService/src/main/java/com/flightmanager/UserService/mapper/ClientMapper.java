package com.flightmanager.UserService.mapper;

import com.flightmanager.UserService.domain.Client;
import com.flightmanager.UserService.domain.RoleType;
import com.flightmanager.UserService.dto.ClientDto;
import com.flightmanager.UserService.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ClientMapper {

    private RoleRepository roleRepository;

    public ClientDto clientToClientDto(Client client)
    {
        ClientDto clientDto = new ClientDto();
        clientDto.setFirstName(client.getFirstName());
        clientDto.setLastName(client.getLastName());
        clientDto.setEmail(client.getEmail());
        clientDto.setId(client.getId());
        clientDto.setIsBanned(client.getIsBanned());
        client.setRole(roleRepository.findRoleByRoleType(RoleType.ROLE_CLIENT).get());
        return clientDto;
    }
}
