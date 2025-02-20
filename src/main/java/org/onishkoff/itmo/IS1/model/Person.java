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
@Table(name = "person", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @NotBlank
    @Column(unique = true)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "eye_color", columnDefinition = "color")
    private Color eyeColor;

    @Enumerated(EnumType.STRING)
    @Column(name = "hair_color", columnDefinition = "color")
    private Color hairColor;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "location_id", referencedColumnName = "id")
    private Location location;

    @Positive
    private long weight;

    @Enumerated(EnumType.STRING)
    @Column(name = "nationality", columnDefinition = "country")
    private Country nationality;

    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "created_by", nullable = false, referencedColumnName = "id")
    private User owner;

    @Column(nullable = false, name = "created_at")
    private Date CreatedAt;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(columnDefinition = "team_id", referencedColumnName = "id")
    private Team team;

    @PrePersist
    private void onCreate() {
        if (CreatedAt == null) {
            CreatedAt = new Date();
        }
    }

}
