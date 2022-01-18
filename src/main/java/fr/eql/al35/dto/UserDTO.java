package fr.eql.al35.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString @EqualsAndHashCode
public class UserDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
    private Integer id;
    private String login;
    private String password;
    private String email;
    private String name;
    private String surname;
    private LocalDate birthDate;
    private String phoneNumber;
    private LocalDateTime subscribingDate;
    private LocalDateTime unsubscribingDate;
    private GenderDTO gender;
    private UserTypeDTO userType;
    private List<AddressDTO> addresses;
}
