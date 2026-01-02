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
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author admin
 */
public class RequestLoggingFilter implements ContainerRequestFilter, ContainerResponseFilter{

    
    private static final Logger log = LoggerFactory.getLogger(RequestLoggingFilter.class);
    
    
    private static final String START_TIME = "requestStartTime";
    
    @Override
    public void filter(ContainerRequestContext request) throws IOException {
        
        long startTime = System.currentTimeMillis();
        request.setProperty(START_TIME, startTime);
        
        String method = request.getMethod();
        String path = request.getUriInfo().getRequestUri().toString();
        String clientIp = resolveClientIp(request);
        String userAgent = request.getHeaderString("User-Agent");
        
        log.info(
                "Incoming request [{}] {} | id={} | ua={}",
                method,
                path,
                clientIp,
                userAgent
        );
    }

    @Override
    public void filter(ContainerRequestContext request, ContainerResponseContext response) throws IOException {
        Long startTime = (Long) request.getProperty(START_TIME);
        
        long durationMs = startTime != null
                ? System.currentTimeMillis() - startTime
                : -1;
        
        log.info("Ongoing response [{}] | status={} | duration={}ms",
                request.getMethod(),
                request.getUriInfo().getPath(),
                response.getStatus(),
                durationMs
        );
    }
    
    private String resolveClientIp(ContainerRequestContext request) {
        List<String> forwaredFor = 
                request.getHeaders().get("X-Forwarded-For");
        
        if(forwaredFor != null && !forwaredFor.isEmpty()) {
            return forwaredFor.get(0).split(",")[0].trim();
        }
        
        return request.getHeaderString("X-Real-IP");
    }
    
}
