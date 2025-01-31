package com.kaansenada.publisherprototype.repository;

import com.kaansenada.publisherprototype.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    Optional<Author> findAuthorByAuthorNameSurname(String authorNameSurname);

}
