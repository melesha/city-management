package com.helmes.citymanagement.rest;

import com.helmes.citymanagement.model.City;
import com.helmes.citymanagement.vo.ErrorEntry;
import com.helmes.citymanagement.vo.Filter;
import com.helmes.citymanagement.vo.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Cities")
@RequestMapping("/city-management")
public interface CityRestService {


    @Operation(summary = "Get a city by id")
    @Parameter(in = ParameterIn.PATH, name = "id", schema = @Schema(type = "long"))
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Successful response."),
                           @ApiResponse(responseCode = "400", description = "Bad request.", content = {@Content(array = @ArraySchema(schema = @Schema(implementation = ErrorEntry.class)))}),
                           @ApiResponse(responseCode = "404", description = "Not found resource.", content = {@Content(array = @ArraySchema(schema = @Schema(implementation = ErrorEntry.class)))}),
                           @ApiResponse(responseCode = "501", description = "Internal server error", content = {@Content(array = @ArraySchema(schema = @Schema(implementation = ErrorEntry.class)))})})
    @GetMapping("/cities/{id}")
    ResponseEntity<City> findCity(@PathVariable final Long id);

    @Operation(summary = "Get a paginated list of cities filtered by city name and sorted by city name")
    @Parameter(in = ParameterIn.QUERY, name = "cityName", schema = @Schema(type = "string"))
    @Parameter(in = ParameterIn.QUERY, name = "pageNumber", schema = @Schema(type = "integer"))
    @Parameter(in = ParameterIn.QUERY, name = "pageSize", schema = @Schema(type = "integer"))
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Successful response."),
                           @ApiResponse(responseCode = "400", description = "Bad request.", content = {@Content(array = @ArraySchema(schema = @Schema(implementation = ErrorEntry.class)))}),
                           @ApiResponse(responseCode = "501", description = "Internal server error", content = {@Content(array = @ArraySchema(schema = @Schema(implementation = ErrorEntry.class)))})})
    @GetMapping("/cities")
    ResponseEntity<Page<City>> findAllByName(@Parameter(hidden = true) final Filter filter, @Parameter(hidden = true) final PageInfo pageInfo);

    @Operation(summary = "Update an existing city")
    @Parameter(in = ParameterIn.QUERY, name = "id", schema = @Schema(type = "long"))
    @Parameter(in = ParameterIn.QUERY, name = "name", schema = @Schema(type = "string"))
    @Parameter(in = ParameterIn.QUERY, name = "photo", schema = @Schema(type = "string"))
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Successful response."),
                          @ApiResponse(responseCode = "400", description = "Bad request.", content = {@Content(array = @ArraySchema(schema = @Schema(implementation = ErrorEntry.class)))}),
                          @ApiResponse(responseCode = "404", description = "Not found resource.", content = {@Content(array = @ArraySchema(schema = @Schema(implementation = ErrorEntry.class)))}),
                          @ApiResponse(responseCode = "501", description = "Internal server error", content = {@Content(array = @ArraySchema(schema = @Schema(implementation = ErrorEntry.class)))})})
    @PutMapping("/cities")
    ResponseEntity<City> updateCity(@RequestBody final City city);
}
