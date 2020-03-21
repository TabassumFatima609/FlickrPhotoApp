package com.test.photoApp.networking;


import com.test.photoApp.models.PhotoResponse;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class Service {
    private final NetworkService networkService;

    public Service(NetworkService networkService) {
        this.networkService = networkService;
    }

    public Subscription getPhotoData(final GetPhotoResponseCallBack callback) {

        return networkService.getPhotos()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends PhotoResponse>>() {
                    @Override
                    public Observable<? extends PhotoResponse> call(Throwable throwable) {
                        return Observable.error(throwable);
                    }
                })
                .subscribe(new Subscriber<PhotoResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(new NetworkError(e));

                    }

                    @Override
                    public void onNext(PhotoResponse photoResponse) {
                        callback.onSuccess(photoResponse);

                    }
                });
    }

    public interface GetPhotoResponseCallBack {
        void onSuccess(PhotoResponse photoResponse);

        void onError(NetworkError networkError);
    }
}
