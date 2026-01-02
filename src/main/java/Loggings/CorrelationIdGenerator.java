/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Loggings;

import java.util.UUID;

public final class CorrelationIdGenerator {

    private CorrelationIdGenerator() { }

    public static String generate() {
        // RFC 4122 UUID v4
        return UUID.randomUUID().toString();
    }
}
