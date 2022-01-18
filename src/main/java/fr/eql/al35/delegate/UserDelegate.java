package fr.eql.al35.delegate;

import java.util.List;

import fr.eql.al35.dto.*;

public interface UserDelegate {

	List<UserDTO> getAllUsers();
	UserDTO getUserById(Integer userId);
	List<AddressDTO> getAddressesByUserId(Integer userId);
	List<GenderDTO> getAllGenders();
	List<UserTypeDTO> getAllUserTypes();
	UserDTO saveUser(UserDTO user);
	UserDTO updateUser(Integer userId, UserDTO updatedUser);
	AddressDTO getAddressById(Integer addressId);
	PayModeDTO getPayModeById(Integer payModeId);
	UserDTO unsubscribeUser(Integer userId, UserDTO updatedUser);
	UserDTO subscribeUser(Integer userId, UserDTO updatedUser);
}
