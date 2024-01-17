package com.cci.usertaskmngt.controller;

import com.cci.usertaskmngt.dto.TaskDto;
import com.cci.usertaskmngt.service.impl.TaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api/users/{userId}/tasks")
public class TaskController {

    @Autowired
    private TaskServiceImpl taskService;

    @PostMapping()
    public ResponseEntity<TaskDto> createTask(@PathVariable int userId, @RequestBody TaskDto taskDto) {
        return ResponseEntity.ok(taskService.createTask(userId, taskDto));
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<TaskDto> updateTask(@PathVariable int userId, @PathVariable("taskId") int taskId, @RequestBody TaskDto taskDto) throws ParseException {
        return ResponseEntity.ok(taskService.updateTask(userId, taskId, taskDto));
    }

    @GetMapping()
    public ResponseEntity<List<TaskDto>> getAllTask(@PathVariable int userId) {
        return ResponseEntity.ok(taskService.findAll(userId));
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<TaskDto> getTask(@PathVariable int userId, @PathVariable("taskId") int taskId) {
        return ResponseEntity.ok(taskService.findByUserIdAndTaskId(userId, taskId));
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<?> deleteTask(@PathVariable int userId, @PathVariable("taskId") int taskId) {
        taskService.deleteTask(userId, taskId);
        return ResponseEntity.ok().build();
    }
}
