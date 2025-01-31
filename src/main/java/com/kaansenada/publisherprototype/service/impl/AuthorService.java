package com.kaansenada.publisherprototype.service.impl;

import com.kaansenada.publisherprototype.dto.AuthorDto;
import com.kaansenada.publisherprototype.dto.BookDto;
import com.kaansenada.publisherprototype.entity.Author;
import com.kaansenada.publisherprototype.entity.Book;
import com.kaansenada.publisherprototype.exception.AlreadyExistsException;
import com.kaansenada.publisherprototype.exception.ResourceNotFoundException;
import com.kaansenada.publisherprototype.repository.AuthorRepository;
import com.kaansenada.publisherprototype.service.IAuthorService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthorService implements IAuthorService {
    private final AuthorRepository authorRepository;
    private final BookService bookService;
    @Override
    @Transactional
    public boolean saveAuthor(AuthorDto dto) {
        Optional<Author> authorOptional = authorRepository.findAuthorByAuthorNameSurname(dto.getAuthorNameSurname());
        if(authorOptional.isPresent()){
            throw new AlreadyExistsException("Author with name and surname " + dto.getAuthorNameSurname() + " already exists");
        }
        Book book = bookService.findBookById(dto.getBookID());

        Author author = new Author();
        author.setAuthorNameSurname(dto.getAuthorNameSurname());
        author.setBook(book);

        authorRepository.save(author);
        return true;
    }

    @Override
    @Transactional
    public boolean updateAuthor(AuthorDto dto) {
        Optional<Author> authorOptional = authorRepository.findAuthorByAuthorNameSurname(dto.getAuthorNameSurname());
        if(!authorOptional.isPresent()){
            throw new ResourceNotFoundException("Author", "name", dto.getAuthorNameSurname());
        }
        Author author = authorOptional.get();
        author.setAuthorNameSurname(dto.getAuthorNameSurname());

        if(dto.getBookID() != author.getBook().getBookId()){
            Book book = bookService.findBookById(dto.getBookID());
            author.setBook(book);
        }


        authorRepository.save(author);
        return true;
    }

    @Override
    public boolean updateAuthor(Author author) {
        Optional<Author> authorOptional = authorRepository.findAuthorByAuthorNameSurname(author.getAuthorNameSurname());
        if(!authorOptional.isPresent()){
            throw new ResourceNotFoundException("Author", "name", author.getAuthorNameSurname());
        }
        authorRepository.save(author);
        return true;
    }


    @Override
    @Transactional
    public boolean deleteAuthorById(Long id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author", "id", id));
        authorRepository.delete(author);
        return true;
    }

    @Override
    public Author findAuthorById(Long id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author", "id", id));
        return author;
    }
    @Override
    public Author findAuthorByNameAndSurname(String nameSurname) {
        Optional<Author> author = authorRepository.findAuthorByAuthorNameSurname(nameSurname);
        return author.orElse(null);
    }

    @Override
    public List<Author> findAllAuthors() {
        return authorRepository.findAll();
    }
}
