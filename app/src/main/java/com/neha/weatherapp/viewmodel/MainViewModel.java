package com.neha.weatherapp.viewmodel;

import com.neha.weatherapp.model.response.CurrentWeatherModel;
import com.neha.weatherapp.model.response.WeatherListModel;
import com.neha.weatherapp.network.RestClient;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

public class MainViewModel {
    public PublishSubject<WeatherListModel> getCurrentWeather() {
        return currentWeather;
    }

    private PublishSubject<WeatherListModel> currentWeather;
    public MainViewModel(){
        currentWeather=PublishSubject.create();
    }
    public void callWeatherApi() {
        RestClient.ApiInterface service = RestClient.getClient();
        Observable<CurrentWeatherModel> observable = service.getCurrentTemp();
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CurrentWeatherModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CurrentWeatherModel productResponseModel) {
                        WeatherListModel weather=new WeatherListModel(productResponseModel.getCurrent());
                        currentWeather.onNext(weather);
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        throwable.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                    }
                });

    }
}
