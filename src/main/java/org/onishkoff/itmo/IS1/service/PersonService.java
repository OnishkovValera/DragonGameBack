package org.onishkoff.itmo.IS1.service;

import lombok.RequiredArgsConstructor;
import org.onishkoff.itmo.IS1.dto.model.request.PersonDtoRequest;
import org.onishkoff.itmo.IS1.dto.model.response.PersonDto;
import org.onishkoff.itmo.IS1.exception.PersonNotFoundException;
import org.onishkoff.itmo.IS1.model.Person;
import org.onishkoff.itmo.IS1.repository.PersonRepository;
import org.onishkoff.itmo.IS1.util.Mapper;
import org.onishkoff.itmo.IS1.util.SecurityUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class PersonService {

    private final UserService userService;
    private final PersonRepository personRepository;
    private final Mapper mapper;
    private final SecurityUtil securityUtil;

    public PersonDto getPersonDto(Long id) {
        return mapper.toPersonDto(findPersonById(id));
    }

    public Person findPersonById(long id) {
        return personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
    }

    @Transactional
    public PersonDto save(PersonDtoRequest person){
        Person personToSave = mapper.toPerson(person);
        personToSave.setOwner(securityUtil.getUserFromContext());
        Person personResponse = personRepository.save(personToSave);
        return mapper.toPersonDto(personResponse);

    }


    public Page<Person> getAllPersons(Pageable pageable) {
        String login = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long ownerId = userService.findByLogin(login).orElseThrow(PersonNotFoundException::new).getId();
        return personRepository.findAllByOwnerId(ownerId, pageable);

    }

}
