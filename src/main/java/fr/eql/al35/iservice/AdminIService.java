package fr.eql.al35.iservice;

import fr.eql.al35.dto.UserDTO;

import java.util.List;


public interface AdminIService {


    List<UserDTO> displayAllUsers();

    UserDTO displayUser(Integer userId);

    UserDTO unsubscribeUser(Integer userId);

    UserDTO subscribeUser(Integer userId);

    UserDTO updateUser(Integer userId, UserDTO user);
}
