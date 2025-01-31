package com.kaansenada.publisherprototype.controller;

import com.kaansenada.publisherprototype.dto.AuthorDto;
import com.kaansenada.publisherprototype.dto.BookDto;
import com.kaansenada.publisherprototype.dto.ErrorResponseDto;
import com.kaansenada.publisherprototype.dto.ResponseDto;
import com.kaansenada.publisherprototype.entity.Author;
import com.kaansenada.publisherprototype.entity.Book;
import com.kaansenada.publisherprototype.service.IAuthorService;
import com.kaansenada.publisherprototype.service.IBookService;
import com.kaansenada.publisherprototype.service.IPublisherService;
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

@Tag(name = "Book api", description = "Book: REST API")
@RestController
@RequestMapping(path = "/api/book", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Validated
public class BookController {
    private final IBookService bookService;
    private final IAuthorService authorService;
    private final IPublisherService publisherService;

    @Operation(
            summary = "Create Book API",
            description = "REST API to create a Book"
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
    public ResponseEntity<ResponseDto> createBook(@RequestBody @Valid BookDto bookDto) {
        boolean isBookCreated = bookService.saveBook(bookDto);
        if (isBookCreated) {
            Author author = authorService.findAuthorByNameAndSurname(bookDto.getAuthorNameSurname());
            if (author != null) {
                author.setBook(bookService.findBookByIsbn13(bookDto.getIsbn13()));
                authorService.updateAuthor(author);
            } else {
                AuthorDto newAuthor = new AuthorDto();
                newAuthor.setAuthorNameSurname(bookDto.getAuthorNameSurname());
                newAuthor.setBookID(bookService.findBookByIsbn13(bookDto.getIsbn13()).getBookId());
                authorService.saveAuthor(newAuthor);
            }
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(new ResponseDto("Book created successfully", "success"));
        } else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto("Book creation failed", "error"));
        }
    }

    @Operation(
            summary = "Update Book API",
            description = "REST API to update a Book"
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
    public ResponseEntity<ResponseDto> updateBook(@Valid @RequestBody BookDto bookDto) {
        boolean result = bookService.updateBook(bookDto);
        if (result) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto("Book updated successfully", "success"));
        } else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto("Book update failed", "error"));
        }
    }

    @Operation(
            summary = "Get All Books REST API",
            description = "REST API to fetch all books"
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
    public ResponseEntity<List<BookDto>> getAllBooks() {
        List<Book> books = bookService.findAll();
        List<BookDto> bookDtos = books.stream().map(BookDto::new).toList();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(bookDtos);
    }

    @Operation(
            summary = "Get Book REST API",
            description = "REST API to fetch a book by id"
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
    public ResponseEntity<BookDto> getBook(@PathVariable("id") Long id) {
        Book book = bookService.findBookById(id);
        BookDto bookDto = new BookDto(book);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(bookDto);
    }

    @Operation(
            summary = "Get Book REST API",
            description = "REST API to fetch a book by title"
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
    @GetMapping("/get/{title}")
    public ResponseEntity<List<BookDto>> getBookByTitle(@PathVariable("title") String title) {
        List<Book> books = bookService.findAll();
        List<BookDto> bookDtos = books.stream().map(BookDto::new).filter(bookDto -> bookDto.getTitle().startsWith(title)).toList();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(bookDtos);
    }
    @Operation(
            summary = "Delete Book REST API",
            description = "REST API to delete Book basen on id"
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
    public ResponseEntity<ResponseDto> deleteBook(@PathVariable("id") Long id) {
        boolean result = bookService.deleteBookById(id);
        if (result) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto("Book deleted successfully", "success"));
        } else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto("Book deletion failed", "error"));
        }
    }


}
