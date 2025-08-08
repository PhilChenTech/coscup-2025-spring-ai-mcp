package com.nicenpc.springaidemo.book.application.service;

import com.nicenpc.springaidemo.book.application.repository.BookEntity;
import com.nicenpc.springaidemo.book.application.repository.BookEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookApplicationServiceImpl implements BookApplicationService {

    private final BookEntityRepository bookEntityRepository;

    @Override
    public BookEntity create(BookEntity book) {
        return bookEntityRepository.save(book);
    }

    @Override
    public List<BookEntity> findAll() {
        return bookEntityRepository.findAll();
    }

    @Override
    public Optional<BookEntity> findByTitle(String title) {
        return bookEntityRepository.findByTitle(title);
    }

    @Override
    public void deleteByTitle(String title) {
        bookEntityRepository.findByTitle(title).ifPresent(bookEntityRepository::delete);
    }

    @Override
    public void deleteAll() {
        bookEntityRepository.deleteAll();
    }
}
