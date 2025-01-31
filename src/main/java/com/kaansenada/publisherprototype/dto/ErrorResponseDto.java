package com.kaansenada.publisherprototype.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@Schema(name = "ErrorResponse", description = "Scehma for ErrorResponse")
@AllArgsConstructor
public class ErrorResponseDto {
    @Schema(description = "API path")
    private String apiPath;

    @Schema(description = "Error code")
    private HttpStatus errorCode;

    @Schema(description = "Error message")
    private String errorMessage;

    @Schema(description = "Error time")
    private LocalDateTime errorTime;
}
