package telran.java57.springdatajpa.person.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import telran.java57.springdatajpa.person.model.Person;

public interface PersonRepository extends JpaRepository<Person, Integer> {
}
