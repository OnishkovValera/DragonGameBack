package org.onishkoff.itmo.IS1.service;

import lombok.RequiredArgsConstructor;
import org.onishkoff.itmo.IS1.exception.PersonNotFoundException;
import org.onishkoff.itmo.IS1.model.Person;
import org.onishkoff.itmo.IS1.repository.PersonRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;

    public Person findPersonById(long id) {
        return personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
    }


}
