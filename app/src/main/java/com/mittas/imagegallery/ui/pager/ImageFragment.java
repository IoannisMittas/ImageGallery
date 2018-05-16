package com.mittas.imagegallery.ui.pager;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.mittas.imagegallery.R;

public class ImageFragment extends Fragment {
        private static final String ARG_IMAGE_URI = "image_uri";
        private Uri imageUri;

        @Override
        public void setArguments(Bundle args) {
            super.setArguments(args);
            this.imageUri = args.getParcelable(ARG_IMAGE_URI);
        }

        public static ImageFragment newInstance(Uri imageUri) {
            ImageFragment fragment = new ImageFragment();
            Bundle args = new Bundle();
            args.putParcelable(ARG_IMAGE_URI, imageUri);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_image, container, false);

            final ImageView imageView = rootView.findViewById(R.id.imageview);

            if (imageUri != null) {
                Glide.with(getActivity()).load(imageUri).thumbnail(0.1f).into(imageView);
            }

            return rootView;
        }
    }