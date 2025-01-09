package org.onishkoff.itmo.IS1.dto.model.request;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CaveDtoRequest {
    private Long id;
    private Double depth;
    private Double numberOfTreasures;

}

