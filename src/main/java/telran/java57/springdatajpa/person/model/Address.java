package telran.java57.springdatajpa.person.model;

import jakarta.persistence.Embeddable;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Embeddable
public class Address {
    String city;
    String street;
    Integer building;
}
