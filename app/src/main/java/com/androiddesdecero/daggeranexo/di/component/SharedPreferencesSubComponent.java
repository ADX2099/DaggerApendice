package com.androiddesdecero.daggeranexo.di.component;

import com.androiddesdecero.daggeranexo.di.module.SharedPreferencesModule;
import com.androiddesdecero.daggeranexo.di.scope.SharedPreferencesScope;
import com.androiddesdecero.daggeranexo.ui.FourthActivity;
import com.androiddesdecero.daggeranexo.ui.ThirdActivity;

import javax.inject.Scope;

import dagger.Subcomponent;

@SharedPreferencesScope
@Subcomponent(modules = SharedPreferencesModule.class)
public interface SharedPreferencesSubComponent {
    void inject(ThirdActivity thirdActivity);
    void inject(FourthActivity fourthActivity);
}
