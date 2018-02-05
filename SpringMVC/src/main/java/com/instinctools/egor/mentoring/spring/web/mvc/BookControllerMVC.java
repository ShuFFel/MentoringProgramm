package com.instinctools.egor.mentoring.spring.web.mvc;

import com.instinctools.egor.mentoring.spring.core.entity.Book;
import com.instinctools.egor.mentoring.spring.core.entity.User;
import com.instinctools.egor.mentoring.spring.services.BookService;
import com.instinctools.egor.mentoring.spring.services.UserService;
import com.instinctools.egor.mentoring.spring.web.dto.BookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookControllerMVC {
    private final UserService userService;
    private final BookService bookService;

    @Autowired
    public BookControllerMVC(final UserService userService, final BookService bookService) {
        this.userService = userService;
        this.bookService = bookService;
    }

    @GetMapping
    public ModelAndView getAll() {
        final ModelAndView modelAndView = new ModelAndView("bookList");
        final List<Book> books = bookService.getAllBooks();
        modelAndView.addObject("bookList", books);
        return modelAndView;
    }

    @PostMapping("/assign")
    public ModelAndView assignBook(@RequestParam("userId") final String userId,
                                   @RequestParam("bookId") final String bookId) {
        User user = userService.getUserById(userId);
        Book book = bookService.getBookById(bookId);
        bookService.assignBook(user, book);
        return new ModelAndView();
    }

    @PostMapping("/return")
    public ModelAndView returnBook(@RequestParam("bookId") final String bookId) {
        Book book = bookService.getBookById(bookId);
        if (book == null) {
            return new ModelAndView("error");
        }
        bookService.returnBook(book);
        return new ModelAndView();
    }

    @PostMapping("/update")
    public ModelAndView updateBook(@RequestParam("bookId") final String bookId,
                                   @ModelAttribute("book") final BookDTO bookDTO) {
        final Book book = bookService.getBookById(bookId);
        book.setName(bookDTO.getName());
        book.setAuthor(bookDTO.getAuthor());
        bookService.updateBook(book);
        return new ModelAndView("redirect:/books");
    }

    @GetMapping("/update/form")
    public ModelAndView getUpdateForm(@RequestParam("bookId") final String bookId) {
        ModelAndView modelAndView = new ModelAndView("bookUpdate");
        modelAndView.addObject("bookId", bookId);
        modelAndView.addObject("bookDTO", bookService.getBookById(bookId));
        return modelAndView;
    }

    @PostMapping("/delete")
    public ModelAndView deleteBook(@RequestParam("id") final String id) {
        final Book book = bookService.getBookById(id);
        bookService.deleteBook(book);
        return new ModelAndView("redirect:/books");
    }

    @PostMapping("/create")
    public ModelAndView createBook(@ModelAttribute("bookDTO") final BookDTO bookDTO) {
        Book book = bookDTO.toModel();
        bookService.createBook(book);
        return new ModelAndView("redirect:/books");
    }

    @GetMapping("/create/form")
    public ModelAndView getCreateBookForm() {
        ModelAndView modelAndView = new ModelAndView("bookCreate");
        modelAndView.addObject("bookDTO", new BookDTO("fromJava1", "fromJava2"));
        return modelAndView;
    }


}
