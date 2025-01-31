package com.kaansenada.publisherprototype.feign.service;

import com.kaansenada.publisherprototype.feign.repository.GoogleBooksRepository;
import com.kaansenada.publisherprototype.feign.entity.GoogleBooksResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GoogleBooksSerivce implements IGoogleBooksService {

    private final GoogleBooksRepository googleBooksRepository;


    @Override
    public GoogleBooksResponse searchBooks(String query) {

        GoogleBooksResponse googleBooksResponse = googleBooksRepository.searchBooks(query);
        if (googleBooksResponse != null) {
            return googleBooksResponse;
        }
        return null;
    }
}
