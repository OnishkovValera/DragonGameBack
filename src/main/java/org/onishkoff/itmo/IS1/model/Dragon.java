package org.onishkoff.itmo.IS1.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import org.onishkoff.itmo.IS1.model.enums.DragonCharacter;

import java.time.ZonedDateTime;
import java.util.Date;

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
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "coordinates_id")
    private Coordinates coordinates;

    @NotNull
    @Column(name = "creation_date")
    private ZonedDateTime dataTime;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "cave_id", referencedColumnName = "id")
    private DragonCave cave;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST})
    @JoinColumn(name = "killer_id", referencedColumnName = "id")
    private Person person;

    @Positive
    private Integer age;

    private String description;

    private Boolean speaking;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "character", columnDefinition = "dragon_character")
    private DragonCharacter character;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "head_id", referencedColumnName = "id")
    private DragonHead head;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "created_by", referencedColumnName = "id")
    private User owner;

    @Column(name = "created_at")
    private Date createdAt;

    @PrePersist
    private void onCreate() {
        if (createdAt == null) {
            createdAt = new Date();
        }
        if (dataTime == null) {
            dataTime = ZonedDateTime.now();
        }
    }
}
