package com.kaansenada.publisherprototype.service;

import com.kaansenada.publisherprototype.dto.PublisherDto;
import com.kaansenada.publisherprototype.entity.Publisher;

import java.util.List;
import java.util.Optional;

public interface IPublisherService {
    boolean savePublisher(Publisher publisher);
    boolean savePublisher(PublisherDto publisherDto);
    List<Publisher> findAll();

    Publisher findById(Long id);

    Publisher findByPublisherName(String name);

    boolean deleteById(Long id);

    boolean updatePublisher(Publisher publisher);

}
