package com.mittas.imagegallery.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.support.annotation.NonNull;

import com.mittas.imagegallery.BasicApp;
import com.mittas.imagegallery.data.ImageModel;

import java.util.List;

public class ImageListViewModel extends AndroidViewModel{

    private final MediatorLiveData<List<ImageModel>> observableImages;

    public ImageListViewModel(@NonNull Application application) {
        super(application);

        observableImages = new MediatorLiveData<>();

        LiveData<List<ImageModel>> images = ((BasicApp) application).getRepository()
                .getAllImages();

        // observe the changes of the images from the database and forward them
        observableImages.addSource(images, observableImages::setValue);
    }

    /**
     * Expose the LiveData images query so the UI can observe it.
     */
    public LiveData<List<ImageModel>> getAllImages() {
        return observableImages;
    }
}
