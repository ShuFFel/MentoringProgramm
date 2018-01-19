package com.instinctools.egor.mentoring.web.web.soap;

import com.instinctools.egor.mentoring.web.core.entity.User;
import com.instinctools.egor.mentoring.web.core.services.UserService;
import com.instinctools.egor.mentoring.web.web.dto.UserDTO;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService
public class UserServiceSOAPImpl{
    private UserService userService;

    public UserServiceSOAPImpl(){}

    public UserServiceSOAPImpl(UserService userServices) {
        this.userService = userService;
    }

    @WebMethod
    public void createUser(UserDTO user) {
        User userToCreate = new User(user.getName(), user.getBirthDate());
        userService.createUser(userToCreate);
    }

    @WebMethod
    public void deleteUser(String id) {
        User userToDelete = userService.getUserById(id);
        userService.deleteUser(userToDelete);
    }

    @WebMethod
    public void updateUser(String id, UserDTO user) {
        User userToUpdate = userService.getUserById(id);
        userToUpdate.setUserName(user.getName());
        userToUpdate.setDateOfBirth(user.getBirthDate());
        userService.updateUser(userToUpdate);
    }

    @WebMethod
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
}
