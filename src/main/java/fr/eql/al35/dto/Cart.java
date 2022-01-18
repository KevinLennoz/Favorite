package fr.eql.al35.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString @EqualsAndHashCode @NoArgsConstructor @AllArgsConstructor
public class Cart implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Set<OrderLineDTO> orderLines = new HashSet<>();
	private int clothQuantity;
	private double totalPrice;
	
}
