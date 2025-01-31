package com.kaansenada.publisherprototype.dto;

import com.kaansenada.publisherprototype.entity.Author;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(name = "Author", description = "Scehma for Author")
@Getter
@Setter
@Data
@NoArgsConstructor
public class AuthorDto {
    @NotEmpty(message = "Author name and surname required.")
    @Schema(description = "Author name and surname", example = "John Doe")
    private String authorNameSurname;

    @NotNull(message = "Book id is required.")
    @Schema(description = "Book id", example = "1")
    Long bookID;


    public AuthorDto(Author author) {
        this.authorNameSurname = author.getAuthorNameSurname();
    }
}
