package io.github.hikarimiku.checkpointspot.taskservice.dto;


import lombok.Data;

import java.util.UUID;

@Data
public class TaskListDTO {

    private UUID id;

    private String name;

}
