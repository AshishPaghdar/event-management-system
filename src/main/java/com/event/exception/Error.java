package com.event.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
public class Error {
    private HttpStatus status;
    Map<String, List<String>> error;
}
