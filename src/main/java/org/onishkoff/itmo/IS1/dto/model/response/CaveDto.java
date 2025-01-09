package org.onishkoff.itmo.IS1.dto.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CaveDto{
    private Long id;
    private Long depth;
    private Long numberOfTreasures;

}
