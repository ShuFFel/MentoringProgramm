package com.instinctools.egor.mentoring.Web.web.rest;

import com.instinctools.egor.mentoring.Web.core.entity.Book;
import com.instinctools.egor.mentoring.Web.core.entity.User;
import com.instinctools.egor.mentoring.Web.factories.GenericServices;
import com.instinctools.egor.mentoring.Web.web.dto.BookDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/BookService")
public class BookServiceRESTImpl {

    private SettingRESTService settings;

    public BookServiceRESTImpl() {
    }

    public BookServiceRESTImpl(SettingRESTService settings) {
        this.settings = settings;
    }

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createBook(BookDTO book) {
        Book bookToCreate = GenericServices.getFactory().createBook(book.getName(), book.getAuthor());
        GenericServices.getBookService(settings.getStorageType()).createBook(bookToCreate);
        String result = "Successfully created: "
                + GenericServices.getBookService(settings.getStorageType()).getBookById(bookToCreate.getId());
        return Response.status(201).entity(result).build();
    }

    @DELETE
    @Path("/delete/{id}")
    public Response deleteBook(@PathParam("id") String id) {
        Book bookToDelete = GenericServices.getBookService(settings.getStorageType()).getBookById(id);
        GenericServices.getBookService(settings.getStorageType()).deleteBook(bookToDelete);
        String result = "Successfully deleted! " + bookToDelete.toString();
        return Response.status(201).entity(result).build();
    }

    @PUT
    @Path("/update/{id}")
    public void updateBook(String id) {
        Book bookToUpdate = GenericServices.getBookService(settings.getStorageType()).getBookById(id);
        GenericServices.getBookService(settings.getStorageType()).updateBook(bookToUpdate);
    }

    @PUT
    @Path("/assign/{userId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void assignBook(@PathParam("userId") String userId, String bookId) {
        Book bookToAssign = GenericServices.getBookService(settings.getStorageType()).getBookById(bookId);
        User userToAssign = GenericServices.getUserService(settings.getStorageType()).getUserById(userId);
        GenericServices.getBookService(settings.getStorageType()).assignBook(userToAssign, bookToAssign);
    }

    @GET
    @Path("/getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Book> getAllBooks() {
        return GenericServices.getBookService(settings.getStorageType()).getAllBooks();
    }

    public SettingRESTService getSettings() {
        return settings;
    }
}
