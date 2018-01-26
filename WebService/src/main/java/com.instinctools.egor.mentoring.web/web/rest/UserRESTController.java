package com.instinctools.egor.mentoring.web.web.rest;

import com.instinctools.egor.mentoring.web.core.entity.User;
import com.instinctools.egor.mentoring.web.core.services.UserService;
import com.instinctools.egor.mentoring.web.web.dto.UserDTO;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/users")
public class UserRESTController {
    private UserService userService;

    public UserRESTController() {
    }

    public UserRESTController(final UserService userService) {
        this.userService = userService;
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
    public Response createUser(final UserDTO user) {
        User createdUser = user.toModel();
        userService.createUser(createdUser);
        String result = "Successfully created: " + userService.getUserById(createdUser.getId());
        return Response.status(Response.Status.OK).entity(result).build();
    }

    @DELETE
    @Path("/delete/{id}")
    public void deleteUser(@PathParam("id") final String id) {
        User userToDelete = userService.getUserById(id);
        userService.deleteUser(userToDelete);
    }

    @PUT
    @Path("/update/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateUser(@PathParam("id") final String id, final UserDTO user) {
        User userToUpdate = userService.getUserById(id);
        userToUpdate.setUserName(user.getName());
        userToUpdate.setDateOfBirth(user.getBirthDate());
        userService.updateUser(userToUpdate);
    }
}
