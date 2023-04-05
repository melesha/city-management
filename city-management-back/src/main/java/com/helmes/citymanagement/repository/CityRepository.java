package com.helmes.citymanagement.repository;

import com.helmes.citymanagement.model.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
    Page<City> findByNameContainsIgnoringCase(String name, Pageable pageable);

}
