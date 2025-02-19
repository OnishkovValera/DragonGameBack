package org.onishkoff.itmo.IS1.dto.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.onishkoff.itmo.IS1.model.enums.Color;
import org.onishkoff.itmo.IS1.model.enums.Country;


@Data
public class PersonDtoRequest {
    private Long id;

    @NotBlank
    @NotNull
    @Size(min = 6, message = "Минимальная длина имени: 6")
    private String name;

    private Color eyeColor;

    private Color hairColor;

    private LocationDtoRequest location;

    @Positive
    private long weight;

    private Country nationality;

}
