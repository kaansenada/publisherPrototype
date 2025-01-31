package com.kaansenada.publisherprototype.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Schema(name = "Response", description = "Schema to hold the response")
@Data
@AllArgsConstructor
public class ResponseDto {
    @Schema(description = "Response message")
    private String message;
    @Schema(description = "Response status")
    private String statusMsg;
}
