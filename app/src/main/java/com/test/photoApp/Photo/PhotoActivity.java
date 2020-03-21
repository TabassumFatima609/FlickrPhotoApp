package com.test.photoApp.Photo;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.test.photoApp.BaseApp;
import com.test.photoApp.R;
import com.test.photoApp.models.HitsItem;
import com.test.photoApp.models.PhotoResponse;
import com.test.photoApp.networking.Service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class PhotoActivity extends BaseApp implements PhotoView {

    private RecyclerView list;
    @Inject
    public Service service;
    ProgressBar progressBar;
    PhotoPresenter presenter;
    List<PhotoResponse> photoResponse = new ArrayList<>();
    int listSize, currentSize = 100;
    List<HitsItem> hitsItems;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getDeps().inject(this);

        renderView();
        init();

        presenter = new PhotoPresenter(service, this);
        presenter.getPhotoList();
    }

    public void renderView() {
        setContentView(R.layout.activity_home);
        getToolBar(getString(R.string.favorites), View.VISIBLE, View.VISIBLE, false);

        list = findViewById(R.id.list);
        progressBar = findViewById(R.id.progress);
    }

    public void init() {
        list.setLayoutManager(new GridLayoutManager(this, 3));
    }

    @Override
    public void showWait() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void removeWait() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onFailure(String appErrorMessage) {
    }

    @Override
    public void getPhotoResponse(PhotoResponse photoResponse) {
                PhotoAdapter adapter = new PhotoAdapter(getApplicationContext(), photoResponse.getHits());

        list.setAdapter(adapter);
    }



}
