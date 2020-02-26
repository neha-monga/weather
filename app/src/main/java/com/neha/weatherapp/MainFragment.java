package com.neha.weatherapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.neha.weatherapp.adapter.ForecastAdapter;
import com.neha.weatherapp.model.response.WeatherListModel;
import com.neha.weatherapp.viewmodel.MainViewModel;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainFragment extends Fragment {
    private MainViewModel mainViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainViewModel = new MainViewModel();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        mainViewModel.callWeatherApi();
        mainViewModel.getCurrentWeather().subscribe(currentWeatherResponse -> {
            showCurrentTempView(currentWeatherResponse);
        });
        return rootView;
    }


    private void showCurrentTempView(WeatherListModel currentTemp) {
        CollapsingToolbarLayout collapsingToolbarLayout = getView().findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle(currentTemp.getCurrent().getTemperature().toString());
        RecyclerView forecastList = getView().findViewById(R.id.rv_forecast);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        forecastList.setLayoutManager(linearLayoutManager);
        ForecastAdapter forecastAdapter = new ForecastAdapter(getActivity(), currentTemp.getForecastList());
        forecastList.setAdapter(forecastAdapter);
    }
}
