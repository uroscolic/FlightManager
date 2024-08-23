package com.flightmanager.UserService.controller;

import com.flightmanager.UserService.dto.TokenRequestDto;
import com.flightmanager.UserService.dto.TokenResponseDto;
import com.flightmanager.UserService.service.IUserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@AllArgsConstructor
public class UserController {

    private IUserService userService;
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping
    public ResponseEntity<TokenResponseDto> login(@RequestBody @Valid TokenRequestDto tokenRequestDto) {
        return new ResponseEntity<>(userService.login(tokenRequestDto), HttpStatus.OK);
    }
}
