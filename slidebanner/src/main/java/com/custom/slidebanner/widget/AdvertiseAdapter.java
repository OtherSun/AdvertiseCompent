package com.custom.slidebanner.widget;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.custom.slidebanner.R;

/**
 * Created by Administrator on 2017/3/18.
 */

public class AdvertiseAdapter extends BaseAdvertiseAdapter {

    int[] imgRes = {R.drawable.image1, R.drawable.image2, R.drawable.image3, R.drawable.image4, R.drawable.image5};

    @Override
    public int getRealCount() {
        return imgRes.length;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(container.getContext());
        imageView.setImageResource(imgRes[position % imgRes.length]);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
