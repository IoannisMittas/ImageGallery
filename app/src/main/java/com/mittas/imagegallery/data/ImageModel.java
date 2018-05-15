package com.mittas.imagegallery.data;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

public class ImageModel {
    private Uri uri;

    public ImageModel(Uri imageUri) {
        this.uri = imageUri;
    }

    public Uri getUri() {
        return uri;
    }
}
