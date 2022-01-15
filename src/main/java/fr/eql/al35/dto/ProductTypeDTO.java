package fr.eql.al35.dto;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ProductTypeDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
    private String name;
    private List<LocationDTO> locations;

}
