package com.flightmanager.UserService.runner;


import com.flightmanager.UserService.domain.*;
import com.flightmanager.UserService.repository.RoleRepository;
import com.flightmanager.UserService.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile({"default"})
@Component
@AllArgsConstructor
public class TestDataRunner implements CommandLineRunner {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    @Override
    public void run(String... args) throws Exception {

        Role adminRole = new Role();
        adminRole.setRoleType(RoleType.ROLE_ADMIN);
        roleRepository.save(adminRole);

        Role managerRole = new Role();
        managerRole.setRoleType(RoleType.ROLE_MANAGER);
        roleRepository.save(managerRole);

        Role clientRole = new Role();
        clientRole.setRoleType(RoleType.ROLE_CLIENT);
        roleRepository.save(clientRole);

        Admin admin = new Admin();
        admin.setFirstName("Dusan");
        admin.setLastName("Colic");
        admin.setEmail("dusancolic07@gmail.com");
        admin.setPassword("1234");
        admin.setRole(adminRole);

        userRepository.save(admin);

        Manager manager = new Manager();
        manager.setFirstName("Uros");
        manager.setLastName("Colic");
        manager.setEmail("uroscolic02@gmail.com");
        manager.setPassword("1234");
        manager.setRole(managerRole);


        userRepository.save(manager);

        Client client = new Client();
        client.setFirstName("Client");
        client.setLastName("Client");
        client.setEmail("client@gmail.com");
        client.setPassword("1234");
        client.setRole(clientRole);
        client.setActive(true);


        userRepository.save(client);

    }
}
