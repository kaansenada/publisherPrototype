package com.kaansenada.publisherprototype.service;

import com.kaansenada.publisherprototype.dto.AuthorDto;
import com.kaansenada.publisherprototype.entity.Author;

import java.util.List;

public interface IAuthorService {
    boolean saveAuthor(AuthorDto author);

    boolean updateAuthor(AuthorDto author);
    boolean updateAuthor(Author author);

    boolean deleteAuthorById(Long id);

    Author findAuthorById(Long id);

    List<Author> findAllAuthors();
    Author findAuthorByNameAndSurname(String nameSurname);
}
