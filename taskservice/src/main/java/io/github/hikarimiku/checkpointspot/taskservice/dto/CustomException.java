package io.github.hikarimiku.checkpointspot.taskservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class CustomException extends Exception {
    private final int errorCode;

    public CustomException(int errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

}
