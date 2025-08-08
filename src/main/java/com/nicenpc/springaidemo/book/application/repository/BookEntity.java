package com.nicenpc.springaidemo.book.application.repository;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Table(name = "book")
@Entity
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    private String title;
    private String author;
}
