package telran.java57.springdatajpa.person.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import telran.java57.springdatajpa.person.model.Person;

import java.time.LocalDate;
import java.util.stream.Stream;

public interface PersonRepository extends JpaRepository<Person, Integer> {
    Iterable<Person> findPersonByAddress_City_IgnoreCase(String city);

    Iterable<Person> findPersonByBirthDateBetween(LocalDate date1, LocalDate date2);

    Iterable<Person> findPersonByName_IgnoreCase(String city);
}
