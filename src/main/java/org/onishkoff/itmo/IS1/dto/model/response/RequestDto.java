package org.onishkoff.itmo.IS1.dto.model.response;


import lombok.Builder;
import lombok.Data;
import org.onishkoff.itmo.IS1.model.enums.Status;

import java.util.Date;

@Data
@Builder
public class RequestDto {
    Long id;
    UserDto user;
    Status status;
    Date requestDate;
    UserDto adminProcessedId;
    Date processedDate;
    String comment;

}
