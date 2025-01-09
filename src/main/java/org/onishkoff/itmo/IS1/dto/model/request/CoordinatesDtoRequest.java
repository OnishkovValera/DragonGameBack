package org.onishkoff.itmo.IS1.dto.model.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CoordinatesDtoRequest {

    private Long id;

    @Max(98)
    private Long x;
    @Min(-462)
    private Long y;
}
