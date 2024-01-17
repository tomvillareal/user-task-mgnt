package com.cci.usertaskmngt.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.cci.usertaskmngt.dto.UserDto;
import com.cci.usertaskmngt.service.impl.UserServiceImpl;
import com.cci.usertaskmngt.util.JsonUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@WebMvcTest(value = UserController.class)
public class UserControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserServiceImpl userService;

    @Test
    public void whenPostUser_thenCreateUser() throws Exception {
        UserDto userDto = new UserDto("test", "John", " Smith");
        given(userService.create(Mockito.any())).willReturn(userDto);

        mvc.perform(post("/api/users").contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.toJson(userDto))).andExpect(status().isOk());

        verify(userService, VerificationModeFactory.times(1)).create(Mockito.any());
        reset(userService);
    }

    @Test
    public void whenPutUser_thenUpdateUser() throws Exception {
        UserDto userDto = new UserDto("test", "John", " Smith");
        given(userService.update(anyLong(), Mockito.any())).willReturn(userDto);

        mvc.perform(put("/api/users/1").contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.toJson(userDto))).andExpect(status().isOk());

        verify(userService, VerificationModeFactory.times(1)).update(anyLong(), Mockito.any());
        reset(userService);
    }

    @Test
    public void whenGetUser_thenGetUser() throws Exception {
        UserDto userDto = new UserDto("test", "John", " Smith");
        given(userService.findById(anyLong())).willReturn(userDto);

        mvc.perform(get("/api/users/1")).andExpect(status().isOk());

        verify(userService, VerificationModeFactory.times(1)).findById(anyLong());
        reset(userService);
    }

    @Test
    public void whenGetAllUser_thenGetAllUser() throws Exception {
        UserDto user1 = new UserDto("test", "John", " Smith");
        UserDto user2 = new UserDto("test2", "John", " Smith");
        List<UserDto> users = Arrays.asList(user1, user2);
        given(userService.findAll()).willReturn(users);

        mvc.perform(get("/api/users")).andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));

        verify(userService, VerificationModeFactory.times(1)).findAll();
        reset(userService);
    }
}
