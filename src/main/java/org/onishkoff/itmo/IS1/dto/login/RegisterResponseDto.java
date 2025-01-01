package org.onishkoff.itmo.IS1.dto.login;

import lombok.Builder;
import lombok.Data;
import org.onishkoff.itmo.IS1.model.enums.Role;

@Data
@Builder
public class RegisterResponseDto {
    private Long id;
    private String name;
    private String login;
    private String password;
    private Role role;

}
