package fr.eql.al35.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class UserTypeDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
    private Integer id;
    private String name;
}
