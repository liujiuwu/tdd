package de.rieckpil.blog.repository;

import de.rieckpil.blog.model.Book;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
@DataJpaTest
//使用NONE需要把H2库的依赖删除
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BookRepositoryTest {
    @Autowired
    private DataSource dataSource;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private BookRepository bookRepository;

    @Container
    private static final PostgreSQLContainer POSTGRE_SQL_CONTAINER = new PostgreSQLContainer<>("postgres:12")
            .withDatabaseName("springboot").withUsername("root").withPassword("123456");

    @DynamicPropertySource
    public static void setDatabaseProperties(DynamicPropertyRegistry propertyRegistry) {
        //propertyRegistry.add("spring.datasource.username", MY_SQL_CONTAINER::getUsername);
        //propertyRegistry.add("spring.datasource.password", MY_SQL_CONTAINER::getPassword);
        propertyRegistry.add("spring.datasource.url", POSTGRE_SQL_CONTAINER::getJdbcUrl);
    }

    @Test
    void test() {
        assertTrue(POSTGRE_SQL_CONTAINER.isRunning());
    }

    @BeforeAll
    public static void setup() {
        //System.setProperty("driver-class-name","com.mysql.jdbc.Driver");
        //System.setProperty("url",MY_SQL_CONTAINER.getJdbcUrl());
        //System.setProperty("username",MY_SQL_CONTAINER.getUsername());
        //System.setProperty("password",MY_SQL_CONTAINER.getPassword());
    }

    @Test
    public void contextLoad() {
        assertNotNull(dataSource);
        assertNotNull(entityManager);
        assertNotNull(bookRepository);
    }

    @Test
    public void testCustomNativeQuery() {
        Book book1 = new Book();
        book1.setName("Rust");
        bookRepository.save(book1);

        Book book2 = new Book();
        book2.setName("Java");
        bookRepository.save(book2);

        assertEquals(2, bookRepository.findAll().size());
        assertTrue(bookRepository.findAll().contains(book1));
    }
}