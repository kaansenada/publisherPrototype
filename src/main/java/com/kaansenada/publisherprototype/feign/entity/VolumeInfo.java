package com.kaansenada.publisherprototype.feign.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VolumeInfo {
    @JsonProperty("title")
    private String title;

    @JsonProperty("industryIdentifiers")
    private List<IndustryIdentifier> industryIdentifiers;

    @JsonProperty("publisher")
    private String publisher;

    @JsonProperty("authors")
    private List<String> authors;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public String getIsbn13() {
        if (industryIdentifiers != null) {
            for (IndustryIdentifier id : industryIdentifiers) {
                if ("ISBN_13".equals(id.getType())) {
                    return id.getIdentifier();
                }
            }
        }
        return null;
    }
}
