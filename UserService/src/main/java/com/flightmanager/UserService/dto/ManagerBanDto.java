package com.flightmanager.UserService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ManagerBanDto {
    private String email;
    private boolean banned;
}
