package com.kaansenada.publisherprototype.dto;

import com.kaansenada.publisherprototype.entity.Publisher;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(name = "Publisher", description = "Schema for Publisher")
public class PublisherDto {
    @NotNull(message = "Publisher name is required.")
    @Size(min = 2, max = 100, message = "Publisher name must be between 2 and 100 characters")
    @Schema(description = "Publisher name", example = "Publisher A")
    private String publisherName;

    public PublisherDto(Publisher publisher) {
        this.publisherName = publisher.getPublisherName();
    }
}
