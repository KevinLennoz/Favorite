package fr.eql.al35.iservice;

import java.util.List;

import fr.eql.al35.dto.AddressDTO;
import fr.eql.al35.dto.GenderDTO;
import fr.eql.al35.dto.UserDTO;
import fr.eql.al35.dto.UserTypeDTO;

public interface AccountIService {

	List<UserDTO> displayAllUsers();

	UserDTO getUserById(Integer userId);

	List<AddressDTO> getAddressByUser(UserDTO user);

	UserDTO getAdminAccount();

	List<GenderDTO> getAllGenders();

	List<UserTypeDTO> getAllUserTypes();

	void saveUser(UserDTO user);

}

