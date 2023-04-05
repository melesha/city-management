package com.helmes.citymanagement.rest;

import com.helmes.citymanagement.vo.ErrorEntry;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Authentication")
@RequestMapping("/city-management")
public interface AuthRestService {

    @Operation(summary = "Get JWT Token")
    @Parameter(in = ParameterIn.HEADER, name = "username", schema = @Schema(type = "String"))
    @Parameter(in = ParameterIn.HEADER, name = "password", schema = @Schema(type = "String"))
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Successful response."),
            @ApiResponse(responseCode = "400", description = "Bad request.", content = {@Content(array = @ArraySchema(schema = @Schema(implementation = ErrorEntry.class)))}),
            @ApiResponse(responseCode = "501", description = "Internal server error", content = {@Content(array = @ArraySchema(schema = @Schema(implementation = ErrorEntry.class)))})})
    @PostMapping("/token")
    String token(Authentication authentication);
}
