/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ExceptionMappers;

import Exceptions.ConflictException;
import Responses.ErrorResponse;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

/**
 *
 * @author admin
 */

@Provider
public class ConflictExceptionMapper implements ExceptionMapper<ConflictException>{

    @Override
    public Response toResponse(ConflictException e) {
        var error = ErrorResponse.of("CONFLICT", e.getMessage(), 409);
        
        return Response.status(Response.Status.CONFLICT)
                .entity(error)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
    
}
