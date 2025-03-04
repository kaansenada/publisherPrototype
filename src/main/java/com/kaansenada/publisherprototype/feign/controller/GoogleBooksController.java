package com.kaansenada.publisherprototype.feign.controller;

import com.kaansenada.publisherprototype.dto.BookDto;
import com.kaansenada.publisherprototype.dto.ErrorResponseDto;
import com.kaansenada.publisherprototype.feign.entity.GoogleBooksResponse;
import com.kaansenada.publisherprototype.feign.entity.SaleInfo;
import com.kaansenada.publisherprototype.feign.entity.VolumeInfo;
import com.kaansenada.publisherprototype.feign.service.IGoogleBooksService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Google books api", description = "google books api implementation")
@RestController
@RequestMapping(path="/api/google-books",produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
public class GoogleBooksController {
    private final IGoogleBooksService googleBooksService;

    @Operation(summary = "Search books by title", description = "Search books by title")
    @ApiResponses({
        @ApiResponse( responseCode = "200", description = "HTTP Status OK" ),
        @ApiResponse( responseCode = "500", description = "HTTP Status Internal Server Error", content = @Content( schema = @Schema(implementation = ErrorResponseDto.class) ) )
    })
    @GetMapping("/search")
    public BookDto searchBooks(@RequestParam String query) {
        GoogleBooksResponse googleBooksResponse = googleBooksService.searchBooks(query);
        VolumeInfo volumeInfo = googleBooksResponse.getItems().get(0).getVolumeInfo();
        SaleInfo saleInfo = googleBooksResponse.getItems().get(0).getSaleInfo();
        BookDto bookDto = new BookDto(volumeInfo, saleInfo);
        return bookDto;
    }
}
