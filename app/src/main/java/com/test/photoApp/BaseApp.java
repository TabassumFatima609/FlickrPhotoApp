package com.test.photoApp;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.test.photoApp.deps.DaggerDeps;
import com.test.photoApp.deps.Deps;
import com.test.photoApp.networking.NetworkModule;

import java.io.File;

public class BaseApp extends AppCompatActivity {
    Deps deps;
    Context context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        File cacheFile = new File(getCacheDir(), "responses");
        context = this;
        deps = DaggerDeps.builder().networkModule(new NetworkModule(cacheFile, context)).build();

    }


    public Toolbar getToolBar(String intentValue, int backVisibility, int searchVisibility, boolean isBackClick) {

        Toolbar mToolbar = findViewById(R.id.tool_bar);

        setSupportActionBar(mToolbar);
        setSupportActionBar(mToolbar);

        ((TextView) findViewById(R.id.tv_title)).setText(intentValue);
        return mToolbar;
    }

    public Deps getDeps() {
        return deps;
    }
}
