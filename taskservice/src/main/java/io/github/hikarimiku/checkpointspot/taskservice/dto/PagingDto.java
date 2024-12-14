package io.github.hikarimiku.checkpointspot.taskservice.dto;


import lombok.Data;

@Data
public class PagingDto {
    private int page = 0; // Default to first page
    private int size = 10; // Default to 10 items per page
    private String sortBy = "id"; // Default to sorting by "id"
    private String sortDirection = "ASC";
}
