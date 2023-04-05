package com.helmes.citymanagement.service;

import com.helmes.citymanagement.model.City;
import com.helmes.citymanagement.vo.PageInfo;
import org.springframework.data.domain.Page;

public interface CityService {

    Page<City> findByName(String name, PageInfo pageInfo);

    City findById(long id);

    City saveCity(City city);
}
