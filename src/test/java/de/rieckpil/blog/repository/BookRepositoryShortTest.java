package de.rieckpil.blog.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest(properties = {
        "spring.test.database.replace=NONE",
        //"spring.datasource.url=jdbc:tc:mysql:5.7:///test"
        "spring.datasource.url=jdbc:tc:mysql:8:///test",
})
public class BookRepositoryShortTest {
    @Autowired
    private BookRepository bookRepository;

    @Test
    @Sql("/scripts/INIT_THREE_BOOK.sql")
    public void testCustomNativeQuery() {
        assertEquals(3, bookRepository.findAll().size());
    }
}