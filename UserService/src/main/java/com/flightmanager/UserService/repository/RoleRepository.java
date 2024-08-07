package com.flightmanager.UserService.repository;


import com.flightmanager.UserService.domain.Role;
import com.flightmanager.UserService.domain.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findRoleByRoleType(RoleType roleType);
}
