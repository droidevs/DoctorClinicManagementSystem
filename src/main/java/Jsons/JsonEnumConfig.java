/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/AnnotationType.java to edit this template
 */
package Jsons;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


// 1. Annotation to configure enum behavior
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface JsonEnumConfig {
    boolean caseSensitive() default false;
    boolean strict() default true; // Reject unknown values
    String unknownFallback() default ""; // Fallback enum for unknown values
    boolean serializeAsObject() default false; // Serialize as {value: "...", description: "..."}
    String[] aliases() default {}; // JSON aliases for enum values
}
