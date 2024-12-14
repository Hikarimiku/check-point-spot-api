package io.github.hikarimiku.checkpointspot.taskservice.exceptionhandler;

import io.github.hikarimiku.checkpointspot.taskservice.dto.CustomException;
import io.github.hikarimiku.checkpointspot.taskservice.dto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDTO<Object>> handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDTO<>(500, e.getMessage(), null));
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ResponseDTO<Object>> handleCustomException(CustomException e) {
        int code = e.getErrorCode();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDTO<>(code, e.getMessage(), null));
    }
}
