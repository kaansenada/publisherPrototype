package com.kaansenada.publisherprototype.controller;

import com.kaansenada.publisherprototype.dto.AuthorDto;
import com.kaansenada.publisherprototype.dto.ErrorResponseDto;
import com.kaansenada.publisherprototype.dto.ResponseDto;
import com.kaansenada.publisherprototype.entity.Author;
import com.kaansenada.publisherprototype.service.IAuthorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Stream;

@Tag(name = "Author api", description = "Author: Firstname Lastname and Book")
@RestController
@RequestMapping(path = "/api/author", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Validated
public class AuthorController {
    private IAuthorService authorService;


    @Operation(
            summary = "Create Author API",
            description = "REST API to create an Author"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status CREATED"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAuthor(@Valid @RequestBody AuthorDto authorDto) {
        boolean result = authorService.saveAuthor(authorDto);
        if (result) {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(new ResponseDto("Author: Firstname Lastname successfully", "success"));
        } else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto("Author: Firstname Lastname failed", "error"));
        }
    }


    @Operation(
            summary = "Update Author API",
            description = "REST API to update an author"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Expectation Failed"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateAuthor(@Valid @RequestBody AuthorDto authorDto) {
        boolean result = authorService.updateAuthor(authorDto);
        if (result) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto("Author: Firstname Lastname successfully", "success"));
        } else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto("Author: Firstname Lastname failed", "error"));
        }
    }
    @Operation(
            summary = "Get Author by ID REST API",
            description = "REST API to fetch Author by id"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @GetMapping("/get/{id}")
    public ResponseEntity<Author> getAuthor(@PathVariable("id") Long id) {
        Author author = authorService.findAuthorById(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(author);
    }

    @Operation(
            summary = "Get All Authors REST API",
            description = "REST API to fetch all authors"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @GetMapping("/all")
    public ResponseEntity<List<AuthorDto>> getAllAuthors() {
        List<Author> authors = authorService.findAllAuthors();
        List<AuthorDto> authorDtos = authors.stream().map(AuthorDto::new).toList();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(authorDtos);
    }
    @Operation(
            summary = "Delete Author REST API",
            description = "REST API to delete Author basen on id"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Expectation Failed"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDto> deleteAuthor(@PathVariable("id") Long id) {
        boolean result = authorService.deleteAuthorById(id);
        if (result) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto("Author: Firstname Lastname successfully", "success"));
        } else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto("Author: Firstname Lastname failed", "error on deletion"));
        }
    }
}
