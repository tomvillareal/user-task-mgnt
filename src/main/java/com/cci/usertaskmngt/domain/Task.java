package com.cci.usertaskmngt.domain;

import com.cci.usertaskmngt.constant.TaskStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Task {

    @Id
    @GeneratedValue
    private int taskId;

    private String name;

    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dateTime;

    private TaskStatus status;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
