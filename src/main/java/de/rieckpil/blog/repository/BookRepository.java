package de.rieckpil.blog.repository;

import de.rieckpil.blog.model.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<Book, Serializable> {
    List<Book> findAll();
}
