package com.mittas.imagegallery.data;

import android.net.Uri;

public class ImageModel {
    private Uri uri;

    public ImageModel(Uri imageUri) {
        this.uri = imageUri;
    }

    public Uri getUri() {
        return uri;
    }
}
