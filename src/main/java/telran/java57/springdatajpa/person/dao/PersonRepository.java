package telran.java57.springdatajpa.person.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import telran.java57.springdatajpa.person.dto.CityPopulationDto;
import telran.java57.springdatajpa.person.model.Person;

import java.time.LocalDate;
import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Integer> {
    List<Person> findByNameIgnoreCase(String name);

    List<Person> findByAddressCityIgnoreCase(String city);

    List<Person> findByBirthDateBetween(LocalDate from, LocalDate to);

    @Query("select new telran.java57.springdatajpa.person.dto.CityPopulationDto(p.address.city, count(p))" +
            " from Person as p group by p.address.city")
    Iterable<CityPopulationDto> getCitiesPopulation();
}
