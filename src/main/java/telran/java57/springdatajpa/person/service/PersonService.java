package telran.java57.springdatajpa.person.service;

import telran.java57.springdatajpa.person.dto.AddressDto;
import telran.java57.springdatajpa.person.dto.CityPopulationDto;
import telran.java57.springdatajpa.person.dto.PersonDto;

public interface PersonService {
    boolean addPerson(PersonDto personDto);

    PersonDto findPersonById(Integer id);

    PersonDto removePerson(Integer id);

    PersonDto updatePersonName(Integer id, String name);

    PersonDto updatePersonAddress(Integer id, AddressDto addressDto);

    PersonDto[] findPersonsByCity(String city);

    PersonDto[] findPersonsByName(String name);

    PersonDto[] findPersonsBetweenAge(Integer minAge, Integer maxAge);

    Iterable<CityPopulationDto> getCitiesPopulation();

}
