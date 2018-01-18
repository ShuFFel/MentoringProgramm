package com.instinctools.egor.mentoring.Web.web.rest;

import com.instinctools.egor.mentoring.Web.core.entity.User;
import com.instinctools.egor.mentoring.Web.core.services.UserService;
import com.instinctools.egor.mentoring.Web.factories.GenericServices;
import com.instinctools.egor.mentoring.Web.web.dto.UserDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/UserService")
public class UserServiceRESTImpl {

    private SettingRESTService settings;
    private UserService userService;

    public UserServiceRESTImpl() {
    }

    public UserServiceRESTImpl(SettingRESTService settings) {
        this.settings = settings;
    }

    @GET
    @Path("/getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getAllUsers() {
        updateUserService();
        return userService.getAllUsers();
    }

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUser(UserDTO user) {
        User createdUser = GenericServices.getFactory().createUser(user.getName(), user.getBirthDate());
        updateUserService();
        userService.createUser(createdUser);
        String result = "Successfully created: " + userService.getUserById(createdUser.getId());
        return Response.status(201).entity(result).build();
    }

    @DELETE
    @Path("/delete/{id}")
    public void deleteUser(@PathParam("id") String id) {
        updateUserService();
        User userToDelete = userService.getUserById(id);
        userService.deleteUser(userToDelete);
    }

    @PUT
    @Path("/update/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateUser(@PathParam("id") String id, UserDTO user) {
        updateUserService();
        User userToUpdate = userService.getUserById(id);
        userToUpdate.setUserName(user.getName());
        userToUpdate.setDateOfBirth(user.getBirthDate());
        userService.updateUser(userToUpdate);
    }

    private void updateUserService(){
        userService = GenericServices.getUserService(settings.getStorageType());
    }

}
