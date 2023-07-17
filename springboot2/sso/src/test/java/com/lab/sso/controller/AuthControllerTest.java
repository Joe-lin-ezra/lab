package com.lab.sso.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lab.base.dto.sso.request.LoginDTO;
import com.lab.base.dto.sso.response.LoginResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private AuthController authController;

    private ObjectMapper objectMapper;

    @Before
    public void setUp() throws Exception {
//        MockitoAnnotations.openMocks(this);
        objectMapper = new ObjectMapper();
    }

    /**
     * Test login api and exceptions.
     */
    @Test
    public void login() throws Exception {
        LoginDTO loginRequest = new LoginDTO("test@mail.com", "password");
        String jsonPayload = objectMapper.writeValueAsString(loginRequest);

//        LoginResponse expectedResponse = new LoginResponse("token");
//        String expectedResponseBody = objectMapper.writeValueAsString(expectedResponse);

        mockMvc.perform(post("/sso/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonPayload))
                .andExpect(status().isOk());
//                .andExpect( content().json(  ) );
//				  .andExpect(content().json(expectedResponseBody));
    }

    @Test
    public void loginMethodArgumentNotValidException() throws Exception
    {
        LoginDTO loginRequest = new LoginDTO("test", "password");
        String jsonPayload = objectMapper.writeValueAsString(loginRequest);

        //        LoginResponse expectedResponse = new LoginResponse("token");
        //        String expectedResponseBody = objectMapper.writeValueAsString(expectedResponse);

        mockMvc.perform(post("/sso/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonPayload))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void loginException() throws Exception
    {
        LoginDTO loginRequest = new LoginDTO("test", "password");
        String jsonPayload = objectMapper.writeValueAsString(loginRequest);

        when(authController.login( any() )).thenThrow( new Exception() );

        mockMvc.perform(post("/sso/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonPayload))
                .andExpect(status().isInternalServerError());
    }

    /**
     * Test login api and exceptions.
     */
    @Test
    public void logout() {

    }

    @Test
    public void logoutMethodArgumentNotValidException() {

    }

    @Test
    public void logoutException() throws Exception
    {
        when(authController.logout( any() )).thenThrow( new Exception() );

        mockMvc.perform(post("/sso/logout")
                .contentType(MediaType.APPLICATION_JSON)
                .andExpect(status().isInternalServerError());
    }

    /**
     * Test login api and exceptions.
     */
    @Test
    public void register() {

    }

    @Test
    public void registerMethodArgumentNotValidException() {

    }

    /**
     * Test check-jwt api and exceptions.
     */
    @Test
    public void checkJwt() {

    }

    @Test
    public void checkJwtMethodArgumentNotValidException() {

    }
}