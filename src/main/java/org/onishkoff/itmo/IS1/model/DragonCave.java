package org.onishkoff.itmo.IS1.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class DragonCave {
    @Id
    private Long id;
    private Double depth;
    private Double numberOfTreasures;

}
