package com.cci.usertaskmngt.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto {

    private int id;
    private String name;
    private String description;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateTime;
    private String status;

    public TaskDto(String name, String description, LocalDateTime dateTime) {
        this.name = name;
        this.description = description;
        this.dateTime = dateTime;
    }
}
