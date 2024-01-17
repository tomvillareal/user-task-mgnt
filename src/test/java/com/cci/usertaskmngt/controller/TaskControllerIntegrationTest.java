package com.cci.usertaskmngt.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.cci.usertaskmngt.dto.TaskDto;
import com.cci.usertaskmngt.service.impl.TaskServiceImpl;
import com.cci.usertaskmngt.util.JsonUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@WebMvcTest(value = TaskController.class)
public class TaskControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private TaskServiceImpl taskService;

    @Test
    public void whenPostTask_thenCreateTask() throws Exception {
        TaskDto taskDto = new TaskDto("task", "test", LocalDateTime.now());
        given(taskService.createTask(anyLong(), any())).willReturn(taskDto);

        mvc.perform(post("/api/users/1/tasks").contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.toJson(taskDto))).andExpect(status().isOk());

        verify(taskService, VerificationModeFactory.times(1)).createTask(anyLong(), any());
        reset(taskService);
    }

    @Test
    public void whenPutTask_thenUpdateTask() throws Exception {
        TaskDto taskDto = new TaskDto("task", "test", LocalDateTime.now());
        given(taskService.updateTask(anyLong(), anyLong(),any())).willReturn(taskDto);

        mvc.perform(put("/api/users/1/tasks/1").contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.toJson(taskDto))).andExpect(status().isOk());

        verify(taskService, VerificationModeFactory.times(1)).updateTask(anyLong(), anyLong(), any());
        reset(taskService);
    }

    @Test
    public void whenGetTask_thenGetTaskByUser() throws Exception {
        TaskDto taskDto = new TaskDto("task", "test", LocalDateTime.now());
        given(taskService.findByUserIdAndTaskId(anyLong(), anyLong())).willReturn(taskDto);

        mvc.perform(get("/api/users/1/tasks/1").contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.toJson(taskDto))).andExpect(status().isOk());

        verify(taskService, VerificationModeFactory.times(1)).findByUserIdAndTaskId(anyLong(), anyLong());
        reset(taskService);
    }

    @Test
    public void whenGetAllUserTask_thenGetAllTaskByUser() throws Exception {
        TaskDto task1 = new TaskDto("task", "test", LocalDateTime.now());
        TaskDto task2 = new TaskDto("task1", "test", LocalDateTime.now());
        List<TaskDto> tasks = Arrays.asList(task1, task2);

        given(taskService.findAll(anyLong())).willReturn(tasks);

        mvc.perform(get("/api/users/1/tasks")).andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(2)));

        verify(taskService, VerificationModeFactory.times(1)).findAll(anyLong());
        reset(taskService);
    }

    @Test
    public void whenDeleteTask_thenDeleteTaskByUser() throws Exception {
        mvc.perform(delete("/api/users/1/tasks/1")).andExpect(status().isOk());

        verify(taskService, VerificationModeFactory.times(1)).deleteTask(anyLong(), anyLong());
        reset(taskService);
    }
}
