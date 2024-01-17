package com.cci.usertaskmngt.service;

import com.cci.usertaskmngt.dto.TaskDto;

import java.text.ParseException;
import java.util.List;

public interface TaskService {

    TaskDto createTask(long userId, TaskDto taskDto);
    TaskDto updateTask(long userId, long taskId, TaskDto taskDto) throws ParseException;
    TaskDto findByUserIdAndTaskId(long userId, long taskId);
    List<TaskDto> findAll(long userId);
    void deleteTask(long userId, long taskId);
}
