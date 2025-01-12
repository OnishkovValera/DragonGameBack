package org.onishkoff.itmo.IS1.dto.model.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.ArrayList;

@Data
public class TeamDtoRequest {

    Long id;

    @NotNull
    @Size(max = 30, min = 6)
    String name;

    CaveDtoRequest cave;

    ArrayList<Long> members;
}
