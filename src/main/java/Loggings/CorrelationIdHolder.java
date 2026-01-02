/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Loggings;

import org.slf4j.MDC;

/**
 *
 * @author admin
 */
public class CorrelationIdHolder {
    
    private static final ThreadLocal<String> CORRELATION_ID = new ThreadLocal<>();
    
    private CorrelationIdHolder() { }
    
    public static void set(String id) {
        CORRELATION_ID.set(id);
        MDC.put("correlationId", id); // optional : for logging
    }
    
    public static String get() {
        return CORRELATION_ID.get();
    }
    
    public static void clear() {
        CORRELATION_ID.remove();
        MDC.remove("correlationId");
    }
}
