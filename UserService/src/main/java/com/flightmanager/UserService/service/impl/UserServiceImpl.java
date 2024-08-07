package com.flightmanager.UserService.service.impl;

import com.flightmanager.UserService.service.IUserService;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
@Service
@Transactional
public class UserServiceImpl implements IUserService {
}
