package com.custom.slidebanner.widget;

import android.support.v4.view.PagerAdapter;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2017/3/17.
 */

public abstract class BaseAdvertiseAdapter extends PagerAdapter {
    /**
     * 获取实际图片数量
     *
     * @return
     */
    public abstract int getRealCount();

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public abstract Object instantiateItem(ViewGroup container, int position);

    @Override
    public abstract void destroyItem(ViewGroup container, int position, Object object);
}
