package com.instinctools.egor.mentoring.Web.web.rest;

import com.instinctools.egor.mentoring.Web.core.entity.User;
import com.instinctools.egor.mentoring.Web.core.factory.EntityFactory;
import com.instinctools.egor.mentoring.Web.core.services.BookService;
import com.instinctools.egor.mentoring.Web.core.services.UserService;
import com.instinctools.egor.mentoring.Web.web.dto.UserDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/UserService")
public class UserServiceRESTImpl {

    private BookService bookService;
    private UserService userService;
    private EntityFactory factory;

    public UserServiceRESTImpl(BookService bookService, UserService userService, EntityFactory factory) {
        this.bookService = bookService;
        this.userService = userService;
        this.factory = factory;
    }

    @GET
    @Path("/getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUser(UserDTO user) {
        User createdUser = factory.createUser(user.getName(), user.getBirthDate());
        userService.createUser(createdUser);
        String result = "Successfully created: " + userService.getUserById(createdUser.getId());
        return Response.status(201).entity(result).build();
    }

    @DELETE
    @Path("/delete/{id}")
    public void deleteUser(@PathParam("id") String id) {
        User userToDelete = userService.getUserById(id);
        userService.deleteUser(userToDelete);
    }


}
