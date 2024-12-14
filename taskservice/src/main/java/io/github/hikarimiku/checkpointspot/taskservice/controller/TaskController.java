package io.github.hikarimiku.checkpointspot.taskservice.controller;

import io.github.hikarimiku.checkpointspot.taskservice.dto.*;
import io.github.hikarimiku.checkpointspot.taskservice.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {
    @Autowired
    TaskService taskService;

    @PostMapping
    public ResponseEntity<ResponseDTO<TaskDto>> createTask(@RequestBody TaskDto taskDto) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO<>(200,
                    "Successfully created task", taskService.createTask(taskDto)));
    }

    @PostMapping("/search")
    public ResponseEntity<ResponseDTO<List<TaskDto>>> search(@RequestBody TaskSearchDto taskSearchDto) {
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO<>(200,
                "Successfully retrieve list of tasks", taskService.getListTask(taskSearchDto)));
    }

    @GetMapping
    public ResponseEntity<ResponseDTO<List<TaskListDTO>>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO<>(200,
                "Successfully retrieve all list of tasks", taskService.getListAllTask()));
    }

    @PutMapping
    public ResponseEntity<ResponseDTO<TaskDto>> updateTask(@RequestBody TaskDto taskDto) {
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO<>(200,
                "Successfully update task status", taskService.updateTask(taskDto)));
    }
}
