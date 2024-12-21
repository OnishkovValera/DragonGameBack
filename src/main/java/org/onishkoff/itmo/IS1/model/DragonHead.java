package org.onishkoff.itmo.IS1.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class DragonHead {
    @Id
    private Long id;

    @Column
    private Integer toothCount;
}
