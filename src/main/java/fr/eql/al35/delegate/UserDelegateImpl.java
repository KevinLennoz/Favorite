package fr.eql.al35.delegate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import fr.eql.al35.dto.AddressDTO;
import fr.eql.al35.dto.GenderDTO;
import fr.eql.al35.dto.UserDTO;
import fr.eql.al35.dto.UserTypeDTO;
import fr.eql.al35.util.WebClientGenericResponse;

@Service
public class UserDelegateImpl implements UserDelegate {

	private final WebClient userWebclient;
	private static final String USER_ENDPOINT = "/users";

	@Autowired
	public UserDelegateImpl(@Qualifier("userWebclient") WebClient userWebclient) {
		this.userWebclient = userWebclient;
	}

	/*
	 * GET :  "/users"
	 */
	@Override
	public List<UserDTO> getAllUsers() {
		return WebClientGenericResponse.getListResponse(userWebclient,
														USER_ENDPOINT,
														new UserDTO());
	}

	/*
	 * GET :  "/users/{userId}"
	 */
	@Override
	public UserDTO getUserById(Integer userId) {
		return WebClientGenericResponse.getResponse(userWebclient,
													USER_ENDPOINT  + "/" + userId,
													new UserDTO());
	}

	/*
	 * GET :  "/users/{userId}/addresses"
	 */
	@Override
	public List<AddressDTO> getAddressesByUserId(Integer userId) {
		return WebClientGenericResponse.getListResponse(userWebclient,
														USER_ENDPOINT + "/" + userId + "/addresses",
														new AddressDTO());
	}

	/*
	 * GET :  "/users/genders"
	 */
	@Override
	public List<GenderDTO> getAllGenders() {
		return WebClientGenericResponse.getListResponse(userWebclient,
														USER_ENDPOINT + "/genders",
														new GenderDTO());
	}

	/*
	 * GET :  "/users/user-types"
	 */
	@Override
	public List<UserTypeDTO> getAllUserTypes() {
		return WebClientGenericResponse.getListResponse(userWebclient,
														USER_ENDPOINT + "/user-types",
														new UserTypeDTO());
	}

	/*
	 * POST :  "/users"
	 */
	@Override
	public UserDTO saveUser(UserDTO user) {
		return WebClientGenericResponse.postResponse(userWebclient,
													 USER_ENDPOINT,
													 new UserDTO());
	}

	/*
	 * GET :  "/users/addresses/addressId"
	 */
	@Override
	public AddressDTO getAddressById(Integer addressId) {
		return WebClientGenericResponse.getResponse(userWebclient,
				USER_ENDPOINT + "/addresses" + "/" + addressId,
				new AddressDTO());
	}
}
