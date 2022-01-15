package fr.eql.al35.delegate;

import java.util.List;

import fr.eql.al35.dto.AddressDTO;
import fr.eql.al35.dto.GenderDTO;
import fr.eql.al35.dto.UserDTO;
import fr.eql.al35.dto.UserTypeDTO;

public interface UserDelegate {

	List<UserDTO> getAllUsers();
	UserDTO getUserById(Integer userId);
	List<AddressDTO> getAddressesByUserId(Integer userId);
	List<GenderDTO> getAllGenders();
	List<UserTypeDTO> getAllUserTypes();
	UserDTO saveUser(UserDTO user);

}
