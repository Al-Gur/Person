package telran.java57.springdatajpa.person.service;

import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import telran.java57.springdatajpa.person.dao.PersonRepository;
import telran.java57.springdatajpa.person.dto.PersonDto;
import telran.java57.springdatajpa.person.dto.exceptions.PersonNotFoundException;
import telran.java57.springdatajpa.person.model.Person;

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
    public PersonDto findPerson(String personId) throws BadRequestException {
        Integer id;
        try {
             id = Integer.valueOf(personId);
        }
        catch (Exception e){
            throw new BadRequestException();
        }
        Person person = personRepository.findById(id)
                .orElseThrow(PersonNotFoundException::new);
        return modelMapper.map(person, PersonDto.class);
    }
}
