package com.instinctools.egor.mentoring.Web.web.rest;

import com.instinctools.egor.mentoring.Web.core.entity.Book;
import com.instinctools.egor.mentoring.Web.core.entity.User;
import com.instinctools.egor.mentoring.Web.core.services.BookService;
import com.instinctools.egor.mentoring.Web.core.services.UserService;
import com.instinctools.egor.mentoring.Web.factories.GenericServices;
import com.instinctools.egor.mentoring.Web.web.dto.BookDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/BookService")
public class BookServiceRESTImpl {

    private SettingRESTService settings;
    private BookService bookService;
    private UserService userService;

    public BookServiceRESTImpl() {
    }

    public BookServiceRESTImpl(SettingRESTService settings) {
        this.settings = settings;
    }

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createBook(BookDTO book) {
        updateServices();
        Book bookToCreate = GenericServices.getFactory().createBook(book.getName(), book.getAuthor());
        bookService.createBook(bookToCreate);
        String result = "Successfully created: "
                + bookService.getBookById(bookToCreate.getId());
        return Response.status(201).entity(result).build();
    }

    @DELETE
    @Path("/delete/{id}")
    public Response deleteBook(@PathParam("id") String id) {
        updateServices();
        Book bookToDelete = bookService.getBookById(id);
        bookService.deleteBook(bookToDelete);
        String result = "Successfully deleted! " + bookToDelete.toString();
        return Response.status(201).entity(result).build();
    }

    @PUT
    @Path("/update/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateBook(String id, BookDTO book) {
        updateServices();
        Book bookToUpdate = bookService.getBookById(id);
        bookToUpdate.setAuthor(book.getAuthor());
        bookToUpdate.setName(book.getName());
        bookService.updateBook(bookToUpdate);
    }

    @PUT
    @Path("/assign/{userId}/{bookId}")
    public void assignBook(@PathParam("userId") String userId, @PathParam("bookId") String bookId) {
        updateServices();
        Book bookToAssign = bookService.getBookById(bookId);
        User userToAssign = userService.getUserById(userId);
        bookService.assignBook(userToAssign, bookToAssign);
    }

    @GET
    @Path("/getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Book> getAllBooks() {
        updateServices();
        return bookService.getAllBooks();
    }

    private void updateServices(){
        bookService = GenericServices.getBookService(settings.getStorageType());
        userService = GenericServices.getUserService(settings.getStorageType());
    }

}
