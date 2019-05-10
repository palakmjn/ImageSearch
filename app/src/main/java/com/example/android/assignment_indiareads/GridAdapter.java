package com.example.android.assignment_indiareads;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by User on 5/6/2019.
 */

public class GridAdapter extends ArrayAdapter<Image> {
    public GridAdapter(Context context, ArrayList<Image> images) {
        super(context, R.layout.image_item, images);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Image image = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.image_item, parent, false);
        }
        ImageView iv = (ImageView) convertView.findViewById(R.id.imageId);
        iv.setImageResource(0);
        Picasso.with(getContext()).load(image.getImageUrl()).into(iv);
        return convertView;

    }

}
