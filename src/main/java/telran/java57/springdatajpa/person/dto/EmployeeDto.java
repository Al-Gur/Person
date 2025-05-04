package telran.java57.springdatajpa.person.dto;

import lombok.Getter;

@Getter
public class EmployeeDto extends PersonDto {
    String company;
    int salary;
}
