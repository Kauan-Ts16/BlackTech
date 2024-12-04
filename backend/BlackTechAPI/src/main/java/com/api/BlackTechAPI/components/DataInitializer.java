package com.api.BlackTechAPI.components;

import com.api.BlackTechAPI.enums.RoleName;
import com.api.BlackTechAPI.model.RoleModel;
import com.api.BlackTechAPI.model.UserModel;
import com.api.BlackTechAPI.repository.RoleRepository;
import com.api.BlackTechAPI.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public void run(String... args) throws Exception {

        if (roleRepository.count() == 0) {

            RoleModel admin = new RoleModel(RoleName.ROLE_ADMIN);
            RoleModel client = new RoleModel(RoleName.ROLE_CLIENT);

            roleRepository.save(admin);
            roleRepository.save(client);
        }

        if (userRepository.count() == 0) {

            UserModel defaultUser = new UserModel();
            defaultUser.setName("Admin");
            defaultUser.setEmail("admin@gmail.com");
            defaultUser.setPassword(passwordEncoder.encode("password-admin"));

            RoleModel admin = roleRepository.findByRoleName(RoleName.ROLE_ADMIN);
            defaultUser.setRole(admin);

            userRepository.save(defaultUser);
        }

    }
}
