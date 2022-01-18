package fr.eql.al35.dto;

import java.io.Serializable;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString @EqualsAndHashCode
public class CustomDTO implements Serializable{

	private static final long serialVersionUID = 1L;

    private Integer id;
    private Integer designId;
    private DesignDTO design;
    private String locationLabel;


}
