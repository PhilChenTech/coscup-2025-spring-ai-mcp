package com.nicenpc.springaidemo.book.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookEntityRepository extends JpaRepository<BookEntity, Long> {
    Optional<BookEntity> findByTitle(String title);
}
