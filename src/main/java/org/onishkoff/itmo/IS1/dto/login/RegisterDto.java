package org.onishkoff.itmo.IS1.dto.login;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;


@Builder
@Data
public class RegisterDto {

    @NotNull
    @NotBlank
    String login;

    @NotBlank
    @NotNull
    String password;

    @NotBlank
    @NotNull
    String name;

}
