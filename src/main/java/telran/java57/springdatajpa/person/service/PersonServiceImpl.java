package telran.java57.springdatajpa.person.service;

import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import telran.java57.springdatajpa.person.dao.PersonRepository;
import telran.java57.springdatajpa.person.dto.PersonDto;
import telran.java57.springdatajpa.person.dto.exceptions.PersonNotFoundException;
import telran.java57.springdatajpa.person.model.Person;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService{
    final PersonRepository personRepository;
    final ModelMapper modelMapper;

    @Override
    public boolean addPerson(PersonDto personDto) {
        if(personRepository.existsById(personDto.getId())){
            return false;
        }
        personRepository.save(modelMapper.map(personDto, Person.class));
        return true;
    }

    @Override
    public PersonDto findPerson(Integer personId) {
        Person person = personRepository.findById(personId)
                .orElseThrow(PersonNotFoundException::new);
        return modelMapper.map(person, PersonDto.class);
    }

    @Override
    public Iterable<PersonDto> findByCity(String city) {
        return ((Collection<Person>) personRepository.findPersonByAddress_City_IgnoreCase(city))
                .stream()
                .map(person -> modelMapper.map(person, PersonDto.class))
                .toList();
    }

    @Override
    public Iterable<PersonDto> findByAges(Integer minAge, Integer maxAge) {
        LocalDate now = LocalDate.now();
        LocalDate minBirthDate = now.minusYears(maxAge);
        LocalDate maxBirthDate = now.minusYears(minAge);
        return ((Collection<Person>) personRepository.findPersonByBirthDateBetween(minBirthDate, maxBirthDate))
                .stream()
                .map(person -> modelMapper.map(person, PersonDto.class))
                .toList();
    }
}
