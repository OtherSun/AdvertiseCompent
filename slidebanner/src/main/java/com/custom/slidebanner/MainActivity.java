package com.custom.slidebanner;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.custom.slidebanner.widget.AdvertiseAdapter;
import com.custom.slidebanner.widget.AdvertisementLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private AdvertisementLayout mBanner;
    private AdvertiseAdapter mAdvertiseAdapter;
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();
    }

    private void initView() {
        mListView = (ListView) findViewById(R.id.listview);
        ArrayList<String> data = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            data.add("数据" + i);
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
//      获取屏幕宽高
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int bannerHeightPix = (int) (displayMetrics.widthPixels * 0.618f);
        AbsListView.LayoutParams layoutParams = new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, bannerHeightPix);
        mBanner = new AdvertisementLayout(this);
        mBanner.setLayoutParams(layoutParams);

        mAdvertiseAdapter = new AdvertiseAdapter();
        mBanner.setAdapter(mAdvertiseAdapter);
        mBanner.setRadioButtonDrawable(R.drawable.radio_btn_point);
        mBanner.startAutoSlideBanner();
        mListView.addHeaderView(mBanner);
        mListView.setAdapter(arrayAdapter);
    }

    private void initData() {

    }
}
