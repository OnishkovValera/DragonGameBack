package org.onishkoff.itmo.IS1.dto.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.onishkoff.itmo.IS1.model.Location;
import org.onishkoff.itmo.IS1.model.enums.Color;
import org.onishkoff.itmo.IS1.model.enums.Country;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class PersonDto {

    private long id;

    private String name;

    private Color eyeColor;

    private Color hairColor;

    private Location location;

    private long weight;

    private Country nationality;

    private UserDto owner;

    private Date createdAt;
}
