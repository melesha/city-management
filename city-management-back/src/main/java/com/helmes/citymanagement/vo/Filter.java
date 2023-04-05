package com.helmes.citymanagement.vo;

public class Filter {
    private String cityName;

    public Filter(String cityName) {
        this.cityName = cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityName() {
        return cityName;
    }
}
