package com.kaansenada.publisherprototype.repository;

import com.kaansenada.publisherprototype.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book,Long> {
    Optional<Book> findBookByIsbn13(String isbn);
    /**
     * publishing date tutulsaydı
     * List<Book> findBookAfterDate(Date date);
     * şeklinde bir method yazılabilirdi
     */

}
