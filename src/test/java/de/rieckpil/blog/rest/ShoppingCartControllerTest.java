package de.rieckpil.blog.rest;

import de.rieckpil.blog.service.BookService;
import de.rieckpil.blog.model.Book;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Slf4j
@WebMvcTest(ShoppingCartController.class)
class ShoppingCartControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Order(1)
    @DisplayName("多个字符串型入参")
    @ParameterizedTest
    @ValueSource(strings = { "a", "b", "c" })
    void stringsTest(String candidate) {
        log.info("stringsTest [{}]", candidate);
        assertTrue(null!=candidate);
    }

    @Order(2)
    @DisplayName("多个字符串型入参")
    @ParameterizedTest
    @NullSource
    @ValueSource(strings = { "a", "b", "c"})
    void stringsTest2(String candidate) {
        log.info("stringsTest [{}]", candidate);
        assertTrue(null!=candidate);
    }

    @ParameterizedTest
    @ValueSource(strings = {"/de/rieckpil/blog/rest/books.json"})
    public void testJson(String value) {
        System.out.println(value);
    }

    @Test
    public void shouldReturnAllBooks() throws Exception {
        Book book1 = new Book();
        book1.setName("Java编程1");
        book1.setPrice(new BigDecimal("10"));
        book1.setAuthor("jiuwu");

        Book book2 = new Book();
        book2.setName("Java编程2");
        book2.setPrice(new BigDecimal("102"));
        book2.setAuthor("jiuwu2");


        Mockito.when(bookService.findAll()).thenReturn(List.of(book1, book2));

        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/getBooks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", Matchers.is("Java编程1")))
                .andExpect(jsonPath("$[0].price", Matchers.is(10)));
    }

    @Nested
    //@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
    class A_year_is_not_supported {

        @Test
        void if_it_is_zero() {
        }

        @Test
        void should_return_true_when_create_given_price() {
        }

        @DisplayName("A negative value for year is not supported by the leap year computation.")
        @ParameterizedTest(name = "For example, year {0} is not supported.")
        @ValueSource(ints = { -1, -4 })
        void if_it_is_negative(int year) {
        }

    }

    @Nested
    //@IndicativeSentencesGeneration(separator = " -> ", generator = DisplayNameGenerator.ReplaceUnderscores.class)
    @IndicativeSentencesGeneration(separator = " -> ")
    class A_year_is_a_leap_year {

        @Test
        void if_it_is_divisible_by_4_but_not_by_100() {
        }

        @ParameterizedTest(name = "Year {0} is a leap year.")
        @ValueSource(ints = { 2016, 2020, 2048 })
        void if_it_is_one_of_the_following_years(int year) {
        }

    }

    @RepeatedTest(5)
    void repeatedTest() {
        System.out.println("====");
    }
}