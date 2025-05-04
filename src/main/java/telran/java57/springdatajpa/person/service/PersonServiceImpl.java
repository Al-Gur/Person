package telran.java57.springdatajpa.person.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import telran.java57.springdatajpa.person.dao.PersonRepository;
import telran.java57.springdatajpa.person.dto.*;
import telran.java57.springdatajpa.person.dto.exception.PersonNotFoundException;
import telran.java57.springdatajpa.person.model.Address;
import telran.java57.springdatajpa.person.model.Child;
import telran.java57.springdatajpa.person.model.Employee;
import telran.java57.springdatajpa.person.model.Person;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService, CommandLineRunner {
    final PersonRepository personRepository;
    final ModelMapper modelMapper;

    @Override
    @Transactional
    public boolean addPerson(PersonDto personDto) {
        if (personRepository.existsById(personDto.getId())) {
            return false;
        }
        personRepository.save(modelMapper.map(personDto, Person.class));
        return true;
    }

    @Override
    public PersonDto findPersonById(Integer id) {
        Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);

//        System.out.println(person.getClass());
//        if(person.getClass() == Child.class) {
//            System.out.println(((Child) person).getKindergarten());
//        }

        return modelMapper.map(person, PersonDto.class);
    }


    @Override
    @Transactional
    public PersonDto removePerson(Integer id) {
        Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
        personRepository.delete(person);
        return modelMapper.map(person, PersonDto.class);
    }


    @Override
    @Transactional
    public PersonDto updatePersonName(Integer id, String name) {
        Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
        person.setName(name);
        personRepository.save(person);
        return modelMapper.map(person, PersonDto.class);
    }


    @Override
    @Transactional
    public PersonDto updatePersonAddress(Integer id, AddressDto addressDto) {
        Person person = personRepository.findById(id).orElseThrow(() -> new PersonNotFoundException());
        person.setAddress(modelMapper.map(addressDto, Address.class));
        personRepository.save(person);
        return modelMapper.map(person, PersonDto.class);
    }


    @Override
    @Transactional(readOnly = true)
    public PersonDto[] findPersonsByCity(String city) {
        return personRepository.findByAddressCityIgnoreCase(city)
                .stream()
                .map(p ->
                        p.getClass() == Child.class ?
                                modelMapper.map(p, ChildDto.class) :
                                p.getClass() == Employee.class ?
                                        modelMapper.map(p, EmployeeDto.class) :
                                        modelMapper.map(p, PersonDto.class))
                .toArray(PersonDto[]::new);
    }


    @Override
    @Transactional(readOnly = true)
    public PersonDto[] findPersonsByName(String name) {
        return personRepository.findByNameIgnoreCase(name)
                .stream()
                .map(p -> modelMapper.map(p, PersonDto.class))
                .toArray(PersonDto[]::new);
    }


    @Override
    @Transactional(readOnly = true)
    public PersonDto[] findPersonsBetweenAge(Integer minAge, Integer maxAge) {
        LocalDate from = LocalDate.now().minusYears(maxAge);
        LocalDate to = LocalDate.now().minusYears(minAge);
        return personRepository.findByBirthDateBetween(from, to)
                .stream()
                .map(p ->
                        p.getClass() == Child.class ?
                                modelMapper.map(p, ChildDto.class) :
                                p.getClass() == Employee.class ?
                                        modelMapper.map(p, EmployeeDto.class) :
                                        modelMapper.map(p, PersonDto.class))
                .toArray(PersonDto[]::new);
    }


    @Override
    @Transactional(readOnly = true)
    public Iterable<CityPopulationDto> getCitiesPopulation() {
        return personRepository.getCitiesPopulation();
    }

    @Override
    public void run(String... args) throws Exception {
        if (personRepository.count() == 0) {
            Person person = new Person(1000, "John", LocalDate.of(1993, 2, 14),
                    new Address("Tel Aviv", "Herzl", 2));
            Employee employee = new Employee(2000, "Peter", LocalDate.of(1998, 2, 14),
                    new Address("Rehovot", "Herzl", 2), 35_000, "Motorola");
            Child child = new Child(3000, "Mosche", LocalDate.of(2023, 4, 14),
                    new Address("Ashkelon", "Herzl", 2), "Shalom");
            personRepository.save(person);
            personRepository.save(employee);
            personRepository.save(child);
        }
    }
}
