package com.test.photoApp.networking;


import com.test.photoApp.models.PhotoResponse;

import retrofit2.http.GET;
import rx.Observable;

public interface NetworkService {


    @GET("api/?key=15662093-b434b75072420c84b8dd60692&q=yellow+flowers&image_type=photo")
    Observable<PhotoResponse> getPhotos(/*@Query("key") int key,
                                        @Query("q") String flowerName,
                                        @Query("image_type") String imageType*/);
}
