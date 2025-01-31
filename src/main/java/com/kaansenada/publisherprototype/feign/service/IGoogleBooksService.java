package com.kaansenada.publisherprototype.feign.service;

import com.kaansenada.publisherprototype.feign.entity.GoogleBooksResponse;

import java.util.List;

public interface IGoogleBooksService {
    GoogleBooksResponse searchBooks(String query);
}
