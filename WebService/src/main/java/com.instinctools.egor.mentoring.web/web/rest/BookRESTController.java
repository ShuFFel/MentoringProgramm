package com.instinctools.egor.mentoring.web.web.rest;

import com.instinctools.egor.mentoring.web.core.entity.Book;
import com.instinctools.egor.mentoring.web.core.entity.User;
import com.instinctools.egor.mentoring.web.core.services.BookService;
import com.instinctools.egor.mentoring.web.core.services.UserService;
import com.instinctools.egor.mentoring.web.web.dto.BookDTO;

import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/books")
public class BookRESTController {
    private BookService bookService;
    private UserService userService;

    public BookRESTController() {
    }

    public BookRESTController(final BookService bookService, final UserService userService) {
        this.bookService = bookService;
        this.userService = userService;
    }

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createBook(final BookDTO book) {
        Book bookToCreate = book.toModel();
        bookService.createBook(bookToCreate);
        String result = "Successfully created: "
                        + bookService.getBookById(bookToCreate.getId());
        return Response.status(Response.Status.OK).entity(result).build();
    }

    @DELETE
    @Path("/delete/{id}")
    public Response deleteBook(@PathParam("id") final String id) {
        Book bookToDelete = bookService.getBookById(id);
        bookService.deleteBook(bookToDelete);
        String result = "Successfully deleted! " + bookToDelete.toString();
        return Response.status(Response.Status.OK).entity(result).build();
    }

    @PUT
    @Path("/update/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateBook(final String id, final BookDTO book) {
        Book bookToUpdate = bookService.getBookById(id);
        bookToUpdate.setAuthor(book.getAuthor());
        bookToUpdate.setName(book.getName());
        bookService.updateBook(bookToUpdate);
    }

    @PUT
    @Path("/assign/{userId}/{bookId}")
    public void assignBook(@PathParam("userId") final String userId, @PathParam("bookId") final String bookId) {
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
