package com.helmes.citymanagement.dataloader;

import com.helmes.citymanagement.model.City;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Component
public class SetupCity {

    private static final Logger LOG = LoggerFactory.getLogger(SetupCity.class);
    public static final String CITIES_CSV_FILE = "cities.csv";

    private DataLoader csvCityDataLoader;
    private DataSaver dbCityDataSaver;

    public SetupCity(DataLoader csvCityDataLoader, DataSaver dbCityDataSaver) {
        this.csvCityDataLoader = csvCityDataLoader;
        this.dbCityDataSaver = dbCityDataSaver;
    }

    @PostConstruct
    public void initCitiesFromFile() {
        List<City> cities = getCitiesFromFile(CITIES_CSV_FILE);
        dbCityDataSaver.save(cities);
    }

    private List<City> getCitiesFromFile(String file) {
        try {
            File csvCityFile = new ClassPathResource(file).getFile();
            return csvCityDataLoader.load(csvCityFile);
        } catch (IOException e) {
            LOG.error("Failed to load the file " + CITIES_CSV_FILE);
        }
        return Collections.emptyList();
    }
}
