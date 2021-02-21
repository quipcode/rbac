package rbac.server.controllers;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import org.springframework.web.client.RestTemplate;
import rbac.server.BaseIT;




import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



import rbac.server.util.AuthControllerUtil;

@Slf4j
@SpringBootTest
public class AuthControllerTest extends BaseIT {


    @Autowired
    private RestTemplate restTemplate;

    public static final String API_ROOT = "/api/auth/";


    @Test
    void loginUserSuccess() throws Exception{
        mockMvc.perform(
                post(API_ROOT + "login")
//                        .contentType(MediaType.APPLICATION_JSON)
                        .param(AuthControllerUtil.REQUEST_PARAMETER_USERNAME, AuthControllerUtil.CORRECT_USERNAME)
                        .param(AuthControllerUtil.REQUEST_PARAMETER_PASSWORD, AuthControllerUtil.CORRECT_PASSWORD))
                .andExpect(status().is2xxSuccessful());


    }
    @Test
    void loginInvalidCredentials() throws Exception{
        mockMvc.perform(post(API_ROOT + "login")
                .param(AuthControllerUtil.REQUEST_PARAMETER_USERNAME, AuthControllerUtil.INCORRECT_USERNAME)
                .param(AuthControllerUtil.REQUEST_PARAMETER_PASSWORD, AuthControllerUtil.CORRECT_PASSWORD))
                .andExpect(status().isUnauthorized());

    }
}
