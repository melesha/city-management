package com.helmes.citymanagement.dataloader;

import com.helmes.citymanagement.model.City;
import com.helmes.citymanagement.repository.CityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DBCityDataSaver implements DataSaver<City>{
    private static final Logger LOG = LoggerFactory.getLogger(DBCityDataSaver.class);

    private CityRepository cityRepository;

    public DBCityDataSaver(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public void save(List<City> list) {
        cityRepository.saveAll(list);
        LOG.info(list.size() + " cities were loaded into the DB");
    }
}
