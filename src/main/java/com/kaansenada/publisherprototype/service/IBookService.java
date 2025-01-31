package com.kaansenada.publisherprototype.service;

import com.kaansenada.publisherprototype.dto.BookDto;
import com.kaansenada.publisherprototype.entity.Book;

import java.util.Date;
import java.util.List;

public interface IBookService {
    boolean saveBook(BookDto dto);
    boolean deleteBookById(Long id);
    boolean deleteBookByISBN13(String isbn);
    Book findBookById(Long id);
    boolean updateBook(BookDto dto);
    List<Book> findAll();
    Book findBookByIsbn13(String isbn);

    /**
     * Eğer kitap nesnesinde yayın tarihi tutuyor olsaydık
     * @param date
     * @return
     */
    //List<Book> findBookAfterDate(Date date);

}
