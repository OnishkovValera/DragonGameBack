package org.onishkoff.itmo.IS1.dto.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.onishkoff.itmo.IS1.model.*;
import org.onishkoff.itmo.IS1.model.enums.DragonCharacter;

import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@Builder
public class DragonDto {
    private Integer id;
    private String name;
    private Coordinates coordinates;
    private ZonedDateTime creationDateTime;
    private DragonCave dragonCave;
    private PersonDto person;
    private Integer age;
    private String description;
    private Boolean speaking;
    private DragonCharacter dragonCharacter;
    private DragonHead dragonHead;
    private User owner;
}
