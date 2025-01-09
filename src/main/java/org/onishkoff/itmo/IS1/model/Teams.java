package org.onishkoff.itmo.IS1.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Teams {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @NotNull
    @Column(nullable = false, name = "name")
    private String name;

    @NotNull
    @NotBlank
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable = false,name = "user_id", referencedColumnName = "id")
    private User userId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cave_id", referencedColumnName = "id")
    private DragonCave caveId;

}
