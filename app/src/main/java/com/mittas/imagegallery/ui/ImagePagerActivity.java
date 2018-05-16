package com.mittas.imagegallery.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.view.ViewPager;
import android.os.Bundle;

import com.mittas.imagegallery.R;
import com.mittas.imagegallery.viewmodel.ImagePagerViewModel;

import java.util.ArrayList;

public class ImagePagerActivity extends AppCompatActivity {
    private ImagePagerViewModel viewModel;
    private ImagePagerAdapter adapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager viewPager;

    // TODO fix activity_image_pager
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_pager);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setUpPager(savedInstanceState);

        viewModel = ViewModelProviders.of(this).get(ImagePagerViewModel.class);
        subscribeUi();
    }

    private void subscribeUi() {
        viewModel.getAllImages().observe(this, images -> adapter.setImages(images));
    }

    private void setUpPager(Bundle savedInstanceState) {
        adapter = new ImagePagerAdapter(getSupportFragmentManager(), new ArrayList<>());

        viewPager = findViewById(R.id.viewpager);
        viewPager.setAdapter(adapter);

        // User clicked image to be opened
        if (savedInstanceState == null) {
            int currentImagePosition = getIntent().getIntExtra((ImageListFragment.IMAGE_POSITION), 0);
            // if we doesn't call viewPager with a Handler, it doesn't work
            new Handler().post(() -> viewPager.setCurrentItem(currentImagePosition));
        }

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                //noinspection ConstantConditions
                setTitle(adapter.getPageTitle(position));
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }
}
