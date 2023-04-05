package com.helmes.citymanagement.rest;

import com.helmes.citymanagement.service.TokenService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = AuthRestServiceImpl.class)
class AuthRestServiceImplTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    TokenService tokenService;

    @Test
    void getToken_unauthorized() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/city-management/token")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser()
    void getToken_success() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/city-management/token")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }

}
