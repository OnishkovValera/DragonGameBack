package org.onishkoff.itmo.IS1.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.onishkoff.itmo.IS1.model.enums.Color;
import org.onishkoff.itmo.IS1.model.enums.Country;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Person {

    @Id
    private long id;
    @NotNull
    @NotBlank
    private String name;
    private Color eyeColor;
    private Color hairColor;
    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;
    @Positive
    private long weight;
    private Country nationality;
}
