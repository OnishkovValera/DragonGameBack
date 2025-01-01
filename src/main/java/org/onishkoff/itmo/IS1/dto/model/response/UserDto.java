package org.onishkoff.itmo.IS1.dto.model.response;

import lombok.Builder;
import lombok.Data;
import org.onishkoff.itmo.IS1.model.enums.Role;

@Data
@Builder
public class UserDto {
    private Long id;
    private String name;
    private String login;
    private Role role;
}
