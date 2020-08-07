package com.androiddesdecero.daggeranexo.ui;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.androiddesdecero.daggeranexo.Constantes;
import com.androiddesdecero.daggeranexo.R;
import com.androiddesdecero.daggeranexo.di.BaseApplication;

import javax.inject.Inject;

public class FourthActivity extends AppCompatActivity {

    @Inject
    SharedPreferences sharedPreferences;

    private TextView tvTemperatura;
    private TextView tvHumedad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);
        setUpDagger();
        setUpView();
        read();
    }

    private void read() {
        tvTemperatura.setText(sharedPreferences.getString(Constantes.TEMPERATURA,""));
        tvHumedad.setText(sharedPreferences.getString(Constantes.HUMEDAD,""));
    }

    private void setUpView() {
        tvTemperatura = findViewById(R.id.tvTemperatura);
        tvHumedad = findViewById(R.id.tvHumedad);

    }

    private void setUpDagger() {
        ((BaseApplication)getApplication()).plusSharedPreferencesSubComponent().inject(this);
    }
}
