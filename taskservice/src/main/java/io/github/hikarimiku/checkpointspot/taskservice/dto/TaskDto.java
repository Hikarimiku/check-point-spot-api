package io.github.hikarimiku.checkpointspot.taskservice.dto;


import lombok.Data;

import java.util.UUID;

@Data
public class TaskDto {

    private UUID id;

    private String name;

    private String status;

    private TaskDto parentTask;

    private Long totalDependency;

    private Long totalDependencyComplete;

    private Long totalDependencyDone;
}
