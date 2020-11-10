package io.service.user.configuration.openapi;

/**
 * Custom annotations wrapper for all endpoints
 */

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
@ApiResponses(value = {
        @ApiResponse(
                responseCode = "400",
                description = "Bad Request",
                content = {@Content(mediaType = "application/json", schema = @Schema(implementation = String.class))}
        ),
        @ApiResponse(
                responseCode = "401",
                description = "Unauthorized: the request requires user authentication",
                content = {@Content(mediaType = "application/json", schema = @Schema(implementation = String.class))}),
        @ApiResponse(
                responseCode = "403",
                description = "Forbidden",
                content = {@Content(mediaType = "application/json", schema = @Schema(implementation = String.class))})
})
public @interface IoApiOperation {

}
