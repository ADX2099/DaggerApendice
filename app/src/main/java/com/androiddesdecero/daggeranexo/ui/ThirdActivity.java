package com.androiddesdecero.daggeranexo.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.view.View;
import android.widget.Button;

import com.androiddesdecero.daggeranexo.Constantes;
import com.androiddesdecero.daggeranexo.R;
import com.androiddesdecero.daggeranexo.adapter.TiempoAdapter;
import com.androiddesdecero.daggeranexo.api.ApiClient;
import com.androiddesdecero.daggeranexo.di.BaseApplication;
import com.androiddesdecero.daggeranexo.model.Meteorologia;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ThirdActivity extends AppCompatActivity {

    private Button nextActivity;
    private RecyclerView listaTemperatura;

    @Inject
    ApiClient apiClient;

    @Inject
    Context context;

    @Inject
    TiempoAdapter adapter;

    @Inject
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        setUpDagger();
        setUpView();
        webService();
    }

    private void setUpDagger() {
        ((BaseApplication)getApplication()).plusSharedPreferencesSubComponent().inject(this);
    }

    private void setUpView() {
        nextActivity = findViewById(R.id.nextActivity);
        nextActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, FourthActivity.class));
            }
        });
        listaTemperatura = findViewById(R.id.rvLista);
        LinearLayoutManager lim = new LinearLayoutManager(this);
        lim.setOrientation(LinearLayoutManager.VERTICAL);
        listaTemperatura.setLayoutManager(lim);
        listaTemperatura.setAdapter(adapter);

    }

    private void webService(){
        Call<Meteorologia> call = apiClient.getTiempo();
        call.enqueue(new Callback<Meteorologia>() {
            @Override
            public void onResponse(Call<Meteorologia> call, Response<Meteorologia> response) {
                adapter.setData(response.body().getList());
                SharedPreferences.Editor prefEditor = sharedPreferences.edit();
                prefEditor.putString(Constantes.HUMEDAD,response.body().getList().get(0).getMain().getHumidity());
                prefEditor.putString(Constantes.TEMPERATURA, response.body().getList().get(0).getMain().getTemp());
                prefEditor.apply();
            }

            @Override
            public void onFailure(Call<Meteorologia> call, Throwable t) {

            }
        });

    }


}
