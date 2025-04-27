package telran.java57.springdatajpa.person.service;

import org.apache.coyote.BadRequestException;
import telran.java57.springdatajpa.person.dto.PersonDto;

public interface PersonService {
    boolean addPerson(PersonDto personDto);

    PersonDto findPerson(Integer personId);

    Iterable<PersonDto> findByCity(String city);

    Iterable<PersonDto> findByAges(Integer minAge, Integer maxAge);

    PersonDto updateName(Integer personId, String newName);

    Iterable<PersonDto> findByName(String city);
}
