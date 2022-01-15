package fr.eql.al35.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class AddressDTO {

    private Integer id;
    private String name;
    private String street;
    private UserDTO user;
    private CityDTO city;
}
