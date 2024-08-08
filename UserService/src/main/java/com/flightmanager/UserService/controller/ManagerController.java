package com.flightmanager.UserService.controller;

import com.flightmanager.UserService.domain.RoleType;
import com.flightmanager.UserService.dto.ManagerBanDto;
import com.flightmanager.UserService.dto.ManagerCreateDto;
import com.flightmanager.UserService.dto.ManagerDto;
import com.flightmanager.UserService.dto.UserDto;
import com.flightmanager.UserService.security.CheckSecurity;
import com.flightmanager.UserService.security.service.TokenService;
import com.flightmanager.UserService.service.IUserService;
import io.jsonwebtoken.Claims;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/manager")
@AllArgsConstructor
public class ManagerController {

    private IUserService userService;
    private TokenService tokenService;

    @GetMapping
    @CheckSecurity(roles = {"ROLE_MANAGER", "ROLE_ADMIN"})
    public ResponseEntity<Page<UserDto>> getAllManagers(@RequestHeader("Authorization") String authorization,
                                                        Pageable pageable) {
        return ResponseEntity.ok(userService.findAllOfRole(pageable, RoleType.ROLE_MANAGER));
    }

    @GetMapping("{id}")
    @CheckSecurity(roles = {"ROLE_MANAGER", "ROLE_ADMIN"})
    public ResponseEntity<UserDto> getManagerById(@RequestHeader("Authorization") String authorization,
                                                  @PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.findManagerById(id));
    }
    @PostMapping("/register")
    @CheckSecurity(roles = {"ROLE_ADMIN"})
    public ResponseEntity<ManagerDto> registerManager(@RequestHeader("Authorization") String authorization,
                                                      @RequestBody @Valid ManagerCreateDto managerCreateDto) {
        return new ResponseEntity<>(userService.registerManager(managerCreateDto), HttpStatus.CREATED);
    }

    @PutMapping("/ban")
    @CheckSecurity(roles = {"ROLE_ADMIN"})
    public ResponseEntity<ManagerDto> banManager(@RequestHeader("Authorization") String authorization,
                                                 @RequestBody ManagerBanDto managerBanDto) {
        return new ResponseEntity<>(userService.banManager(managerBanDto), HttpStatus.OK);
    }
}
