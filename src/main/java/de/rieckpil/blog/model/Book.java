package de.rieckpil.blog.model;

import lombok.Data;

import javax.annotation.processing.Generated;
import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Table(name = "books")
@Entity
public class Book {
    private String name;
    private BigDecimal price;
    private String author;

    @Id
    @GeneratedValue
    private Long id;
}
