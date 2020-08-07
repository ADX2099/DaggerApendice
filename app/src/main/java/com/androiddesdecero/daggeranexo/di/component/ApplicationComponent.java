package com.androiddesdecero.daggeranexo.di.component;

import com.androiddesdecero.daggeranexo.adapter.TiempoAdapter;
import com.androiddesdecero.daggeranexo.di.module.AdapterModule;
import com.androiddesdecero.daggeranexo.di.module.ApplicationContextModule;
import com.androiddesdecero.daggeranexo.di.module.RetrofitModule;
import com.androiddesdecero.daggeranexo.di.module.SharedPreferencesModule;
import com.androiddesdecero.daggeranexo.di.scope.ApplicationScope;
import com.androiddesdecero.daggeranexo.ui.SecondActivity;

import dagger.Component;
import retrofit2.Retrofit;

@ApplicationScope
@Component(modules = {ApplicationContextModule.class, RetrofitModule.class, AdapterModule.class})
public interface ApplicationComponent {
    void inject(SecondActivity secondActivity);

    SharedPreferencesSubComponent plusSharedPreferencesSubComponent(SharedPreferencesModule sharedPreferencesModule);
}
