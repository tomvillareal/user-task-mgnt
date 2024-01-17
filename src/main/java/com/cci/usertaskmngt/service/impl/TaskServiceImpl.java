package com.cci.usertaskmngt.service.impl;

import com.cci.usertaskmngt.constant.TaskStatus;
import com.cci.usertaskmngt.domain.Task;
import com.cci.usertaskmngt.domain.User;
import com.cci.usertaskmngt.dto.TaskDto;
import com.cci.usertaskmngt.repository.TaskRepository;
import com.cci.usertaskmngt.repository.UserRepository;
import com.cci.usertaskmngt.service.TaskService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TaskServiceImpl implements TaskService {

    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    private final ModelMapper modelMapper;

    public TaskServiceImpl(UserRepository userRepository, TaskRepository taskRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public TaskDto createTask(long userId, TaskDto taskDto) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found."));

        Task task = modelMapper.map(taskDto, Task.class);
        task.setUser(user);
        task.setStatus(TaskStatus.PENDING);

        taskRepository.save(task);

        return modelMapper.map(task, TaskDto.class);
    }

    @Override
    public TaskDto updateTask(long userId, long taskId, TaskDto taskDto) {
        Task task = taskRepository.findByUser_UserIdAndTaskId(userId, taskId)
                .orElseThrow(() -> new IllegalArgumentException("Task not found."));
        task.setName(taskDto.getName());
        task.setDescription(taskDto.getDescription());
        task.setDateTime(taskDto.getDateTime());

        taskRepository.save(task);

        return modelMapper.map(task, TaskDto.class);
    }

    @Override
    public TaskDto findByUserIdAndTaskId(long userId, long taskId) {
        Task task = taskRepository.findByUser_UserIdAndTaskId(userId, taskId)
                .orElseThrow(() -> new IllegalArgumentException("Task not found."));
        return modelMapper.map(task, TaskDto.class);
    }

    @Override
    public List<TaskDto> findAll(long userId) {
        return taskRepository.findAllByUser_UserId(userId).stream()
                .map((element) -> modelMapper.map(element, TaskDto.class)).collect(Collectors.toList());
    }

    @Override
    public void deleteTask(long userId, long taskId) {
        Task task = taskRepository.findByUser_UserIdAndTaskId(userId, taskId)
                .orElseThrow(() -> new IllegalArgumentException("Task not found."));

        taskRepository.delete(task);
    }
}
