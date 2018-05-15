package com.mittas.imagegallery.ui;

import android.Manifest;
import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mittas.imagegallery.R;
import com.mittas.imagegallery.data.ImageModel;
import com.mittas.imagegallery.viewmodel.ImageListViewModel;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import lib.folderpicker.FolderPicker;

public class ImageListFragment extends Fragment {
    public static final String TAG = "ImageListFragment";

    private static final int SDCARD_PERMISSION = 1;
    private static final int FOLDER_PICKER_CODE = 2;

    private ImageListViewModel viewModel;
    private ImageListAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_image_list, container, false);

        setupRecyclerView(rootView);

        FloatingActionButton fab = rootView.findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), FolderPicker.class);
            startActivityForResult(intent, FOLDER_PICKER_CODE);
        });

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        checkStoragePermission();

        viewModel = ViewModelProviders.of(this).get(ImageListViewModel.class);
        subscribeUi();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if (requestCode == FOLDER_PICKER_CODE) {
            if (resultCode == Activity.RESULT_OK && intent.hasExtra("data")) { // 3rd party library specification: selected folder variable name = data
                String folderPath= intent.getExtras().getString("data");
                File imageFolder = new File(folderPath);
                viewModel.onImageFolderSelected(imageFolder);
            } else if (resultCode == Activity.RESULT_CANCELED) {
                // TODO handle cancellation
            }
        }
    }

    private void setupRecyclerView(View view) {
        recyclerView = view.findViewById(R.id.recyclerview);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        adapter = new ImageListAdapter(getActivity(), new ArrayList<ImageModel>());
        recyclerView.setAdapter(adapter);
    }

    private void subscribeUi() {
        // Update the list when the data changes
        viewModel.getAllImages().observe(this, images -> adapter.setImages(images));
    }

    void checkStoragePermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(getActivity(),
                    Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        SDCARD_PERMISSION);
            }
        }
    }
}
