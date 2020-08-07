package com.androiddesdecero.daggeranexo.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.androiddesdecero.daggeranexo.R;
import com.androiddesdecero.daggeranexo.adapter.TiempoAdapter;
import com.androiddesdecero.daggeranexo.api.ApiClient;
import com.androiddesdecero.daggeranexo.di.BaseApplication;
import com.androiddesdecero.daggeranexo.model.Meteorologia;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SecondActivity extends AppCompatActivity {
    private Button nextActivity;
    private RecyclerView listaTemperatura;

    @Inject
    ApiClient apiClient;

    @Inject
    Context context;


    @Inject
    TiempoAdapter tiempoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        setUpDagger();
        setUpView();
        webService();

    }

    private void webService() {
        Call<Meteorologia> call = apiClient.getTiempo();
        call.enqueue(new Callback<Meteorologia>() {
            @Override
            public void onResponse(Call<Meteorologia> call, Response<Meteorologia> response) {
                Log.d("TAG1", response.body().getList().get(0).getDt());
                tiempoAdapter.setData(response.body().getList());
            }

            @Override
            public void onFailure(Call<Meteorologia> call, Throwable t) {
                Log.d("TAG1", "Error" + t.toString() );
            }
        });
    }

    private void setUpView() {
        nextActivity = findViewById(R.id.nextActivity);
        nextActivity.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, ThirdActivity.class));
            }
        });
        listaTemperatura = findViewById(R.id.rvLista);
        LinearLayoutManager layout = new LinearLayoutManager(this);
        layout.setOrientation(LinearLayoutManager.VERTICAL);
        listaTemperatura.setLayoutManager(layout);
        listaTemperatura.setAdapter(tiempoAdapter);
    }

    private void setUpDagger() {
        ((BaseApplication)getApplication()).getApplicationComponent().inject(this);
    }
}
