/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package Enums;

public enum Day {
    
    MONDAY("Monday", "MON", 1),
    TUESDAY("Tuesday", "TUE", 2),
    WEDNESDAY("Wednesday", "WED", 3),
    THURSDAY("Thursday", "THU", 4),
    FRIDAY("Friday", "FRI", 5),
    SATURDAY("Saturday", "SAT", 6),
    SUNDAY("Sunday", "SUN", 7);
    
    private final String fullName;
    private final String shortCode;
    private final int dayOfWeek; // ISO: Monday=1, Sunday=7
    
    Day(String fullName, String shortCode, int dayOfWeek) {
        this.fullName = fullName;
        this.shortCode = shortCode;
        this.dayOfWeek = dayOfWeek;
    }
    
    public String getFullName() {
        return fullName;
    }
    
    public String getShortCode() {
        return shortCode;
    }
    
    public int getDayOfWeek() {
        return dayOfWeek;
    }
}
