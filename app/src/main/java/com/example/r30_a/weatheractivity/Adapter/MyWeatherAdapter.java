package com.example.r30_a.weatheractivity.Adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.r30_a.weatheractivity.Interface.onSwipeListener;
import com.example.r30_a.weatheractivity.R;
import com.example.r30_a.weatheractivity.Utils.IconProvider;
import com.example.r30_a.weatheractivity.WeatherData.CityWeather;

import java.util.List;

public class MyWeatherAdapter extends RecyclerView.Adapter<MyWeatherAdapter.MyViewHolder> implements onSwipeListener {

    private List<CityWeather> cityWeatherList;
    private int layoutReference;
    private Activity activity;
    private View parentView;
    private OnItemClickListener onItemClickListener;

    public MyWeatherAdapter(List<CityWeather> cityWeatherList, int layoutReference, Activity activity, OnItemClickListener onItemClickListener) {
        this.cityWeatherList = cityWeatherList;
        this.layoutReference = layoutReference;
        this.activity = activity;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public MyWeatherAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        parentView = viewGroup;
        View view = LayoutInflater.from(activity).inflate(layoutReference, viewGroup, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int pos) {
        myViewHolder.bindView(cityWeatherList.get(pos),onItemClickListener);
    }

    @Override
    public int getItemCount() {
        return cityWeatherList.size();
    }

    public void addItem(int position, CityWeather cityWeather){
        cityWeatherList.add(cityWeather);
        notifyItemInserted(position);
    }

    @Override//使用自訂的onSwipeListener通知滑動的該卡片做刪除動作
    public void onItemDelete(final int position) {
        final CityWeather cityWeather = cityWeatherList.get(position);
        cityWeatherList.remove(cityWeather);
        notifyItemRemoved(position);

        //刪除成功後底下跳出提示欄，可按復原取消操作
        Snackbar.make(parentView,R.string.removeOK,Snackbar.LENGTH_LONG)
                .setAction(R.string.undo, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        addItem(position,cityWeather);
                    }
                }).show();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        TextView txvCard_cityName;
        TextView txvCard_description;
        TextView txvCard_currentTemp;
        TextView txvCard_maxTemp;
        TextView txvCard_minTemp;
        ImageView img_weather;

        public MyViewHolder(@NonNull View v) {
            super(v);

            cardView = (CardView) v.findViewById(R.id.cardview);
            txvCard_cityName = (TextView) v.findViewById(R.id.txvCard_cityname);
            txvCard_description = (TextView) v.findViewById(R.id.txvCard_description);
            txvCard_currentTemp = (TextView) v.findViewById(R.id.txvCard_currentTemp);
            txvCard_maxTemp = (TextView) v.findViewById(R.id.txvCard_maxTemp);
            txvCard_minTemp = (TextView) v.findViewById(R.id.txvCard_minTemp);
            img_weather = (ImageView) v.findViewById(R.id.img_weather);
        }

        public void bindView(final CityWeather cityWeather, final OnItemClickListener onItemClickListener) {
            txvCard_cityName.setText(cityWeather.getCity().getName()+", "+ cityWeather.getCity().getCountry());
            txvCard_description.setText(cityWeather.getWeatherData().get(0).getWeatherDetails().get(0).getLongDescription());
            txvCard_currentTemp.setText((int)cityWeather.getWeatherData().get(0).getTemp().getDay()+"°c");
            txvCard_maxTemp.setText((int)cityWeather.getWeatherData().get(0).getTemp().getMax()+"°c");
            txvCard_minTemp.setText((int)cityWeather.getWeatherData().get(0).getTemp().getMin()+"°c");
            String weatherStatus = cityWeather.getWeatherData().get(0).getWeatherDetails().get(0).getShortDescription();
            img_weather.setBackgroundResource(IconProvider.getImageIcon(weatherStatus));

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(cityWeather,getAdapterPosition(),cardView);
                }
            });
        }

    }

    public interface OnItemClickListener {
        void onItemClick(CityWeather cityWeather, int position, View view);
    }
}
