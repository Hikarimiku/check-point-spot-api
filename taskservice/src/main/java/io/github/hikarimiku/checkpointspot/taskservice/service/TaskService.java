package io.github.hikarimiku.checkpointspot.taskservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.hikarimiku.checkpointspot.taskservice.dto.TaskDto;
import io.github.hikarimiku.checkpointspot.taskservice.dto.PagingDto;
import io.github.hikarimiku.checkpointspot.taskservice.dto.TaskListDTO;
import io.github.hikarimiku.checkpointspot.taskservice.dto.TaskSearchDto;
import io.github.hikarimiku.checkpointspot.taskservice.entity.TaskEntity;
import io.github.hikarimiku.checkpointspot.taskservice.repo.TaskRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

import static io.github.hikarimiku.checkpointspot.taskservice.constant.TaskConstants.*;

@Service
@Slf4j
public class TaskService {
    @Autowired
    TaskRepository taskRepository;

    @Autowired
    ObjectMapper objectMapper;

    public TaskDto createTask(TaskDto taskDto){
        try{

            TaskEntity taskEntity = objectMapper.convertValue(taskDto, TaskEntity.class);
            taskEntity.setStatus(IN_PROGRESS);
            taskEntity = taskRepository.save(taskEntity);
            return objectMapper.convertValue(taskEntity, TaskDto.class);
        }catch (Exception e){
            log.error("Error createTask for " + taskDto +": " + e.getMessage());
            throw  e;
        }
    }

    public List<TaskDto> getListTask(TaskSearchDto taskSearchDto){
        try{
            PagingDto pagingDto = taskSearchDto.getPaging();
            Sort sort = Sort.by(Sort.Order.by(pagingDto.getSortBy())
                    .with(Sort.Direction.fromString(pagingDto.getSortDirection())));
            PageRequest pageable = PageRequest.of(pagingDto.getPage(), pagingDto.getSize(), sort);

            Page<Object[]> results = taskRepository.search(taskSearchDto.getName().toUpperCase(),
                    taskSearchDto.getStatus().toUpperCase(), pageable);

            return results.map(result -> {
                TaskEntity taskEntity = (TaskEntity) result[0];  // Get TaskEntity
                Long dependentTaskCount = (Long) result[1];      // Get dependent task count
                Long dependentDoneTaskCount = (Long) result[2];
                Long dependentCompleteTaskCount = (Long) result[3];
                Long totalData = (Long) result[4];

                // Map to TaskWithDependenciesDTO
                TaskDto dto = objectMapper.convertValue(taskEntity, TaskDto.class);
                dto.setTotalDependency(dependentTaskCount);
                dto.setTotalDependencyDone(dependentDoneTaskCount);
                dto.setTotalDependencyComplete(dependentCompleteTaskCount);
                dto.setTotalData(totalData);

                return dto;
            }).toList();

        }catch (Exception e){
            log.error("Error getListTask for " + taskSearchDto +": " + e.getMessage());
            throw  e;
        }
    }

    public List<TaskListDTO> getListAllTask(){
        try{
            List<TaskEntity> taskEntityList = taskRepository.findAll();
            return taskEntityList.stream().map(e ->  objectMapper.convertValue(e, TaskListDTO.class)).toList();
        }catch (Exception e){
            log.error("Error getListAllTask " + e.getMessage());
            throw  e;
        }
    }

    public TaskDto updateTask(TaskDto taskDto){
        try{

            TaskEntity taskEntity = objectMapper.convertValue(taskDto, TaskEntity.class);
            String status = taskEntity.getStatus();

            if(!status.equals(IN_PROGRESS) && !status.equals(DONE) && !status.equals(COMPLETED)){
                throw new RuntimeException("Invalid task status");
            }

            taskEntity = taskRepository.save(taskEntity);
            return objectMapper.convertValue(taskEntity, TaskDto.class);
        }catch (Exception e){
            log.error("Error createTask for " + taskDto +": " + e.getMessage());
            throw  e;
        }
    }
}
