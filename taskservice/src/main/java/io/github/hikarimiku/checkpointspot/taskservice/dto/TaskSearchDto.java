package io.github.hikarimiku.checkpointspot.taskservice.dto;


import lombok.Data;

@Data
public class TaskSearchDto {

    private String name;

    private String status;

    private PagingDto paging;
}
