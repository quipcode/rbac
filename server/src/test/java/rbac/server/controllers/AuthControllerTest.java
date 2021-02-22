package rbac.server.controllers;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MvcResult;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import rbac.server.BaseIT;
import rbac.server.util.AuthControllerUtil;
import rbac.server.payload.request.LoginRequest;
import rbac.server.payload.request.SignupRequest;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@SpringBootTest
@Transactional
@Rollback
public class AuthControllerTest extends BaseIT {
    public static final String API_ROOT = "/api/auth/";

    @Test
    void loginUserSuccess() throws Exception{
        ObjectMapper requestMap = new ObjectMapper();
        LoginRequest loginRequest = new LoginRequest();

        loginRequest.setUsername(AuthControllerUtil.CORRECT_USERNAME);
        loginRequest.setPassword(AuthControllerUtil.CORRECT_PASSWORD);

        String request = requestMap.writeValueAsString(loginRequest);

        mockMvc.perform(
                post(API_ROOT + "login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request)
        )
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void loginInvalidCredentials() throws Exception{
        ObjectMapper requestMap = new ObjectMapper();
        LoginRequest loginRequest = new LoginRequest();

        loginRequest.setUsername(AuthControllerUtil.INCORRECT_USERNAME);
        loginRequest.setPassword(AuthControllerUtil.CORRECT_PASSWORD);

        String request = requestMap.writeValueAsString(loginRequest);

        mockMvc.perform(post(API_ROOT + "login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void loginIncorrectMethod() throws Exception{
        ObjectMapper requestMap = new ObjectMapper();
        LoginRequest loginRequest = new LoginRequest();

        loginRequest.setUsername(AuthControllerUtil.INCORRECT_USERNAME);
        loginRequest.setPassword(AuthControllerUtil.CORRECT_PASSWORD);

        String request = requestMap.writeValueAsString(loginRequest);

        mockMvc.perform(post(API_ROOT + "login")
                .content(request))
                .andExpect(status().isUnsupportedMediaType());
    }

    @Test
    public void signUpExistingEmail() throws Exception{
        ObjectMapper requestMap = new ObjectMapper();
        SignupRequest signupRequest = new SignupRequest();

        signupRequest.setUsername(AuthControllerUtil.INCORRECT_USERNAME);
        signupRequest.setPassword(AuthControllerUtil.CORRECT_PASSWORD);
        signupRequest.setEmail(AuthControllerUtil.CORRECT_EMAIL);

        String request = requestMap.writeValueAsString(signupRequest);

        MvcResult result = mockMvc.perform(post(API_ROOT + "register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request)).andReturn();

        MockHttpServletResponse response = result.getResponse();

        ObjectMapper responseMapper = new ObjectMapper();

        TypeReference<HashMap<String, String>> typeRef
                = new TypeReference<HashMap<String, String>>() {};
        Map<String, String> responseMap = responseMapper.readValue(response.getContentAsString(), typeRef);

        assertEquals(responseMap.get("message"), "Error: Email is already in use");

    }

    @Test
    public void signUpExistingUsername() throws Exception{
        ObjectMapper requestMap = new ObjectMapper();
        SignupRequest signupRequest = new SignupRequest();

        signupRequest.setUsername(AuthControllerUtil.CORRECT_USERNAME);
        signupRequest.setPassword(AuthControllerUtil.CORRECT_PASSWORD);
        signupRequest.setEmail(AuthControllerUtil.INCORRECT_EMAIL);

        String request = requestMap.writeValueAsString(signupRequest);

        MvcResult result = mockMvc.perform(post(API_ROOT + "register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request)).andReturn();

        MockHttpServletResponse response = result.getResponse();

        ObjectMapper responseMapper = new ObjectMapper();

        TypeReference<HashMap<String, String>> typeRef
                = new TypeReference<HashMap<String, String>>() {};
        Map<String, String> responseMap = responseMapper.readValue(response.getContentAsString(), typeRef);

        assertEquals(responseMap.get("message"), "Error: Username is already taken");

    }

    @Test
    public void signUpNewUser() throws Exception{
        ObjectMapper requestMap = new ObjectMapper();
        SignupRequest signupRequest = new SignupRequest();

        signupRequest.setUsername(AuthControllerUtil.INCORRECT_USERNAME);
        signupRequest.setPassword(AuthControllerUtil.INCORRECT_PASSWORD);
        signupRequest.setEmail(AuthControllerUtil.INCORRECT_EMAIL);

        String request = requestMap.writeValueAsString(signupRequest);

        MvcResult result = mockMvc.perform(post(API_ROOT + "register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request)).andReturn();

        MockHttpServletResponse response = result.getResponse();

        ObjectMapper responseMapper = new ObjectMapper();

        TypeReference<HashMap<String, String>> typeRef
                = new TypeReference<HashMap<String, String>>() {};
        Map<String, String> responseMap = responseMapper.readValue(response.getContentAsString(), typeRef);

        assertEquals(responseMap.get("message"), "User registered successfully");

    }
}

