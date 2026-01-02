/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Loggings;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import java.io.IOException;

/**
 *
 * @author admin
 */
public class CorrelationIdFilter implements ContainerRequestFilter, ContainerResponseFilter{

    public static final String HEADER_NAME = "X-Correlation-Id";
    
    @Override
    public void filter(ContainerRequestContext crc) throws IOException {
        String correlationId = crc.getHeaderString(HEADER_NAME);
        
        if (correlationId == null || correlationId.isBlank()) {
            correlationId = CorrelationIdGenerator.generate();
        }
        
        // store it in MDC and thread-local
        CorrelationIdHolder.set(correlationId);
    }

    @Override
    public void filter(ContainerRequestContext request, ContainerResponseContext response) throws IOException {
        
        String correlationId = CorrelationIdHolder.get();
        
        if(correlationId != null) {
            response.getHeaders().putSingle(HEADER_NAME, correlationId);
        }
        
        CorrelationIdHolder.clear();
    }
    
}
