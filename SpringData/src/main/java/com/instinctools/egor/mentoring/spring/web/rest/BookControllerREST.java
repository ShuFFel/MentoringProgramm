package com.instinctools.egor.mentoring.spring.web.rest;

import com.instinctools.egor.mentoring.spring.core.entity.Book;
import com.instinctools.egor.mentoring.spring.core.entity.User;
import com.instinctools.egor.mentoring.spring.services.BookService;
import com.instinctools.egor.mentoring.spring.services.UserService;
import com.instinctools.egor.mentoring.spring.web.dto.BookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@Controller
@RequestMapping("/book")
public class BookControllerREST {

    private final UserService userService;
    private final BookService bookService;

    @Autowired
    public BookControllerREST(final UserService userService, final BookService bookService) {
        this.userService = userService;
        this.bookService = bookService;
    }

    @GetMapping(value = "/getAll", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok().body(bookService.getAllBooks());
    }

    @PostMapping(value = "/create", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> createBook(@RequestParam final BookDTO bookDTO) {
        Book book = bookDTO.toModel();
        bookService.createBook(book);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping(value = "/update", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> updateBook(@RequestParam final String id, @RequestParam final BookDTO bookDTO) {
        Book book = bookService.getBookById(id);
        if (book == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        book.setAuthor(bookDTO.getAuthor());
        book.setName(bookDTO.getName());
        bookService.updateBook(book);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping(value = "/assign", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> assignBook(@RequestParam final String userId,
                                        @RequestParam final String bookId) {
        User user = userService.getUserById(userId);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        Book book = bookService.getBookById(bookId);
        if (book == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        bookService.assignBook(user, book);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}

