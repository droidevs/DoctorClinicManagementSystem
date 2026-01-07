/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Jsons;


// 1. BooleanEnum interface
public interface BooleanEnum {
    boolean getBooleanValue();
    String getBooleanRepresentation(); // Optional: "true"/"false", "yes"/"no", etc.
    
    // Default implementations for common patterns
    default boolean isTrue() {
        return getBooleanValue();
    }
    
    default boolean isFalse() {
        return !getBooleanValue();
    }
}
