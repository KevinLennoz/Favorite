package fr.eql.al35.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.eql.al35.delegate.UserDelegate;
import fr.eql.al35.entity.Address;
import fr.eql.al35.entity.Gender;
import fr.eql.al35.entity.User;
import fr.eql.al35.entity.UserType;
import fr.eql.al35.iservice.AccountIService;
import fr.eql.al35.repository.UserTypeIRepo;


@Service
public class AccountService implements AccountIService {

	private final UserDelegate userDelegate;
	
	@Autowired
	UserTypeIRepo userTypeRepository;

	public AccountService(UserDelegate userDelegate) {
		this.userDelegate = userDelegate;
	}

	@Override
	public List<User> displayAllUsers() {
		return userDelegate.getAllUsers();
	}

	@Override
	public User getUser3() {
		return userDelegate.getUserById(3);
	}

	@Override
	public List<Address> getAddressByUser(User user) {
		return userDelegate.getAddressesByUserId(user.getId());
	}

	@Override
	public User getAdminAccount() {
		return userDelegate.getUserById(2);
	}
	
	@Override
	public List<Gender> getAllGenders(){
		return userDelegate.getAllGenders();
	}

	@Override
	public List<UserType> getAllUserTypes() {
		return userDelegate.getAllUserTypes();
	}
}
