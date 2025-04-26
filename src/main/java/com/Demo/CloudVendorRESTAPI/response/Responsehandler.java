package com.Demo.CloudVendorRESTAPI.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class Responsehandler {

    public static ResponseEntity<Object> responseBuilder(
            String message,
            Object responseObject,
            HttpStatus httpStatus
    )
    {
        Map<String,Object> response=new HashMap<>();
        response.put("message",message);

        response.put("data",responseObject);
        response.put("httpstatus",httpStatus);

        return new ResponseEntity<>(response,httpStatus);
    }
}
