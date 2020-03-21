package com.test.photoApp.deps;


import com.test.photoApp.Photo.PhotoActivity;
import com.test.photoApp.networking.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {NetworkModule.class,})
public interface Deps {
    void inject(PhotoActivity photoActivity);
}
