/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Filters;


import jakarta.ws.rs.Priorities;

/**
 * Centralized filter priorities for custom filters
 */
public final class FilterPriority {

    private FilterPriority() {} // prevent instantiation

    /**
     * Generate/request correlation ID first
     */
    public static final int CORRELATION_ID = 100;

    /**
     * Add CORS headers (preflight support)
     */
    public static final int CORS = 200;
    /**
     * JWT authentication
     */
    public static final int JWT_SECURITY = 400;
   
    public static final int AUDIT = 500;     // audit filter
}
