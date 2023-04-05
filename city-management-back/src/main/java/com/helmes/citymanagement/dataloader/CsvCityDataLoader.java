package com.helmes.citymanagement.dataloader;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.helmes.citymanagement.model.City;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Component
public class CsvCityDataLoader implements DataLoader<City> {
    private static final Logger LOGGER = LoggerFactory.getLogger(CsvCityDataLoader.class);

    @Override
    public List<City> load(File file) {
        CsvSchema schema = CsvSchema.emptySchema().withHeader();
        CsvMapper mapper = CsvMapper.csvBuilder().build();
        try {
            MappingIterator<City> mappingIterator = mapper.readerFor(City.class).with(schema).readValues(file);
            return mappingIterator.readAll();
        } catch (IOException e) {
            LOGGER.error("Failed to initialize cities from the file " + file, e);
            return Collections.emptyList();
        }
    }
}
