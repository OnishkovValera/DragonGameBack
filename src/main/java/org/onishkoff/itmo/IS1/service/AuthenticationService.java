package org.onishkoff.itmo.IS1.service;

import lombok.RequiredArgsConstructor;
import org.onishkoff.itmo.IS1.dto.login.RegisterDto;
import org.onishkoff.itmo.IS1.model.User;
import org.onishkoff.itmo.IS1.util.JwtTokenUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtTokenUtils jwtTokenUtils;


    public String authenticate(String login, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login, password));
        User user = userService.findByLogin(login).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return jwtTokenUtils.generateToken(user);


    }

    public User register(RegisterDto registerDto) {
        return userService.registerUser(registerDto);

    }

}
