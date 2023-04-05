package com.helmes.citymanagement.service;

import com.helmes.citymanagement.exception.NotFoundException;
import com.helmes.citymanagement.model.City;
import com.helmes.citymanagement.repository.CityRepository;
import com.helmes.citymanagement.vo.PageInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CityServiceImplTest {

    public static final String NAME_UPD = "nameUpd";
    public static final String PHOTO_UPD = "photoUpd";
    @MockBean
    private CityRepository cityRepository;

    @Autowired
    private CityService cityService;

    City RECORD_1 = new City(1l, "City1", "photo");
    City RECORD_2 = new City(2l, "City2", "photo");
    City RECORD_3 = new City(3l, "City3", "photo");
    Page<City> emptyCities = new PageImpl<>(Collections.emptyList());
    Page<City> cities = new PageImpl<>(Arrays.asList(RECORD_1, RECORD_2, RECORD_3));
    PageInfo pageInfo = new PageInfo(1, 10, null);


    @BeforeEach
    public void init() {
        Mockito.when(cityRepository.findAll(Mockito.any(Pageable.class))).thenReturn(cities);
        Mockito.when(cityRepository.findByNameContainsIgnoringCase(Mockito.anyString(), Mockito.any(Pageable.class)))
                .thenReturn(emptyCities);
        Mockito.when(cityRepository.findById(1l)).thenReturn(Optional.empty());
        Mockito.when(cityRepository.findById(2l)).thenReturn(Optional.of(RECORD_2));
    }

    @Test
    void findAll() {
        Page<City> result = cityService.findByName("", pageInfo);

        assertEquals(3, result.getTotalElements());
        assertEquals(RECORD_1, result.getContent().get(0));
        assertEquals(RECORD_2, result.getContent().get(1));
        assertEquals(RECORD_3, result.getContent().get(2));
    }
    @Test
    void findByName() {
        Page<City> result = cityService.findByName("Test", pageInfo);

        assertEquals(0, result.getTotalElements());
        assertTrue(result.getContent().isEmpty());
    }

    @Test
    void findById_notFound() {
        assertThrows(NotFoundException.class, () -> {
            cityService.findById(1l);
        });
    }

    @Test
    void findById() {
        City result = cityService.findById(2l);
        assertEquals(RECORD_2, result);
    }

    @Test
    void save_notFound() {
        RECORD_1.setName(NAME_UPD);
        RECORD_1.setPhoto(PHOTO_UPD);

        assertThrows(NotFoundException.class, () -> {
            cityService.saveCity(RECORD_1);
        });
    }

    @Test
    void save() {
        RECORD_2.setName(NAME_UPD);
        RECORD_2.setPhoto(PHOTO_UPD);

        Mockito.when(cityRepository.save(RECORD_2)).thenReturn(RECORD_2);
        City result = cityService.saveCity(RECORD_2);

        assertEquals(2l, result.getId());
        assertEquals(NAME_UPD, result.getName());
        assertEquals(PHOTO_UPD, result.getPhoto());
    }



}
