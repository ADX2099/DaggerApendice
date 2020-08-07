package com.androiddesdecero.daggeranexo.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.androiddesdecero.daggeranexo.Mensaje;
import com.androiddesdecero.daggeranexo.R;
import com.androiddesdecero.daggeranexo.di.BaseApplication;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {
    private TextView tvTitle;
    private TextView tvDescription;

    @Inject
    Mensaje mensaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpDagger();
        setUpView();

    }

    private void setUpView() {
        tvTitle = findViewById(R.id.tvTitle);
        tvDescription = findViewById(R.id.tvDescription);
        tvTitle.setText(mensaje.getMensajeTitle());
        tvDescription.setText(mensaje.getMensajeDescription());
        tvDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SecondActivity.class));
            }
        });
    }

    private void setUpDagger() {
        ((BaseApplication)getApplication()).getMensajeComponent().inject(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ((BaseApplication)getApplication()).clearMensajeComponent();
    }
}
