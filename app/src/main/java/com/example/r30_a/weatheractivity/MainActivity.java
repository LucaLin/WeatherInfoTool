package com.example.r30_a.weatheractivity;

import android.app.ActivityOptions;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.r30_a.weatheractivity.API.API;
import com.example.r30_a.weatheractivity.API.WeatherService;
import com.example.r30_a.weatheractivity.Adapter.MyWeatherAdapter;
import com.example.r30_a.weatheractivity.Interface.onSwipeListener;
import com.example.r30_a.weatheractivity.Utils.ItemTouchHelperCalback;
import com.example.r30_a.weatheractivity.WeatherData.CityWeather;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//參考來源：https://github.com/martinrzg/android-weather-app

public class MainActivity extends AppCompatActivity {

    private List<CityWeather> cityWeatherList;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private LinearLayoutManager linearLayoutManager;
    private SwipeRefreshLayout swipeRefreshLayout;
    private FloatingActionButton fabAddCity;
    WeatherService service;
    Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        initView();
        toast.setText(R.string.startAddCity);toast.show();

        ItemTouchHelper.Callback callback = new ItemTouchHelperCalback((onSwipeListener) adapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

    }

    private void initView() {
        toast = Toast.makeText(this,"",Toast.LENGTH_SHORT);
        cityWeatherList = new ArrayList<>();
        fabAddCity = (FloatingActionButton) findViewById(R.id.fabAddCity);
        recyclerView = (RecyclerView) findViewById(R.id.ry_weatherCards);
        linearLayoutManager = new LinearLayoutManager(this);

        //取得天氣api服務
        service = API.getApi().create(WeatherService.class);
        //綁定清單介面
        adapter = new MyWeatherAdapter(cityWeatherList, R.layout.layout_weather_card, this, new MyWeatherAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(CityWeather cityWeather, int position, View view) {
                //點擊進入細節頁面
                Intent intent = new Intent(MainActivity.this,WeatherDetailActivity.class);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(
                        MainActivity.this,view,
                        "weatherCardTransition");
                intent.putExtra("city",cityWeather);
                startActivity(intent,options.toBundle());

            }
        });

        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                //設定浮動按鈕出現的時機
                if(dy > 0){//往下捲
                    if(fabAddCity.isShown()){
                        fabAddCity.hide();
                    }
                }else if(dy < 0){//往上滑
                    if(!fabAddCity.isShown()){
                        fabAddCity.show();
                    }
                }
            }
        });

        fabAddCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlert();//點擊出現輸入框
            }
        });
        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.refreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshData();
            }
        });
    }

    private void refreshData() {
        for(int i=0; i<cityWeatherList.size();i++){
            updateCity(cityWeatherList.get(i).getCity().getName(),i);
        }
        swipeRefreshLayout.setRefreshing(false);
    }
    //下滑更新
    public void updateCity(String cityName, final int index){
        Call<CityWeather> cityWeather = service.getWeatherCity(cityName, API.KEY, "metric",6);
        cityWeather.enqueue(new Callback<CityWeather>() {
            @Override
            public void onResponse(Call<CityWeather> call, Response<CityWeather> response) {
                if(response.code() == 200){
                    CityWeather cityWeather = response.body();
                    cityWeatherList.remove(index);
                    cityWeatherList.add(index,cityWeather);
                    adapter.notifyItemChanged(index);

                }
            }

            @Override
            public void onFailure(Call<CityWeather> call, Throwable t) {
                toast.setText(R.string.noResponse);toast.show();
            }
        });
    }

    public void showAlert() {
        final View view = LayoutInflater.from(this).inflate(R.layout.layout_dialog, null);
        final EditText editTextAddCityName = (EditText) view.findViewById(R.id.editTextAddCityName);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.addCity);
        builder.setMessage(R.string.addCityInfo);
        builder.setView(view);
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String cityname = editTextAddCityName.getText().toString();
                if (!TextUtils.isEmpty(cityname)) {
                    addCity(cityname);//增加卡片
                }else {
                    toast.setText(R.string.noInput);toast.show();
                }
            }
        });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.create().show();


    }
    //加入氣象卡片
    public void addCity(String cityname) {

        Call<CityWeather> weatherCall = service.getWeatherCity(cityname,API.KEY,"metric",6);

        weatherCall.enqueue(new Callback<CityWeather>() {
            @Override
            public void onResponse(Call<CityWeather> call, Response<CityWeather> response) {
                if(response.code() == 200){//200 == ok
                    CityWeather cityWeather = response.body();
                    cityWeatherList.add(cityWeather);
                    adapter.notifyItemInserted(cityWeatherList.size()-1);
                    recyclerView.scrollToPosition(cityWeatherList.size()-1);
                    toast.setText(R.string.addOK);toast.show();
                }else {
                    toast.setText(R.string.cityNotFound);toast.show();
                }
            }
            @Override
            public void onFailure(Call<CityWeather> call, Throwable t) {
                toast.setText(R.string.noResponse);toast.show();
            }
        });
    }
}
