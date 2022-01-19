package fr.eql.al35.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
public class StockDTO implements Serializable{

	private static final long serialVersionUID = 1L;

    private Integer id;
    private Integer quantity;
    private SizeDTO size;

    public StockDTO() {
        this.setQuantity(0);
        this.setSize(new SizeDTO());
    }

}
