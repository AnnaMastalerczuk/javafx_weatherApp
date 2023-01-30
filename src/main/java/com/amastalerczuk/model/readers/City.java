package com.amastalerczuk.model.readers;

public class City {
    public String cityName;
    public String countryName;
    public Integer cityId;

    public City(String cityName, String countryName, Integer cityId) {
        this.cityName = cityName;
        this.countryName = countryName;
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }
}
