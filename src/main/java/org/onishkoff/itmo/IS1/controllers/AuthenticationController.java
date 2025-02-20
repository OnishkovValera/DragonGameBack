package org.onishkoff.itmo.IS1.controllers;


import lombok.AllArgsConstructor;
import org.onishkoff.itmo.IS1.dto.login.LoginDto;
import org.onishkoff.itmo.IS1.dto.login.LoginResponseDto;
import org.onishkoff.itmo.IS1.dto.login.RegisterDto;
import org.onishkoff.itmo.IS1.dto.login.RegisterResponseDto;
import org.onishkoff.itmo.IS1.service.AuthenticationService;
import org.onishkoff.itmo.IS1.util.Mapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;



@RestController
@AllArgsConstructor
@RequestMapping("api/v1")
public class AuthenticationController{

    private final AuthenticationService authenticationService;
    private final Mapper mapper;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDto> register(@RequestBody @Validated RegisterDto registerDto){
        RegisterResponseDto dto = mapper.toRegisterResponseDto(authenticationService.register(registerDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);

    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody @Validated LoginDto loginDto){
        System.out.println(loginDto);
        String token = authenticationService.authenticate(loginDto.getLogin(), loginDto.getPassword());
        return ResponseEntity.ok(LoginResponseDto.builder().token(token).build());
    }

    @GetMapping("/isAuthenticated")
    public ResponseEntity<Void> isAuthenticated(){
        return ResponseEntity.ok().build();
    }


}
