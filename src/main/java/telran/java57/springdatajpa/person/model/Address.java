package telran.java57.springdatajpa.person.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Address {
    String city;
    String street;
    Integer building;
}
