package org.onishkoff.itmo.IS1.dto.model.response;

import lombok.Builder;
import lombok.Data;
import org.onishkoff.itmo.IS1.model.Person;
import org.springframework.data.domain.Page;

@Data
@Builder
public class PersonPageDto {
    Page<Person> persons;
}
