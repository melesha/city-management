package com.helmes.citymanagement.dataloader;

import com.helmes.citymanagement.model.City;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

@SpringBootTest
class SetupCityTest {


    @MockBean
    DataLoader dataLoaderMock;

    @MockBean
    DataSaver dataSaver;

    @Autowired
    SetupCity setupCity;

    City RECORD_1 = new City(1l, "City1", "photo");
    City RECORD_2 = new City(2l, "City2", "photo");
    City RECORD_3 = new City(3l, "City3", "photo");

    @Test
    void initCities_emptyFile() {
        Mockito.when(dataLoaderMock.load(Mockito.any(File.class))).thenReturn(Collections.emptyList());
        setupCity.initCitiesFromFile();
        Mockito.verify(dataSaver, Mockito.times(1)).save(Collections.emptyList());
    }

    @Test
    void initCities_goodFile() {
        Mockito.when(dataLoaderMock.load(Mockito.any(File.class))).thenReturn(Arrays.asList(RECORD_1, RECORD_2, RECORD_3));
        setupCity.initCitiesFromFile();
        Mockito.verify(dataLoaderMock, Mockito.times(2)).load(Mockito.any(File.class));
        Mockito.verify(dataSaver, Mockito.times(2)).save(Mockito.anyList());
    }

}
