package org.onishkoff.itmo.IS1.dto.model.request;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DragonHeadDtoRequest {
    private Long id;
    private Integer toothCount;
}
