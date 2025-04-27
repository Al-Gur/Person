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
    public PersonDto findPerson(@PathVariable Integer personId) {
        return personService.findPerson(personId);
    }

    @GetMapping("/city/{city}")
    public Iterable<PersonDto> findByCity(@PathVariable String city) {
        return personService.findByCity(city);
    }

    @GetMapping("/ages/{minAge}/{maxAge}")
    public Iterable<PersonDto> findByAges(@PathVariable Integer minAge, @PathVariable Integer maxAge) {
        return personService.findByAges(minAge, maxAge);
    }

    @PutMapping("/{personId}/name/{newName}")
    public PersonDto updateName (@PathVariable Integer personId, @PathVariable String newName) {
        return personService.updateName(personId, newName);
    }

    @GetMapping("/name/{name}")
    public Iterable<PersonDto> findByName(@PathVariable String name) {
        return personService.findByName(name);
    }
}
