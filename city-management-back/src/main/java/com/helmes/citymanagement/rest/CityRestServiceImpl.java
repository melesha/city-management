package com.helmes.citymanagement.rest;

import com.helmes.citymanagement.model.City;
import com.helmes.citymanagement.service.CityService;
import com.helmes.citymanagement.vo.Filter;
import com.helmes.citymanagement.vo.PageInfo;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class CityRestServiceImpl implements CityRestService {

    private final CityService cityService;

    public CityRestServiceImpl(CityService cityService) {
        this.cityService = cityService;
    }

    @Override
    @PreAuthorize("hasAuthority('SCOPE_ROLE_ALLOW_EDIT')")
    public ResponseEntity<City> findCity(Long id) {
        return ResponseEntity.ok(cityService.findById(id));
    }

    @Override
    public ResponseEntity<Page<City>> findAllByName(Filter filter, PageInfo pageInfo) {
        return new ResponseEntity<>(cityService.findByName(filter.getCityName(), pageInfo), HttpStatus.OK);
    }

    @Override
    @PreAuthorize("hasAuthority('SCOPE_ROLE_ALLOW_EDIT')")
    public ResponseEntity<City> updateCity(@RequestBody @Valid City city) {
        return ResponseEntity.ok(cityService.saveCity(city));
    }


}
