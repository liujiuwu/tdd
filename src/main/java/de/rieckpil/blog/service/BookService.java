package de.rieckpil.blog.service;

import de.rieckpil.blog.model.Book;
import de.rieckpil.blog.repository.BookRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public List<Book> findAll() {
        return bookRepository.findAll();
    }
}
