package com.test.photoApp.Photo;

import com.test.photoApp.models.PhotoResponse;
import com.test.photoApp.networking.NetworkError;
import com.test.photoApp.networking.Service;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public class PhotoPresenter {
    private final Service service;
    private final PhotoView view;
    private CompositeSubscription subscriptions;

    public PhotoPresenter(Service service, PhotoView view) {
        this.service = service;
        this.view = view;
        this.subscriptions = new CompositeSubscription();
    }

    public void getPhotoList() {
        view.showWait();

        Subscription subscription = service.getPhotoData(new Service.GetPhotoResponseCallBack() {
            @Override
            public void onSuccess(PhotoResponse photoResponse) {
                view.removeWait();
                view.getPhotoResponse(photoResponse);
            }

            @Override
            public void onError(NetworkError networkError) {
                view.removeWait();
                view.onFailure(networkError.getAppErrorMessage());
            }

        });

        subscriptions.add(subscription);
    }

    public void onStop() {
        subscriptions.unsubscribe();
    }


}
