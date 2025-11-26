package com.example.menulistview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class ImageSpinnerAdapter extends BaseAdapter {
    private Context context;
    private int[] images;

    public ImageSpinnerAdapter(Context context, int[] images) {
        this.context = context;
        this.images = images;
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public Object getItem(int position) {
        return images[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            imageView = new ImageView(context);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(100, 100));
        } else {
            imageView = (ImageView) convertView;
        }
        imageView.setImageResource(images[position]);
        return imageView;
    }
}
