package fr.eql.al35.delegate;

import java.util.List;

import fr.eql.al35.entity.Address;
import fr.eql.al35.entity.Gender;
import fr.eql.al35.entity.User;
import fr.eql.al35.entity.UserType;

public interface UserDelegate {

	List<User> getAllUsers();
	User getUserById(Integer userId);
	List<Address> getAddressesByUserId(Integer userId);
	List<Gender> getAllGenders();
	List<UserType> getAllUserTypes();

}
