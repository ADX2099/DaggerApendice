package com.androiddesdecero.daggeranexo.di.module;

import android.content.Context;
import android.content.SharedPreferences;

import com.androiddesdecero.daggeranexo.di.scope.SharedPreferencesScope;

import dagger.Module;
import dagger.Provides;

@Module
public class SharedPreferencesModule {
    public static final String SHARED_PREFERENCES =  "shares_pref";

    @SharedPreferencesScope
    @Provides
    SharedPreferences provideSharedPreferences(Context context){
        return context.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
    }
}
