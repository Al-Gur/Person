package telran.java57.springdatajpa.person.controller;

import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.web.bind.annotation.*;
import telran.java57.springdatajpa.person.dto.PersonDto;
import telran.java57.springdatajpa.person.service.PersonService;

@RestController
@RequestMapping("/person")
@RequiredArgsConstructor
public class PersonController {
    final PersonService personService;

    @PostMapping
    public boolean addPerson(@RequestBody PersonDto personDto){
        return personService.addPerson(personDto);
    }

    @GetMapping("/{personId}")
    public PersonDto findPerson(@PathVariable String personId) throws BadRequestException {
        return personService.findPerson(personId);
    }

}
