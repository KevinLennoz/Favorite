package fr.eql.al35.service;

import java.util.List;

import fr.eql.al35.delegate.GlobalDelegate;
import fr.eql.al35.delegate.UserDelegate;
import fr.eql.al35.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.eql.al35.iservice.AdminIService;

@Service
public class AdminService implements AdminIService {

    private UserDelegate userDelegate;
    private GlobalDelegate globalDelegate;

    @Autowired
    public AdminService(UserDelegate userDelegate, GlobalDelegate globalDelegate) {
        this.userDelegate = userDelegate;
        this.globalDelegate = globalDelegate;
    }

    @Override
    public List<UserDTO> displayAllUsers() {
        return globalDelegate.getAllUsers();
    }

    @Override
    public UserDTO displayUser(Integer userId) {
        return globalDelegate.getUserById(userId);
    }

    @Override
    public UserDTO unsubscribeUser(Integer userId) {
        UserDTO userToUnsubscribe = userDelegate.getUserById(userId);
        return userDelegate.unsubscribeUser(userId, userToUnsubscribe);
    }

    @Override
    public UserDTO subscribeUser(Integer userId) {
        UserDTO userToSubscribe = userDelegate.getUserById(userId);
        return userDelegate.subscribeUser(userId, userToSubscribe);
    }

    @Override
    public UserDTO updateUser(Integer userId, UserDTO user) {
        return userDelegate.updateUser(userId, user);
    }

}
