/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Responses;

import java.time.LocalDateTime;

public class ErrorResponse {

    public String code;
    public String message;
    public int status;
    public LocalDateTime timestamp;

    public static ErrorResponse of(String code, String message, int status) {
        ErrorResponse r = new ErrorResponse();
        r.code = code;
        r.message = message;
        r.status = status;
        r.timestamp = LocalDateTime.now();
        return r;
    }
}
