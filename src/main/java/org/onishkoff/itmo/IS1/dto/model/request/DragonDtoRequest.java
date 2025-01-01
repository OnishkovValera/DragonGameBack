package org.onishkoff.itmo.IS1.dto.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import org.onishkoff.itmo.IS1.model.enums.DragonCharacter;

@Data
public class DragonDtoRequest {
    @NotNull
    @NotBlank
    private String name;
    @NotNull
    private Long coordinatesId;
    private Long caveId;
    private Long personId;
    @Positive
    private Integer age;
    private String description;
    private Boolean speaking;
    @NotNull
    private DragonCharacter character;
    @NotNull
    @NotBlank
    private Long headId;
}
