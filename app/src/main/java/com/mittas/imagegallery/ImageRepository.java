package com.mittas.imagegallery;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;
import android.net.Uri;

import com.mittas.imagegallery.data.ImageModel;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

/**
 * Repository handling the work with images.
 */
public class ImageRepository {
    private static ImageRepository INSTANCE;
    private final AppExecutors executors;
    private MediatorLiveData<List<ImageModel>> observableImages;
    private MutableLiveData<List<ImageModel>> repositoryImages;

    private ImageRepository(final AppExecutors executors) {
        this.executors = executors;

        repositoryImages = new MutableLiveData<>();

        observableImages = new MediatorLiveData<>();
        observableImages.addSource(this.repositoryImages,
                images -> observableImages.postValue(images));
    }

    public static ImageRepository getInstance(final AppExecutors executors) {
        if (INSTANCE == null) {
            INSTANCE = new ImageRepository(executors);
        }
        return INSTANCE;
    }

    public LiveData<List<ImageModel>> getAllImages() {
        return observableImages;
    }

    public void onImageFolderSelected(File imageFolder) {
        File[] allImageFiles = imageFolder.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return (name.endsWith(".jpg") || name.endsWith(".jpeg") || name.endsWith(".png"));
            }
        });

        ArrayList<ImageModel> imageList = new ArrayList<>();
        for (File imageFile: allImageFiles) {
            Uri imageUri = Uri.fromFile(imageFile);
            imageList.add(new ImageModel(imageUri));
        }

        addImages(imageList);
    }

    private void addImages(final List<ImageModel> images) {
        if(images != null) {
            executors.diskIO().execute(() -> setRepositoryImages(images));
        }
    }

    /**
     * Because of the nature of the app, we don't save the images in a database. We
     * just set the image list that belongs to the repository, so the image data
     * will be retained as long as the repository is alive.
     */
    private void setRepositoryImages(final List<ImageModel> images) {
        repositoryImages.postValue(images);
    }
}
