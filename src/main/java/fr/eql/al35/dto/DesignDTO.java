package fr.eql.al35.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
public class DesignDTO implements Serializable{

	private static final long serialVersionUID = 1L;

    private Integer id;
    private String name;
    private String color;
    private Double price;
    private DesignTypeDTO designType;
    private List<PhotoDTO> photos;

}
