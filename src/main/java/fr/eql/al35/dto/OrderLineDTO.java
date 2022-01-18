package fr.eql.al35.dto;

import lombok.*;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@Getter @Setter @AllArgsConstructor @ToString @EqualsAndHashCode
public class OrderLineDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer id;
	private Integer clothId;
	private ClothDTO cloth;
	private SizeDTO size;
	private Integer quantity;
	private Double price;
	private Double totalPrice = 0.0;
	private List<CustomDTO> customs;

	//Créer une liste de 3 customs vides (regle de gestion du front)
	public OrderLineDTO() {
		this.quantity = 0;
		this.price = 0.0;
		this.totalPrice = 0.0;
		this.customs = Arrays.asList(new CustomDTO(), 
				new CustomDTO(), 
				new CustomDTO());
	}
}
