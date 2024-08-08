package com.flightmanager.UserService.repository;

import com.flightmanager.UserService.domain.RoleType;
import com.flightmanager.UserService.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.role.roleType = ?1")
    Page<User> findByRoleType(RoleType roleType, Pageable pageable);
    Optional<User> findByEmail(String email);
    Optional<User> findByEmailAndPassword(String email, String password);
}
