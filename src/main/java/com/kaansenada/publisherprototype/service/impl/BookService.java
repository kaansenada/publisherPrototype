package com.kaansenada.publisherprototype.service.impl;

import com.kaansenada.publisherprototype.dto.BookDto;
import com.kaansenada.publisherprototype.entity.Book;
import com.kaansenada.publisherprototype.entity.Publisher;
import com.kaansenada.publisherprototype.exception.AlreadyExistsException;
import com.kaansenada.publisherprototype.exception.ResourceNotFoundException;
import com.kaansenada.publisherprototype.repository.BookRepository;
import com.kaansenada.publisherprototype.service.IBookService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BookService implements IBookService {
    private BookRepository bookRepository;
    private PublisherService publisherService;

    @Override
    @Transactional
    public boolean saveBook(BookDto dto) {
        Optional<Book> bookOptional = bookRepository.findBookByIsbn13(dto.getIsbn13());
        if(bookOptional.isPresent()){
            throw new AlreadyExistsException("Book with id " + bookOptional.get().getIsbn13() + " already exists");
        }

        Book book = new Book();
        book.setTitle(dto.getTitle());
        book.setPrice(dto.getPrice());
        book.setIsbn13(dto.getIsbn13());


        Publisher publisherOptional = publisherService.findByPublisherName(dto.getPublisherName());
        if(publisherOptional == null){
            throw new ResourceNotFoundException("Publisher", "name", dto.getPublisherName());
        }
        book.setPublisher(publisherOptional);

        bookRepository.save(book);
        return true;
    }

    @Override
    @Transactional
    public boolean deleteBookById(Long id) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        if(!bookOptional.isPresent()){
            throw new ResourceNotFoundException("Book", "id", id);
        }
        bookRepository.deleteById(id);
        return true;
    }

    @Override
    @Transactional
    public boolean deleteBookByISBN13(String isbn) {
        Optional<Book> bookOptional = bookRepository.findBookByIsbn13(isbn);
        if(!bookOptional.isPresent()){
            throw new ResourceNotFoundException("Book", "ISBN13", isbn);
        }
        bookRepository.deleteById(bookOptional.get().getBookId());
        return true;
    }

    @Override
    public Book findBookById(Long id) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        if(!bookOptional.isPresent()){
            throw new ResourceNotFoundException("Book", "id", id);
        }
        return bookOptional.get();
    }

    @Override
    @Transactional
    public boolean updateBook(BookDto dto) {
        Optional<Book> bookOptional = bookRepository.findBookByIsbn13(dto.getIsbn13());
        if(!bookOptional.isPresent()){
            throw new ResourceNotFoundException("Book", "ISBN13", dto.getIsbn13());
        }
        Book book = bookOptional.get();

        book.setTitle(dto.getTitle());
        book.setPrice(dto.getPrice());
        book.setIsbn13(dto.getIsbn13());


        Publisher publisherOptional = publisherService.findByPublisherName(dto.getPublisherName());
        if(publisherOptional == null){
            throw new ResourceNotFoundException("Publisher", "name", dto.getPublisherName());
        }
        book.setPublisher(publisherOptional);


        bookRepository.save(book);
        return true;

    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book findBookByIsbn13(String isbn) {
       Optional<Book> bookOptional = bookRepository.findBookByIsbn13(isbn);
       if(!bookOptional.isPresent()){
           throw new ResourceNotFoundException("Book", "ISBN13", isbn);
       }
       return bookOptional.get();
    }
}
