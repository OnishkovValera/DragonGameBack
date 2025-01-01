package org.onishkoff.itmo.IS1.dto.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.onishkoff.itmo.IS1.model.Coordinates;
import org.onishkoff.itmo.IS1.model.DragonCave;
import org.onishkoff.itmo.IS1.model.DragonHead;
import org.onishkoff.itmo.IS1.model.Person;
import org.onishkoff.itmo.IS1.model.enums.DragonCharacter;

import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@Builder
public class DragonDto {
    private Integer id;
    private String name;
    private Coordinates coordinates;
    private ZonedDateTime dataTime;
    private DragonCave cave;
    private Person person;
    private Integer age;
    private String description;
    private Boolean speaking;
    private DragonCharacter character;
    private DragonHead head;

}
