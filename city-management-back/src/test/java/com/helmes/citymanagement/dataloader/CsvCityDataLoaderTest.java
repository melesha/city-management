package com.helmes.citymanagement.dataloader;

import com.helmes.citymanagement.model.City;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class CsvCityDataLoaderTest {

    @Autowired
    DataLoader csvCityDataLoader;

    @Test
    void testLoad_validFile() throws IOException {
        File file = new File("src/test/resources/cities-test.csv");

        List<City> cities = csvCityDataLoader.load(file);
        Assertions.assertEquals(3, cities.size());
    }

    @Test
    void testLoad_invalidFile() throws IOException {
        File file = new File("src/test/resources/cities-test-invalid.csv");

        List<City> cities = csvCityDataLoader.load(file);
        Assertions.assertTrue(cities.isEmpty());
    }
}
