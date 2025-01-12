package org.onishkoff.itmo.IS1.dto.login;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;


@Builder
@Data
public class RegisterDto {

    @NotNull
    @NotBlank
    @Email
    String login;

    @NotBlank
    @NotNull
    @Size(min = 6)
    String password;

    @NotBlank
    @NotNull
    @Size(min = 6)
    String name;

}
