package org.onishkoff.itmo.IS1.dto.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.onishkoff.itmo.IS1.model.User;
import org.onishkoff.itmo.IS1.model.enums.DragonCharacter;

@Data
@Builder
@AllArgsConstructor
public class DragonDtoRequest {

    private Integer id;

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    private CoordinatesDtoRequest coordinates;

    private CaveDtoRequest dragonCave;

    private PersonDtoRequest person;

    @Positive
    private Integer age;

    private String description;

    @NotNull
    private Boolean speaking;

    @NotNull
    private DragonCharacter dragonCharacter;

    @NotNull
    private DragonHeadDtoRequest dragonHead;

    private User owner;

}
