package Enum.exceptions;

import Enum.dto.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
public class AppGlobalExceptionHandler {
    @ExceptionHandler(value = {EnumBaseException.class})
    @ResponseStatus(BAD_REQUEST)
    public ResponseEntity<?> handler(EnumBaseException exception){
        var response = ApiResponse.builder().data(exception.getMessage()).build();
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler({IOException.class})
    @ResponseStatus(BAD_REQUEST)
    public ResponseEntity<?> handler(IOException exception){
        var response = ApiResponse.builder().data(exception.getMessage()).build();
        return ResponseEntity.badRequest().body(response);
    }


    @ExceptionHandler({UserNotFoundException.class})
    @ResponseStatus(BAD_REQUEST)
    public ResponseEntity<?> handler(UserNotFoundException exception){
        var response = ApiResponse.builder().data(exception.getMessage()).build();
        return ResponseEntity.badRequest().body(response);
    }

}
