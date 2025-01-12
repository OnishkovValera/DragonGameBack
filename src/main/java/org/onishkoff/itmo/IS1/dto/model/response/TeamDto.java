package org.onishkoff.itmo.IS1.dto.model.response;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;

@Data
@Builder
public class TeamDto {
    Long id;
    String name;
    UserDto owner;
    CaveDto cave;
    ArrayList<PersonDto> members;
}
