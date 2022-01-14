package fr.eql.al35.delegate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import fr.eql.al35.entity.Address;
import fr.eql.al35.entity.Gender;
import fr.eql.al35.entity.User;
import fr.eql.al35.entity.UserType;
import fr.eql.al35.util.WebclientGenericResponse;

@Service
public class UserDelegateImpl implements UserDelegate {
	
	private WebClient userWebclient;
	private static final String USER_ENDPOINT = "/users/";

	@Autowired
	public UserDelegateImpl(@Qualifier("userWebclient") WebClient userWebclient) {
		this.userWebclient = userWebclient;
	}
	
	@Override
	public List<User> getAllUsers() {
		return WebclientGenericResponse.getListResponse(userWebclient, USER_ENDPOINT, new User());
	}
	
	@Override
	public User getUserById(Integer userId) {
		return WebclientGenericResponse.getResponse(userWebclient, USER_ENDPOINT + userId, new User());
	}
	
	@Override
	public List<Address> getAddressesByUserId(Integer userId) {
		return WebclientGenericResponse.getListResponse(userWebclient, USER_ENDPOINT + userId + "/addresses", new Address());
	}

	@Override
	public List<Gender> getAllGenders() {
		return WebclientGenericResponse.getListResponse(userWebclient, USER_ENDPOINT + "/genders", new Gender());
	}
	
	@Override
	public List<UserType> getAllUserTypes() {
		return WebclientGenericResponse.getListResponse(userWebclient, USER_ENDPOINT + "/user-types", new UserType());
	}
}
