package fr.eql.al35.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString @EqualsAndHashCode @AllArgsConstructor
public class Cart implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Set<OrderLineDTO> orderLines;
	private Integer clothQuantity;
	private Double totalPrice;
	
	public Cart() {
		this.orderLines = new HashSet<>();
		this.clothQuantity = 0;
		this.totalPrice = 0.0;
	}
}
