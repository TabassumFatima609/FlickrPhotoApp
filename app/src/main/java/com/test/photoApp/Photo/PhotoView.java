package com.test.photoApp.Photo;

import com.test.photoApp.models.PhotoResponse;

public interface PhotoView {
    void showWait();

    void removeWait();

    void onFailure(String appErrorMessage);

    void getPhotoResponse(PhotoResponse photoResponse);

}
