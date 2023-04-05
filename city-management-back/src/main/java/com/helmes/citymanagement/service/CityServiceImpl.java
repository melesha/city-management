package com.helmes.citymanagement.service;

import com.helmes.citymanagement.exception.NotFoundException;
import com.helmes.citymanagement.model.City;
import com.helmes.citymanagement.repository.CityRepository;
import com.helmes.citymanagement.vo.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class CityServiceImpl implements CityService {

    public static final String PROPERTY_NAME = "name";
    private CityRepository cityRepository;

    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public Page<City> findByName(String name, PageInfo pageInfo) {
        if (StringUtils.isNotEmpty(name)) {
            return cityRepository.findByNameContainsIgnoringCase(name,
                    PageRequest.of(pageInfo.getPageNumber(), pageInfo.getPageSize(), Sort.by(getDefaultSorting())));
        } else {
            return cityRepository.findAll(
                    PageRequest.of(pageInfo.getPageNumber(), pageInfo.getPageSize(), Sort.by(getDefaultSorting())));
        }
    }

    @Override
    public City findById(long id) {
        return cityRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("City with id: %d not found", id)));
    }

    @Override
    public City saveCity(City city) {
        City cityToUpdate = cityRepository.findById(city.getId())
                .orElseThrow(() -> new NotFoundException(String.format("City with id: %d not found", city.getId())));

        cityToUpdate.setName(city.getName());
        cityToUpdate.setPhoto(city.getPhoto());

        return cityRepository.save(cityToUpdate);
    }

    private List<Sort.Order> getDefaultSorting() {
        return Arrays.asList(Sort.Order.asc(PROPERTY_NAME));
    }
}
