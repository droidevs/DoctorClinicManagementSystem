/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ExceptionMappers;


import Exceptions.BusinessException;
import Responses.ErrorResponse;
import jakarta.ws.rs.core.*;
import jakarta.ws.rs.ext.*;

@Provider
public class BusinessExceptionMapper
        implements ExceptionMapper<BusinessException> {

    @Override
    public Response toResponse(BusinessException ex) {

        var error = ErrorResponse.of(
                        "BUSINESS_ERROR",
                        ex.getMessage(),
                        400
                );
        
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(error)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
