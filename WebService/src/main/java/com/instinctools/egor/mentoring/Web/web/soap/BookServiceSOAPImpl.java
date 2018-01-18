package com.instinctools.egor.mentoring.Web.web.soap;

import com.instinctools.egor.mentoring.Web.core.entity.Book;
import com.instinctools.egor.mentoring.Web.core.entity.User;
import com.instinctools.egor.mentoring.Web.core.factory.EntityFactory;
import com.instinctools.egor.mentoring.Web.core.services.BookService;
import com.instinctools.egor.mentoring.Web.core.services.UserService;
import com.instinctools.egor.mentoring.Web.web.dto.BookDTO;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService
public class BookServiceSOAPImpl {

    private BookService bookService;
    private UserService userService;
    private EntityFactory factory;
    private static String string;

    public BookServiceSOAPImpl(){}

    public BookServiceSOAPImpl(BookService bookService, UserService userService, EntityFactory factory) {
        this.bookService = bookService;
        this.userService = userService;
        this.factory = factory;
    }

    @WebMethod
    public void createBook(BookDTO book) {
        Book bookToCreate = factory.createBook(book.getName(), book.getAuthor());
        bookService.createBook(bookToCreate);
        string = "AAa";
    }

    @WebMethod
    public void deleteBook(String id) {
        Book bookToDelete = bookService.getBookById(id);
        bookService.deleteBook(bookToDelete);
    }

    @WebMethod
    public void updateBook(String id, BookDTO book) {
        Book bookToUpdate = bookService.getBookById(id);
        bookToUpdate.setName(book.getName());
        bookToUpdate.setAuthor(book.getAuthor());
        bookService.updateBook(bookToUpdate);
    }

    @WebMethod
    public void assignBook(String userId, String bookId) {
        User userToAssign = userService.getUserById(userId);
        Book bookToAssign = bookService.getBookById(bookId);
        bookService.assignBook(userToAssign, bookToAssign);
    }

    @WebMethod
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }
}
