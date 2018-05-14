package com.mittas.imagegallery.data;

import android.net.Uri;

public class ImageModel {
    private Uri imageUri;

    public ImageModel(Uri imageUri) {
        this.imageUri = imageUri;
    }

    public Uri getImageUri() {
        return imageUri;
    }
}
