package com.instinctools.egor.mentoring.web.web.soap;

import com.instinctools.egor.mentoring.web.core.entity.Book;
import com.instinctools.egor.mentoring.web.core.entity.User;
import com.instinctools.egor.mentoring.web.core.services.BookService;
import com.instinctools.egor.mentoring.web.core.services.UserService;
import com.instinctools.egor.mentoring.web.web.dto.BookDTO;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService
public class BookServiceSOAPImpl {

    private BookService bookService;
    private UserService userService;

    public BookServiceSOAPImpl(){}

    public BookServiceSOAPImpl(BookService bookService, UserService userService) {
        this.bookService = bookService;
        this.userService = userService;
    }

    @WebMethod
    public void createBook(BookDTO book) {
        Book bookToCreate = new Book(book.getName(), book.getAuthor());
        bookService.createBook(bookToCreate);
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
