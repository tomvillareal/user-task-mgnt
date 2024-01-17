package com.cci.usertaskmngt.repository;

import com.cci.usertaskmngt.constant.TaskStatus;
import com.cci.usertaskmngt.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {
    Optional<Task> findByUser_UserIdAndTaskId(long userId, long taskId);
    List<Task> findAllByUser_UserId(long userId);

    List<Task> findAllByStatusAndDateTimeBefore(TaskStatus status, LocalDateTime localDateTime);
}
