package com.flightmanager.UserService.controller;

import com.flightmanager.UserService.domain.RoleType;
import com.flightmanager.UserService.dto.ClientBanDto;
import com.flightmanager.UserService.dto.ClientCreateDto;
import com.flightmanager.UserService.dto.ClientDto;
import com.flightmanager.UserService.dto.UserDto;
import com.flightmanager.UserService.security.CheckSecurity;
import com.flightmanager.UserService.security.service.TokenService;
import com.flightmanager.UserService.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class ClientController {

    private IUserService userService;
    private TokenService tokenService;

    @GetMapping("/{id}")
    public ResponseEntity<ClientDto> findClientById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(userService.findClientById(id), HttpStatus.OK);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<ClientDto> findClientByEmail(@PathVariable("email") String email) {
        return new ResponseEntity<>(userService.findClientByEmail(email), HttpStatus.OK);
    }


    @GetMapping()
    @CheckSecurity(roles = {"ROLE_ADMIN", "ROLE_MANAGER"})
    public ResponseEntity<Page<UserDto>> findAllClients(@RequestHeader("Authorization") String authorization,
                                                        Pageable pageable) {
        return new ResponseEntity<>(userService.findAllOfRole(pageable, RoleType.ROLE_CLIENT), HttpStatus.OK);
    }
    @PutMapping("/ban")
    @CheckSecurity(roles = {"ROLE_ADMIN"})
    public ResponseEntity<ClientDto> banClient(@RequestHeader("Authorization") String authorization,
                                              @RequestBody ClientBanDto clientBanDto) {
        return new ResponseEntity<>(userService.banClient(clientBanDto), HttpStatus.OK);
    }
    @PostMapping("/register")
    public ResponseEntity<ClientDto> registerClient(@RequestBody ClientCreateDto clientCreateDto) {
        return new ResponseEntity<>(userService.registerClient(clientCreateDto), HttpStatus.CREATED);
    }

}
