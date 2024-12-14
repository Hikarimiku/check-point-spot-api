package io.github.hikarimiku.checkpointspot.taskservice.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;


@Entity
@Data
@Table(name = "task")
public class TaskEntity {
    @Id
    @UuidGenerator
    private UUID id;

    private String name;

    private String status;

    @OneToOne
    @JoinColumn(name = "parentTaskId")
    private TaskEntity parentTask;
}

