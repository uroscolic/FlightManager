package com.flightmanager.UserService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ManagerChangePasswordDto {

    private Long id;
    private String oldPassword;
    private String newPassword;

}
