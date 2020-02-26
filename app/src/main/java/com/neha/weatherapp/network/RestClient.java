package com.neha.weatherapp.network;

import com.neha.weatherapp.BuildConfig;
import com.neha.weatherapp.model.response.CurrentWeatherModel;
import com.neha.weatherapp.util.AppConstant;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

/**
 * A class for network calling
 *
 * @author Neha Monga
 */

public class RestClient {

    private static ApiInterface apiInterface;

    public static ApiInterface getClient() {
        if (null == apiInterface) {
            OkHttpClient.Builder okClient = callHttpBuilder();
            okClient.addInterceptor(chain -> {
                Request.Builder ongoing = chain.request().newBuilder();
                return chain.proceed(ongoing.build());
            });
            apiInterface = callApiClient(AppConstant.BASE_URL, okClient);
            return apiInterface;
        }
        return apiInterface;
    }


    private static ApiInterface callApiClient(String baseUrl, OkHttpClient.Builder okClient) {
        Retrofit client = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okClient.build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        return client.create(ApiInterface.class);
    }

    private static OkHttpClient.Builder callHttpBuilder() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        if (BuildConfig.DEBUG) {
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            logging.setLevel(HttpLoggingInterceptor.Level.NONE);
        }
        OkHttpClient.Builder okClient = new OkHttpClient.Builder();
        okClient.connectTimeout(AppConstant.TIMEOUT, TimeUnit.SECONDS);
        okClient.readTimeout(AppConstant.TIMEOUT, TimeUnit.SECONDS);
        okClient.addInterceptor(logging);
        return okClient;
    }


    public interface ApiInterface {

        // Fetches current weather
        @GET(value = "current?access_key=" + BuildConfig.weatherApiKey + "&query=Bangalore")
        Observable<CurrentWeatherModel> getCurrentTemp();

/*        //Forecasts 7 days weather is paid so I haven't implemented it.
        @GET(value = "forecast?access_key=" + BuildConfig.weatherApiKey + "&query = Bangalore& forecast_days = 7")
        Observable<CurrentWeatherResponse> getCurrentTemp();*/

    }
}
