package org.onishkoff.itmo.IS1.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import org.onishkoff.itmo.IS1.model.enums.Color;
import org.onishkoff.itmo.IS1.model.enums.Country;
import java.util.Date;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @NotBlank
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "eye_color", columnDefinition = "color")
    private Color eyeColor;

    @Enumerated(EnumType.STRING)
    @Column(name = "hair_color", columnDefinition = "color")
    private Color hairColor;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id", referencedColumnName = "id")
    private Location location;

    @Positive
    private long weight;

    @Enumerated(EnumType.STRING)
    @Column(name = "nationality", columnDefinition = "country")
    private Country nationality;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "created_by", nullable = false, referencedColumnName = "id")
    private User owner;

    @Column(nullable = false, name = "created_at")
    private Date CreatedAt;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(columnDefinition = "team_id", referencedColumnName = "id")
    private Teams team;

    @PrePersist
    private void onCreate() {
        if(CreatedAt == null){
            CreatedAt = new Date();
        }
    }

}
