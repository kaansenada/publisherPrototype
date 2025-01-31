package com.kaansenada.publisherprototype.controller;

import com.kaansenada.publisherprototype.dto.ErrorResponseDto;
import com.kaansenada.publisherprototype.dto.PublisherDto;
import com.kaansenada.publisherprototype.dto.ResponseDto;
import com.kaansenada.publisherprototype.entity.Publisher;
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

@Tag(name = "Publisher api", description = "Publisher: REST API")
@RestController
@RequestMapping(path = "/api/publisher", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Validated
public class PublisherController {
    private final IPublisherService publisherService;
    private final IBookService bookService;

    @Operation(
            summary = "Create Publisher API",
            description = "REST API to create a Publisher"
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
    public ResponseEntity<ResponseDto> createPublisher(@RequestBody @Valid PublisherDto publisherDto) {
        boolean isPublisherCreated = publisherService.savePublisher(publisherDto);
        if (isPublisherCreated) {
            return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto("Publisher created successfully", "success"));
        } else {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseDto("Publisher creation failed", "error"));
        }
    }

    @Operation(
            summary = "Update Publisher API",
            description = "REST API to update a Publisher"
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
    public ResponseEntity<ResponseDto> updatePublisher(@RequestBody @Valid Publisher publisher) {
        boolean isPublisherUpdated = publisherService.updatePublisher(publisher);
        if (isPublisherUpdated) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto("Publisher updated successfully", "success"));
        } else {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseDto("Publisher update failed", "error"));
        }
    }

    @Operation(
            summary = "Delete Publisher REST API",
            description = "REST API to delete Publisher basen on id"
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
    public ResponseEntity<ResponseDto> deletePublisher(@PathVariable("id") Long id) {
        boolean isPublisherDeleted = publisherService.deleteById(id);
        if (isPublisherDeleted) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto("Publisher deleted successfully", "success"));
        } else {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseDto("Publisher deletion failed", "error"));
        }
    }

    @Operation(
            summary = "Get Publisher REST API",
            description = "REST API to get Publisher basen on id"
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
    public ResponseEntity<Publisher> getPublisher(@PathVariable("id") Long id) {
        Publisher publisher = publisherService.findById(id);
        if (publisher != null) {
            return ResponseEntity.status(HttpStatus.OK).body(publisher);
        } else {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(null);
        }
    }

    @Operation(
            summary = "Get All Publishers REST API",
            description = "REST API to get all Publishers"
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
    public ResponseEntity<List<PublisherDto>> getAllPublishers() {
        List<Publisher> publishers = publisherService.findAll();
        List<PublisherDto> publisherDtos = publishers.stream().map(PublisherDto::new).toList();
        if (publishers != null) {
            return ResponseEntity.status(HttpStatus.OK).body(publisherDtos);
        } else {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(null);
        }
    }

//    @GetMapping("/allWithBooksAndAuthors")
//    public ResponseEntity<List<CustomPublisherDto>> getAllPublishersWithBooksAndAuthors() {
//        List<CustomPublisherDto> customPublisherDtos = publisherService.findAllWithBooksAndAuthors();
//
//    }
}
