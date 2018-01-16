package com.instinctools.egor.mentoring.Web.web.rest;

import com.instinctools.egor.mentoring.Web.core.entity.User;
import com.instinctools.egor.mentoring.Web.core.factory.EntityFactory;
import com.instinctools.egor.mentoring.Web.core.services.BookService;
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

    public UserServiceRESTImpl() {
    }

    public UserServiceRESTImpl(SettingRESTService settings) {
        this.settings = settings;
    }

    @GET
    @Path("/getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getAllUsers() {
        return GenericServices.getUserService(settings.getStorageType()).getAllUsers();
    }

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUser(UserDTO user) {
        User createdUser = GenericServices.getFactory().createUser(user.getName(), user.getBirthDate());
        GenericServices.getUserService(settings.getStorageType()).createUser(createdUser);
        String result = "Successfully created: " +
                GenericServices.getUserService(settings.getStorageType()).getUserById(createdUser.getId());
        return Response.status(201).entity(result).build();
    }

    @DELETE
    @Path("/delete/{id}")
    public void deleteUser(@PathParam("id") String id) {
        User userToDelete = GenericServices.getUserService(settings.getStorageType()).getUserById(id);
        GenericServices.getUserService(settings.getStorageType()).deleteUser(userToDelete);
    }


}
