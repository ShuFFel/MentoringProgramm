package com.instinctools.egor.mentoring.Web.web.rest;

import com.instinctools.egor.mentoring.Web.core.entity.Book;
import com.instinctools.egor.mentoring.Web.core.entity.User;
import com.instinctools.egor.mentoring.Web.core.factory.EntityFactory;
import com.instinctools.egor.mentoring.Web.core.services.BookService;
import com.instinctools.egor.mentoring.Web.core.services.UserService;
import com.instinctools.egor.mentoring.Web.web.dto.BookDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/BookService")
public class BookServiceRESTImpl {

    private final BookService bookService;
    private final UserService userService;
    private final EntityFactory entityFactory;

    public BookServiceRESTImpl(BookService bookService, UserService userService, EntityFactory entityFactory) {
        this.bookService = bookService;
        this.userService = userService;
        this.entityFactory = entityFactory;
    }

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createBook(BookDTO book) {
        Book bookToCreate = entityFactory.createBook(book.getName(), book.getAuthor());
        bookService.createBook(bookToCreate);
        String result = "Successfully created: " + bookService.getBookById(bookToCreate.getId());
        return Response.status(201).entity(result).build();
    }

    @DELETE
    @Path("/delete/{id}")
    public void deleteBook(@PathParam("id") String id) {
        Book bookToDelete = bookService.getBookById(id);
        bookService.deleteBook(bookToDelete);
    }

    @PUT
    @Path("/update/{id}")
    public void updateBook(String id) {
        Book bookToUpdate = bookService.getBookById(id);
        bookService.updateBook(bookToUpdate);
    }

    @PUT
    @Path("/assign")
    @Consumes(MediaType.APPLICATION_JSON)
    public void assignBook(String userId, String bookId) {
        Book bookToAssign = bookService.getBookById(bookId);
        User userToAssign = userService.getUserById(userId);
        bookService.assignBook(userToAssign, bookToAssign);
    }

    @GET
    @Path("/getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }
}
