package com.ultra.rmq.config;

import com.ultra.rmq.entity.Role;
import com.ultra.rmq.enums.Roles;
import com.ultra.rmq.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.Arrays;

@Component
public class DataInitializer implements CommandLineRunner {
    private final RoleRepository roleRepository;

    public DataInitializer(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Initialize roles if they don't exist
        Arrays.stream(Roles.values())
                .forEach(roleEnum -> {
                    if (!roleRepository.existsByAuthority(roleEnum.getRole())) {
                        Role role = new Role(roleEnum.getRole());
                        roleRepository.save(role);
                    }
                });
    }
}
