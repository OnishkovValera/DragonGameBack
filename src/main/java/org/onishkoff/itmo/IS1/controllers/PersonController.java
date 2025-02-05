package org.onishkoff.itmo.IS1.controllers;

import lombok.RequiredArgsConstructor;
import org.onishkoff.itmo.IS1.dto.model.request.PersonDtoRequest;
import org.onishkoff.itmo.IS1.dto.model.response.PersonDto;
import org.onishkoff.itmo.IS1.service.PersonService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/person")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    @PostMapping()
    public ResponseEntity<PersonDto> createPerson(@RequestBody @Validated PersonDtoRequest person) {
        PersonDto responsePerson = personService.saveDto(person);
        return ResponseEntity.status(HttpStatus.CREATED).body(responsePerson);

    }

    @GetMapping()
    public ResponseEntity<Page<PersonDto>> getAllPersons(@RequestParam(defaultValue = "0") Integer page,
                                                         @RequestParam(defaultValue = "10") Integer size,
                                                         @RequestParam(defaultValue = "id") String sortColumn,
                                                         @RequestParam(defaultValue = "") String filter,
                                                         @RequestParam(defaultValue = "asc") String order,
                                                         @RequestParam(defaultValue = "false") Boolean userPersonOnly
                                                         ){
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(order), sortColumn));
        Page<PersonDto> personsPage = personService.getAllPersons(pageable, filter, userPersonOnly);
        return ResponseEntity.ok().body(personsPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonDto> getPerson(@PathVariable Long id) {
        return ResponseEntity.ok().body(personService.getPerson(id));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("@securityUtil.hasAccess(@personService.findPersonById(#id).getOwner().getId())")
    public ResponseEntity<Void> deletePerson(@PathVariable(name = "id") Long id) {
        personService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    @PreAuthorize("@securityUtil.hasAccess(@personService.findPersonById(#person.getId()).getOwner().getId())")
    public ResponseEntity<PersonDto> updatePerson(@RequestBody @Validated PersonDtoRequest person) {
        return ResponseEntity.ok().body(personService.update(person));

    }


}
