package com.ultra.rmq.service;


import com.ultra.rmq.dto.LoginUserDto;
import com.ultra.rmq.dto.RegisterUserDto;
import com.ultra.rmq.entity.Role;
import com.ultra.rmq.entity.User;
import com.ultra.rmq.enums.Roles;
import com.ultra.rmq.repository.RoleRepository;
import com.ultra.rmq.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final RoleRepository roleRepository;

    public AuthenticationService(
            UserRepository userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder,
            RoleRepository roleRepository
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    public User signup(RegisterUserDto input) {
        userRepository.findByEmail(input.getEmail()).ifPresent(user -> {
            throw new IllegalArgumentException("User with email already exists");
        });
        userRepository.findByUsername(input.getUsername()).ifPresent(user -> {
            throw new IllegalArgumentException("User with username already exists");
        });
        String role = input.isUserAdmin() ? Roles.ROLE_ADMIN.getRole() : Roles.ROLE_USER.getRole();
        Role userRole = roleRepository.findByAuthority(role).orElseGet(
                () -> roleRepository.save(new Role(role))
        );
        Set<Role> authorities = new HashSet<>();
        authorities.add(userRole);
        User user = new User();
        user.setUsername(input.getUsername());
        user.setFullName(input.getFullName());
        user.setEmail(input.getEmail());
        user.setPassword(passwordEncoder.encode(input.getPassword()));
        user.setAuthorities(authorities);
        return userRepository.save(user);
    }

    public User authenticate(LoginUserDto input) {
//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        input.getUsername(),
//                        input.getPassword()
//                )
//        );

        return userRepository.findByUsername(input.getUsername())
                .orElseThrow();
    }
}
