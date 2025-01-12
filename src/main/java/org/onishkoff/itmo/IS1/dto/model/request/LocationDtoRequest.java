package org.onishkoff.itmo.IS1.dto.model.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LocationDtoRequest {
    private Long id;
    private Double x;
    private Integer y;
    private Long z;
}
