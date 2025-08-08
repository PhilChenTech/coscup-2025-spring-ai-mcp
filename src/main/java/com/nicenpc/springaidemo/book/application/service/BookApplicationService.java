package com.nicenpc.springaidemo.book.application.service;

import com.nicenpc.springaidemo.book.application.repository.BookEntity;

import java.util.List;
import java.util.Optional;

public interface BookApplicationService {
    BookEntity create(BookEntity book);

    List<BookEntity> findAll();

    Optional<BookEntity> findByTitle(String title);

    void deleteByTitle(String title);

    void deleteAll();
}
