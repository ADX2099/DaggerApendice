package com.androiddesdecero.daggeranexo.di.module;

import com.androiddesdecero.daggeranexo.adapter.TiempoAdapter;

import dagger.Module;
import dagger.Provides;

@Module
public class AdapterModule {
    @Provides
    public TiempoAdapter getTiempoAdapter(){
        return new TiempoAdapter();
    }
}
