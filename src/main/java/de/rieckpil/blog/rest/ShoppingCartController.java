package de.rieckpil.blog.rest;

import de.rieckpil.blog.service.BookService;
import de.rieckpil.blog.model.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ShoppingCartController {

    private final BookService bookService;

    @RequestMapping("/getBooks")
    public List<Book> getBooks() {
        return bookService.findAll();
    }
}
