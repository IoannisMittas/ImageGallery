package com.mittas.imagegallery.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mittas.imagegallery.R;

public class ImageListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_list);

        if (savedInstanceState == null) {
           ImageListFragment fragment = new ImageListFragment();

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, fragment, ImageListFragment.TAG).commit();
        }
    }
}
