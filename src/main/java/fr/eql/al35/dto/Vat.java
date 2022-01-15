package fr.eql.al35.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Vat implements Serializable{

	private static final long serialVersionUID = 1L;
	
    private Integer id;
    private Double rate;
}
