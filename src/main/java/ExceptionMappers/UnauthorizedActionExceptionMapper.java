/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ExceptionMappers;

import Exceptions.auth.UnauthorizedActionException;
import Responses.ErrorResponse;
import jakarta.ws.rs.core.*;
import jakarta.ws.rs.ext.*;

@Provider
public class UnauthorizedActionExceptionMapper
        implements ExceptionMapper<UnauthorizedActionException> {

    @Override
    public Response toResponse(UnauthorizedActionException ex) {

        var error = ErrorResponse.of(
                        "FORBIDDEN",
                        ex.getMessage(),
                        403
                );
        
        return Response.status(Response.Status.FORBIDDEN)
                .entity(error)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
