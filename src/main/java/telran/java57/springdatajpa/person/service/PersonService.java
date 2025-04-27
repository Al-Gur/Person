package telran.java57.springdatajpa.person.service;

import org.apache.coyote.BadRequestException;
import telran.java57.springdatajpa.person.dto.PersonDto;

public interface PersonService {
    boolean addPerson(PersonDto personDto);

    PersonDto findPerson(String personId) throws BadRequestException;
}
