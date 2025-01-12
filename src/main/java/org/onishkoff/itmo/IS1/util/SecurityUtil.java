package org.onishkoff.itmo.IS1.util;

import lombok.RequiredArgsConstructor;
import org.onishkoff.itmo.IS1.model.User;
import org.onishkoff.itmo.IS1.model.enums.Role;
import org.onishkoff.itmo.IS1.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SecurityUtil{


    private final UserService userService;

    public User getUserFromContext(){
        String login = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userService.findByLogin(login).orElseThrow(() -> new UsernameNotFoundException(login));

    }

    public Boolean hasAccess(Long ownerId){
        User user = getUserFromContext();
        return user.getId().equals(ownerId) || user.getRole().equals(Role.ADMIN);

    }

    public Boolean isAdmin(){
        User user = getUserFromContext();
        return user.getRole().equals(Role.ADMIN);
    }


}
