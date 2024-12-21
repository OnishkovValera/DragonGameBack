package org.onishkoff.itmo.IS1.controllers;


import lombok.AllArgsConstructor;
import org.onishkoff.itmo.IS1.dto.login.LoginDto;
import org.onishkoff.itmo.IS1.dto.login.LoginResponseDto;
import org.onishkoff.itmo.IS1.dto.login.RegisterDto;
import org.onishkoff.itmo.IS1.service.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@AllArgsConstructor
@RequestMapping("api/v1")
public class AuthenticationController{

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody RegisterDto registerDto){
        authenticationService.register(registerDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginDto loginDto){
        String token = authenticationService.authenticate(loginDto.getLogin(), loginDto.getPassword());
        return ResponseEntity.ok(LoginResponseDto.builder().token(token).build());

    }

}
