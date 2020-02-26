package com.neha.weatherapp.model.response;

import java.util.ArrayList;
import java.util.List;

public class WeatherListModel {
    public WeatherListModel(Current current) {
        this.current = current;
        forecastList = new ArrayList<>(5);
        for (int i = 0; i < 5; i++) {
            forecastList.add(current);
        }
    }

    private Current current;

    private List<Current> forecastList;

    public Current getCurrent() {
        return current;
    }

    public List<Current> getForecastList() {
        return forecastList;
    }

}
