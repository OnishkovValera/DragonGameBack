package org.onishkoff.itmo.IS1.dto.login;


import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class LoginDto {
    String login;
    String password;

}
