package fr.eql.al35.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
public class ClothDTO implements Serializable{

	private static final long serialVersionUID = 1L;

    private Integer id;
    private String reference;
    private String name;
    private LocalDateTime refCreationDate;
    private LocalDateTime refDeletionDate;
    private Double price;
    private String description;
    private ProductTypeDTO productType;
    private List<StockDTO> stocks;
    private List<PhotoDTO> photos;
    private Boolean available;
    private Integer quantityInStock;

    public static ClothDTO initStockClothDTO(ClothDTO cloth) {
        cloth.setStocks(Arrays.asList(new StockDTO(), new StockDTO(), new StockDTO(), new StockDTO(), new StockDTO(), new StockDTO()));
        return cloth;
    }
}
