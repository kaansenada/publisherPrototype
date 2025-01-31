package com.kaansenada.publisherprototype.service.impl;

import com.kaansenada.publisherprototype.dto.PublisherDto;
import com.kaansenada.publisherprototype.entity.Publisher;
import com.kaansenada.publisherprototype.exception.AlreadyExistsException;
import com.kaansenada.publisherprototype.exception.ResourceNotFoundException;
import com.kaansenada.publisherprototype.repository.PublisherRepository;
import com.kaansenada.publisherprototype.service.IPublisherService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PublisherService implements IPublisherService {

    private final PublisherRepository publisherRepository;
    @Override
    public boolean savePublisher(Publisher publisher) {
        Optional<Publisher> publisherOptional = publisherRepository.findByPublisherName(publisher.getPublisherName());
        if (publisherOptional.isPresent()) {
            throw new AlreadyExistsException("Publisher with name " + publisher.getPublisherName() + " already exists");
        }
        publisherRepository.save(publisher);
        return true;
    }

    @Override
    public boolean savePublisher(PublisherDto publisherDto) {
        Optional<Publisher> publisherOptional = publisherRepository.findByPublisherName(publisherDto.getPublisherName());
        if (publisherOptional.isPresent()) {
            throw new AlreadyExistsException("Publisher with name " + publisherDto.getPublisherName() + " already exists");
        }
        Publisher publisher = new Publisher();
        publisher.setPublisherName(publisherDto.getPublisherName());
        publisherRepository.save(publisher);
        return true;
    }

    @Override
    public List<Publisher> findAll() {
        return publisherRepository.findAll();
    }

    @Override
    public Publisher findById(Long id) {
        Optional<Publisher> publisherOptional = publisherRepository.findById(id);
        if (!publisherOptional.isPresent()) {
            throw new ResourceNotFoundException("Publisher", "id", id);
        }
        return publisherOptional.get();
    }

    @Override
    public Publisher findByPublisherName(String name) {
        Optional<Publisher> publisherOptional = publisherRepository.findByPublisherName(name);
        if (!publisherOptional.isPresent()) {
            throw new ResourceNotFoundException("Publisher", "name", name);
        }
        return publisherOptional.get();
    }

    @Override
    public boolean deleteById(Long id) {
        Optional<Publisher> publisherOptional = publisherRepository.findById(id);
        if (!publisherOptional.isPresent()) {
            throw new ResourceNotFoundException("Publisher", "id", id);
        }
        publisherRepository.deleteById(id);
        return true;
    }

    @Override
    public boolean updatePublisher(Publisher publisher) {
        Optional<Publisher> publisherOptional = publisherRepository.findById(publisher.getPublisherID());
        if (!publisherOptional.isPresent()) {
            throw new ResourceNotFoundException("Publisher", "id", publisher.getPublisherID());
        }
        publisherRepository.save(publisher);
        return true;

    }

}
