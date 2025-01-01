package org.onishkoff.itmo.IS1.controllers;


import lombok.RequiredArgsConstructor;
import org.onishkoff.itmo.IS1.dto.model.response.UserDto;
import org.onishkoff.itmo.IS1.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @GetMapping("/me")
    public UserDto me() {
        return userService.me();
    }
}
