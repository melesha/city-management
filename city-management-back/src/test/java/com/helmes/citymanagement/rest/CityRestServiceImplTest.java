package com.helmes.citymanagement.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.helmes.citymanagement.exception.NotFoundException;
import com.helmes.citymanagement.model.City;
import com.helmes.citymanagement.service.CityService;
import com.helmes.citymanagement.vo.PageInfo;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.Arrays;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = CityRestServiceImpl.class)
class CityRestServiceImplTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    CityService mockCityService;

    @MockBean
    Page<City> page;

    City RECORD_1 = new City(1l, "City1", "photo");
    City RECORD_2 = new City(2l, "City2", "photo");
    City RECORD_3 = new City(3l, "City3", "photo");

    @Test
    @WithMockUser(username = "admin", roles={"ADMIN"})
    void getAllCities_success() throws Exception {
        Mockito.when(page.getContent()).thenReturn(Arrays.asList(RECORD_1, RECORD_2, RECORD_3));
        Mockito.when(mockCityService.findByName(Mockito.anyString(), Mockito.any(PageInfo.class))).thenReturn(page);
        mockMvc.perform(MockMvcRequestBuilders.get("/city-management/cities?name=Test&pageNumber=1&pageSize=10")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", roles={"ADMIN"})
    void getAllCities_badRequest() throws Exception {
        Mockito.when(mockCityService.findByName(Mockito.any(String.class), Mockito.any(PageInfo.class))).thenReturn(page);
        mockMvc.perform(MockMvcRequestBuilders.get("/city-management/cities")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(username = "admin", roles={"ADMIN"})
    void getAllCities_bindException() throws Exception {
        Mockito.when(mockCityService.findByName(Mockito.any(String.class), Mockito.any(PageInfo.class))).thenReturn(page);
        mockMvc.perform(MockMvcRequestBuilders.get("/city-management/cities?pageNumber=a")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void getCity_unauthorized() throws Exception {
        Mockito.when(mockCityService.findById(Mockito.anyLong())).thenReturn(RECORD_1);
        mockMvc.perform(MockMvcRequestBuilders.get("/city-management/cities/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isUnauthorized());
    }
    @Test
    @WithMockUser(username = "admin", roles={"ALLOW_EDIT"})
    void getCity_success() throws Exception {
        Mockito.when(mockCityService.findById(Mockito.anyLong())).thenReturn(RECORD_1);
        mockMvc.perform(MockMvcRequestBuilders.get("/city-management/cities/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", roles={"ALLOW_EDIT"})
    void getCity_notFound() throws Exception {
        Mockito.when(mockCityService.findById(Mockito.anyLong())).thenThrow(NotFoundException.class);
        mockMvc.perform(MockMvcRequestBuilders.get("/city-management/cities/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(username = "admin", roles={"ALLOW_EDIT"})
    void getCity_internalServerError() throws Exception {
        Mockito.when(mockCityService.findById(Mockito.anyLong())).thenThrow(NullPointerException.class);
        mockMvc.perform(MockMvcRequestBuilders.get("/city-management/cities/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isInternalServerError());
    }
    @Test
    void updateCity_anonymousForbidden() throws Exception {
        Mockito.when(mockCityService.findById(Mockito.anyLong())).thenReturn(RECORD_1);
        mockMvc.perform(MockMvcRequestBuilders.put("/city-management/cities", RECORD_2)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isForbidden());
    }
    @Test
    @WithMockUser(username = "admin", roles={"ADMIN"})
    void updateCity_withoutRoleForbidden() throws Exception {
        Mockito.when(mockCityService.findById(Mockito.anyLong())).thenReturn(RECORD_1);
        Mockito.when(mockCityService.saveCity(Mockito.any(City.class))).thenReturn(RECORD_2);
        mockMvc.perform(MockMvcRequestBuilders.put("/city-management/cities", RECORD_2)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "admin", roles={"ALLOW_EDIT"})
    void updateCity_success() throws Exception {
        Mockito.when(mockCityService.saveCity(Mockito.any(City.class))).thenReturn(RECORD_2);
        mockMvc.perform(MockMvcRequestBuilders.put("/city-management/cities")
                .with(csrf())
                .content(mapper.writeValueAsString(RECORD_2))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", roles={"ALLOW_EDIT"})
    void updateCity_internalServerError() throws Exception {
        Mockito.when(mockCityService.saveCity(Mockito.any(City.class))).thenThrow(NullPointerException.class);
        mockMvc.perform(MockMvcRequestBuilders.put("/city-management/cities")
                .with(csrf())
                .content(mapper.writeValueAsString(RECORD_2))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isInternalServerError());
    }

}
