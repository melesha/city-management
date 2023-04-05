package com.helmes.citymanagement;

import com.helmes.citymanagement.configuration.RSAProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(RSAProperties.class)
public class CityManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(CityManagementApplication.class, args);
    }

}
