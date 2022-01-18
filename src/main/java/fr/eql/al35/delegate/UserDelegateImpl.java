package fr.eql.al35.delegate;

import java.util.List;

import fr.eql.al35.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

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
	 * PATCH :  "/users/userId"
	 */
	@Override
	public UserDTO updateUser(Integer userId, UserDTO updatedUser) {
		return WebClientGenericResponse.patchResponse(userWebclient,
				USER_ENDPOINT + '/' + userId,
				updatedUser);
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

	/*
	 * GET :  "/users/paymodes/{payModeId}"
	 */
	@Override
	public PayModeDTO getPayModeById(Integer payModeId) {
		return WebClientGenericResponse.getResponse(userWebclient,
				USER_ENDPOINT + "/paymodes" + "/" + payModeId,
				new PayModeDTO());
	}

	/*
	 * PATCH : /users/{userId}/unsubscribe
	 */
	@Override
	public UserDTO unsubscribeUser(Integer userId, UserDTO updatedUser) {
		return WebClientGenericResponse.patchResponse(userWebclient,
				USER_ENDPOINT + "/" + userId + "/unsubscribe",
				updatedUser);
	}

	/*
	 * PATCH : /users/{userId}/subscribe
	 */
	@Override
	public UserDTO subscribeUser(Integer userId, UserDTO updatedUser) {
		return WebClientGenericResponse.patchResponse(userWebclient,
				USER_ENDPOINT + "/" + userId + "/subscribe",
				updatedUser);
	}
}
