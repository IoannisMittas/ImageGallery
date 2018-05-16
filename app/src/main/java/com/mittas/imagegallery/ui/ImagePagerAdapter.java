package com.mittas.imagegallery.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.mittas.imagegallery.data.ImageModel;

import java.util.List;

public class ImagePagerAdapter extends FragmentPagerAdapter {
    private List<ImageModel> imageList;

    public ImagePagerAdapter(FragmentManager fm, List<ImageModel> imageList) {
        super(fm);
        this.imageList = imageList;
    }

    @Override
    public Fragment getItem(int position) {
        return ImageFragment.newInstance(imageList.get(position).getUri());
    }

    @Override
    public int getCount() {
        return imageList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return imageList.get(position).getUri().toString();
    }

    public void setImages(List<ImageModel> imageList) {
        this.imageList = imageList;
        notifyDataSetChanged();
    }
}
