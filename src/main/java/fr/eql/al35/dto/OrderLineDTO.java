package fr.eql.al35.dto;

import lombok.*;

import java.util.List;

@Data
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class OrderLineDTO {

    private Integer id;
    private Integer clothId;
    private Integer quantity;
    private Double price;
    private List<CustomDTO> customs;
}
