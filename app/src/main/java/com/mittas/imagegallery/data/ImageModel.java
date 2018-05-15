package com.mittas.imagegallery.data;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

public class ImageModel implements Parcelable{
    public static final String IMAGE_PARCEL = "image_parcel";

    private Uri uri;

    public ImageModel(Uri imageUri) {
        this.uri = imageUri;
    }

    protected ImageModel(Parcel in) {
        uri = in.readParcelable(Uri.class.getClassLoader());
    }

    public Uri getUri() {
        return uri;
    }

    public static final Creator<ImageModel> CREATOR = new Creator<ImageModel>() {
        @Override
        public ImageModel createFromParcel(Parcel in) {
            return new ImageModel(in);
        }

        @Override
        public ImageModel[] newArray(int size) {
            return new ImageModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(uri, flags);
    }
}
