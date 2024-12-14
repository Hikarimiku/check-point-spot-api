package io.github.hikarimiku.checkpointspot.taskservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseDTO<T> {

    private int code;

    private String message;

    private T data;

}
