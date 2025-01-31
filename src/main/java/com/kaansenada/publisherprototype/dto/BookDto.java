package com.kaansenada.publisherprototype.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kaansenada.publisherprototype.entity.Book;
import com.kaansenada.publisherprototype.feign.entity.SaleInfo;
import com.kaansenada.publisherprototype.feign.entity.VolumeInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Schema(name = "Book", description = "Schema for Book information")
@Getter
@Setter
@Data
@NoArgsConstructor
public class BookDto {
    @NotEmpty(message = "Title is required")
    @Size(min = 2, max = 100, message = "Title must be between 2 and 100 characters")
    @Schema(description = "Title of the book", example = "The Lord of the Rings")
    private String title;


    @Positive(message = "Price must be positive and not empty")
    @Schema(description = "Price of the book", example = "19.99")
    private BigDecimal price;

    @NotNull(message = "ISBN13 is required")
    @Size(min = 13, max = 13, message = "ISBN13 must be 13 characters")
    @Schema(description = "ISBN13 of the book", example = "9780132350884")
    @JsonProperty("ISBN13")
    private String isbn13;

    @NotEmpty(message = "Author name and surname required.")
    @Schema(description = "Author name and surname", example = "John Doe")
    private String authorNameSurname;

    @NotEmpty(message = "Publisher name is required.")
    @Schema(description = "Publisher name", example = "Publisher A")
    private String publisherName;

    public BookDto(Book book) {
        this.title = book.getTitle();
        this.price = book.getPrice();
        this.isbn13 = book.getIsbn13();
        this.publisherName = book.getPublisher().getPublisherName();
    }

    public BookDto(VolumeInfo volumeInfo, SaleInfo saleInfo) {
        this.title = volumeInfo.getTitle();
        this.isbn13 = volumeInfo.getIsbn13();
        this.publisherName = volumeInfo.getPublisher();
        volumeInfo.getAuthors().stream().filter(author -> author != null).forEach(author -> this.authorNameSurname = author);
        this.price = BigDecimal.valueOf(saleInfo.getListPrice().getAmount());
    }
}
