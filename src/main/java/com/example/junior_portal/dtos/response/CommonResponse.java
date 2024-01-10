package com.example.junior_portal.dtos.response;

import lombok.Builder;
import org.springframework.http.HttpStatus;

@Builder
public class CommonResponse {
    private HttpStatus status;
    private String message;
    private Object answer;
}
