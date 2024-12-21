package org.onishkoff.itmo.IS1.service;

import lombok.RequiredArgsConstructor;
import org.onishkoff.itmo.IS1.dto.login.RegisterDto;
import org.onishkoff.itmo.IS1.dto.model.UserDto;
import org.onishkoff.itmo.IS1.model.User;
import org.onishkoff.itmo.IS1.model.enums.Role;
import org.onishkoff.itmo.IS1.repository.UserRepository;
import org.onishkoff.itmo.IS1.util.JwtTokenUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService{

    private final JwtTokenUtils jwtTokenUtils;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Transactional
    public User registerUser(RegisterDto registerDto) {
        User registeredUser = User.builder()
                .login(registerDto.getLogin())
                .name(registerDto.getName())
                .password(passwordEncoder.encode(registerDto.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(registeredUser);
        return registeredUser;
    }

    public UserDto me(){
        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = this.findByLogin(login).orElseThrow();
        return UserDto.builder()
                .id(user.getId())
                .login(user.getLogin())
                .name(user.getName())
                .role(user.getRole())
                .build();
    }

    public Optional<User> findByLogin(String login) {
        return userRepository.findByLogin(login);
    }




}
