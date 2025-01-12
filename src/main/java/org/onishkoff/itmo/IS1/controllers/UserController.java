package org.onishkoff.itmo.IS1.controllers;


import lombok.RequiredArgsConstructor;
import org.onishkoff.itmo.IS1.dto.model.response.UserDto;
import org.onishkoff.itmo.IS1.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/me")
    public UserDto me() {
        return userService.me();
    }

    @PutMapping
    @PreAuthorize("@securityUtil.hasAccess(userDto.getId())")
    public ResponseEntity<UserDto> update(@RequestBody UserDto userDto) {
        UserDto user = userService.update(userDto);
        return ResponseEntity.ok(user);
    }



}
