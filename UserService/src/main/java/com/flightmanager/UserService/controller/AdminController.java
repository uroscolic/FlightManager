package com.flightmanager.UserService.controller;

import com.flightmanager.UserService.domain.RoleType;
import com.flightmanager.UserService.dto.UserDto;
import com.flightmanager.UserService.security.CheckSecurity;
import com.flightmanager.UserService.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController {
    private IUserService userService;

    @GetMapping
    @CheckSecurity(roles = {"ROLE_ADMIN"})
    public ResponseEntity<Page<UserDto>> getAllAdmins(@RequestHeader("Authorization") String authorization,
                                                      Pageable pageable) {

        return new ResponseEntity<>(userService.findAllOfRole(pageable, RoleType.ROLE_ADMIN), HttpStatus.OK);
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/all-users")
    @CheckSecurity(roles = {"ROLE_ADMIN"})
    public ResponseEntity<Page<UserDto>> getAllUsers(@RequestHeader("Authorization") String authorization,
                                                     Pageable pageable) {

        return new ResponseEntity<>(userService.findAll(pageable), HttpStatus.OK);
    }

}