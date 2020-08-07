package com.androiddesdecero.daggeranexo.di;

import android.app.Application;


import com.androiddesdecero.daggeranexo.di.component.ApplicationComponent;
import com.androiddesdecero.daggeranexo.di.component.DaggerApplicationComponent;
import com.androiddesdecero.daggeranexo.di.component.DaggerMensajeComponent;
import com.androiddesdecero.daggeranexo.di.component.MensajeComponent;
import com.androiddesdecero.daggeranexo.di.component.SharedPreferencesSubComponent;
import com.androiddesdecero.daggeranexo.di.module.ApplicationContextModule;
import com.androiddesdecero.daggeranexo.di.module.MensajeModule;
import com.androiddesdecero.daggeranexo.di.module.SharedPreferencesModule;

/**
 * Created by albertopalomarrobledo on 11/3/19.
 */

public class BaseApplication extends Application {

   private MensajeComponent mensajeComponent;
   private ApplicationComponent applicationComponent;
   private SharedPreferencesSubComponent sharedPreferencesSubComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        setUpGraphApplicationComponent();
    }

    public MensajeComponent getMensajeComponent(){
        if(mensajeComponent == null){
            setUpGraphMensajeComponent();
        }
        return mensajeComponent;
    }



    private void setUpGraphApplicationComponent() {
        applicationComponent = DaggerApplicationComponent
                .builder()
                .applicationContextModule(new ApplicationContextModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent(){
        if(applicationComponent == null){
            setUpGraphApplicationComponent();
        }
        return applicationComponent;
    }

    public void clearApplicationComponent(){
        applicationComponent = null;
    }
    public void clearMensajeComponent(){
        mensajeComponent = null;
    }


    private void setUpGraphMensajeComponent(){
        mensajeComponent = DaggerMensajeComponent
                .builder()
                .mensajeModule(new MensajeModule())
                .build();
    }

    public SharedPreferencesSubComponent plusSharedPreferencesSubComponent(){
        if(sharedPreferencesSubComponent == null){
            sharedPreferencesSubComponent = applicationComponent
                    .plusSharedPreferencesSubComponent(new SharedPreferencesModule());
        }
        return sharedPreferencesSubComponent;
    }

}
