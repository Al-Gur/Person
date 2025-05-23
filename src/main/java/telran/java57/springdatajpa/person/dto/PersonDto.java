package telran.java57.springdatajpa.person.dto;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class PersonDto {
    Integer id;
    String name;
    LocalDate birthDate;
    AddressDto address;
}
