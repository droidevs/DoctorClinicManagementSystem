/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ExceptionMappers;

import Exceptions.NotFoundException;
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
public class NotFoundExceptionMapper implements ExceptionMapper<NotFoundException>{

    
    @Override
    public Response toResponse(NotFoundException e) {
        return Response.status(Response.Status.NOT_FOUND)
                .entity(ErrorResponse.of(
                        "NOT_FOUND",
                        e.getMessage(),
                        404)
                ).type(MediaType.APPLICATION_JSON)
                .build();
    }
    
}
