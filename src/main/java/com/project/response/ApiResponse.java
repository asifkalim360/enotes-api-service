package com.project.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@NoArgsConstructor 
@AllArgsConstructor
public class ApiResponse<T> {

    private boolean success;
    private String message;
    private T data;
         
    // error response
    public ApiResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}


/**
 ### Line by line

ApiResponse<T> → Generic banaya taaki koi bhi type ka data aa sake

success → frontend ko pata chale success/fail

message → human readable msg

data → DTO / List / Object

*/
