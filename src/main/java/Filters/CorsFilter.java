/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Filters;

import jakarta.annotation.Priority;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.ext.Provider;

import java.io.IOException;

/**
 * Adds CORS headers to all responses
 */
@Provider
@Priority(FilterPriority.CORS)
public class CorsFilter implements ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext requestContext,
                       ContainerResponseContext responseContext) throws IOException {

        // Allow requests from any origin (production: restrict to your domain)
        responseContext.getHeaders().add("Access-Control-Allow-Origin", "*");

        // Allowed HTTP methods
        responseContext.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, PATCH");

        // Allowed headers in request
        responseContext.getHeaders().add("Access-Control-Allow-Headers", 
                "origin, content-type, accept, authorization, x-requested-with");

        // Expose headers to the client if needed
        responseContext.getHeaders().add("Access-Control-Expose-Headers", "Authorization, Content-Disposition");

        // Max age for preflight cache
        responseContext.getHeaders().add("Access-Control-Max-Age", "3600");
    }
}
