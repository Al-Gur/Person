package telran.java57.springdatajpa.person.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CityPopulationDto {
    String city;
    Long population;

}
