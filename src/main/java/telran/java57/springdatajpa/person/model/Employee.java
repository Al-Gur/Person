package telran.java57.springdatajpa.person.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Employee extends Person{
    String company;
    int salary;

    public Employee(Integer id, String name, LocalDate birthDate, Address address, int salary, String company) {
        super(id, name, birthDate, address);
        this.salary = salary;
        this.company = company;
    }
}
