package fr.eql.al35.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.eql.al35.delegate.UserDelegate;
import fr.eql.al35.dto.AddressDTO;
import fr.eql.al35.dto.GenderDTO;
import fr.eql.al35.dto.UserDTO;
import fr.eql.al35.dto.UserTypeDTO;
import fr.eql.al35.iservice.AccountIService;


@Service
public class AccountService implements AccountIService {

	private final UserDelegate userDelegate;

	@Autowired
	public AccountService(UserDelegate userDelegate) {
		this.userDelegate = userDelegate;
	}

	@Override
	public List<UserDTO> displayAllUsers() {
		return userDelegate.getAllUsers();
	}
	
	@Override
	public void saveUser(UserDTO user) {
		userDelegate.saveUser(user);
	}

	@Override
	public UserDTO getUser3() {
		return userDelegate.getUserById(3);
	}

	@Override
	public List<AddressDTO> getAddressByUser(UserDTO user) {
		return userDelegate.getAddressesByUserId(user.getId());
	}

	@Override
	public UserDTO getAdminAccount() {
		return userDelegate.getUserById(2);
	}
	
	@Override
	public List<GenderDTO> getAllGenders(){
		return userDelegate.getAllGenders();
	}

	@Override
	public List<UserTypeDTO> getAllUserTypes() {
		return userDelegate.getAllUserTypes();
	}
}
