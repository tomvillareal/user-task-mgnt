package com.cci.usertaskmngt.schedule;

import com.cci.usertaskmngt.constant.TaskStatus;
import com.cci.usertaskmngt.domain.Task;
import com.cci.usertaskmngt.repository.TaskRepository;
import jakarta.transaction.Transactional;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@Transactional
public class UserTaskSchedule {

    private final TaskRepository taskRepository;

    public UserTaskSchedule(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Scheduled(cron = "0 * * * * *")
    public void updatePendingTasks() {
        List<Task> tasks = taskRepository.findAllByStatusAndDateTimeBefore(TaskStatus.PENDING, LocalDateTime.now());
        tasks.forEach( task -> {
            task.setStatus(TaskStatus.DONE);
        });

        taskRepository.saveAll(tasks);
    }
}
