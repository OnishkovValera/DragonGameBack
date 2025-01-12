package org.onishkoff.itmo.IS1.dto.model.request;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CaveDtoRequest {
    private Long id;

    @NotNull
    private Double depth;

    @Positive
    private Double numberOfTreasures;

}

