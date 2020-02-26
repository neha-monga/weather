package com.neha.weatherapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.neha.weatherapp.R;
import com.neha.weatherapp.model.response.Current;
import com.neha.weatherapp.util.Util;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ForecastViewHolder> {


    private Context context;
    private List<Current> forecastList;


    public ForecastAdapter(Context context, List<Current> forecastList) {
        this.context = context;
        this.forecastList = forecastList;


    }


    @NonNull
    @Override
    public ForecastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_weather_forecast, parent, false);
        return new ForecastViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ForecastViewHolder holder, int position) {
        holder.temperature.setText(context.getString(R.string.today) + " " + forecastList.get(position).getTemperature().toString());
        Util.loadImage(context,forecastList.get(position).getWeatherIcons().get(0),holder.climate);
    }

    @Override
    public int getItemCount() {
        return forecastList.size();
    }

    class ForecastViewHolder extends RecyclerView.ViewHolder {

        TextView temperature;
        AppCompatImageView climate;


        ForecastViewHolder(View itemView) {
            super(itemView);

            temperature = itemView.findViewById(R.id.tv_temperature);
            climate = itemView.findViewById(R.id.iv_climate);


        }

    }


}
