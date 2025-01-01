package org.onishkoff.itmo.IS1.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import org.onishkoff.itmo.IS1.model.enums.DragonCharacter;

import java.time.ZonedDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Dragon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Integer id;

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "coordinates_id")
    private Coordinates coordinates;

    @NotNull
    @Column(name = "creation_date")
    private ZonedDateTime dataTime = ZonedDateTime.now();

    @ManyToOne
    @JoinColumn(name = "cave_id")
    private DragonCave cave;

    @ManyToOne
    @JoinColumn(name = "killer_id")
    private Person person;

    @Positive
    private Integer age;

    private String description;

    private Boolean speaking;

    @NotNull
    @Enumerated(EnumType.STRING)
    private DragonCharacter character;

    @NotNull
    @OneToOne
    @JoinColumn(name = "head_id")
    private DragonHead head;

}
