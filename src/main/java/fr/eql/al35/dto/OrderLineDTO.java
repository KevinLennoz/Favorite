package fr.eql.al35.dto;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class OrderLineDTO implements Serializable{

	private static final long serialVersionUID = 1L;

    private Integer id;
    private Integer clothId;
    private ClothDTO cloth;
    private SizeDTO size;
    private Integer quantity;
    private Double price;
    private List<CustomDTO> customs;
}
