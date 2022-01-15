package fr.eql.al35.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class CityDTO {

    private Integer id;
    private Integer zipCode;
    private String name;
}
