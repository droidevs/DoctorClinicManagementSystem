/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/AnnotationType.java to edit this template
 */
package Jsons;


public interface JsonEnum {
    String getJsonValue();
    String getDescription();
    default boolean isDeprecated() { return false; }
}
