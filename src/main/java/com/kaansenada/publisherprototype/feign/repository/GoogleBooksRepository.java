package com.kaansenada.publisherprototype.feign.repository;

import com.kaansenada.publisherprototype.feign.entity.GoogleBooksResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
@Repository
@FeignClient(name = "googleBooksClient", url = "https://www.googleapis.com/books/v1/volumes")
public interface GoogleBooksRepository {

    @GetMapping
    GoogleBooksResponse searchBooks(@RequestParam("q") String query);
}
