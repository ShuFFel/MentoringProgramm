package com.instinctools.egor.mentoring.Web.web.soap;

import com.instinctools.egor.mentoring.Web.core.entity.User;
import com.instinctools.egor.mentoring.Web.core.factory.EntityFactory;
import com.instinctools.egor.mentoring.Web.core.services.BookService;
import com.instinctools.egor.mentoring.Web.core.services.UserService;
import com.instinctools.egor.mentoring.Web.web.dto.UserDTO;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService
public class UserServiceSOAPImpl{
    private EntityFactory factory;
    private UserService userService;
    private BookService bookService;

    public UserServiceSOAPImpl(){}

    public UserServiceSOAPImpl(EntityFactory factory, UserService userService, BookService bookService) {
        this.factory = factory;
        this.userService = userService;
        this.bookService = bookService;
    }

    @WebMethod
    public void createUser(UserDTO user) {
        User userToCreate = factory.createUser(user.getName(), user.getBirthDate());
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
