package fr.eql.al35.dto;

import java.io.Serializable;

import lombok.*;

@Data
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class CustomDTO implements Serializable{

	private static final long serialVersionUID = 1L;

    private Integer id;
    private Integer designId;
    private String locationLabel;


}
