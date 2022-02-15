package fr.eql.al35.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ProductsDisplayDTO {

    private List<ProductTypeDTO> categories;
    private List<ClothDTO> products;


}
