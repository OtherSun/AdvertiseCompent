package com.custom.slidebanner.widget;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

/**
 * Created by Administrator on 2017/3/17.
 */

public class AdvertisementLayout extends RelativeLayout implements ViewPager.OnPageChangeListener {

    private static final int SLIDE_VIEWPAGER = 111;
    public int delay_millis = 3000;
    private ViewPager mViewPager;
    private RadioGroup mRadioGroup;
    private BaseAdvertiseAdapter mAdapter;
    private Handler mHandler = new Handler() {
        private int currentItemPosition;

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SLIDE_VIEWPAGER:
                    currentItemPosition = mViewPager.getCurrentItem();
                    mViewPager.setCurrentItem(++currentItemPosition);
                    mHandler.sendEmptyMessageDelayed(SLIDE_VIEWPAGER, delay_millis);
                    break;
                default:
                    break;
            }
        }
    };
    private boolean isUser;
    private boolean isAutoPlay;

    /**
     * 设置自动图片轮播时间
     *
     * @param delay_millis 图片滑动间隔时间
     */
    public void setDelay_millis(int delay_millis) {
        this.delay_millis = delay_millis;
    }

    public AdvertisementLayout(Context context) {
        this(context, null);
    }

    public AdvertisementLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context) {
        mViewPager = new ViewPager(context);
        mRadioGroup = new RadioGroup(context);
        mRadioGroup.setOrientation(RadioGroup.HORIZONTAL);

        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        mViewPager.setLayoutParams(layoutParams);

        layoutParams = new LayoutParams(RadioGroup.LayoutParams.WRAP_CONTENT, RadioGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        layoutParams.bottomMargin = 20;
        mRadioGroup.setLayoutParams(layoutParams);


        this.addView(mViewPager);
        this.addView(mRadioGroup);
    }

    public void setAdapter(BaseAdvertiseAdapter adapter) {
        if (adapter == null)
            return;
        mAdapter = adapter;

        initRadioButton();

        mViewPager.setAdapter(mAdapter);
        initFirstPager();

        mViewPager.addOnPageChangeListener(this);
    }

    /**
     * 初始化viewPager显示第一页
     */
    private void initFirstPager() {
        int showPagerPosition = mAdapter.getCount() >> 1;
        showPagerPosition = showPagerPosition - (showPagerPosition % mAdapter.getRealCount());
        mViewPager.setCurrentItem(showPagerPosition);
    }


    /**
     * 设置RadioButton的样式图片
     *
     * @param res 图片资源
     */
    public void setRadioButtonDrawable(int res) {

        for (int i = 0; i < mAdapter.getRealCount(); i++) {
            RadioButton radioButton = (RadioButton) mRadioGroup.getChildAt(i);
            radioButton.setButtonDrawable(res);
        }
    }

    /**
     * 初始化RadioGroup的RadioButton数量
     */
    private void initRadioButton() {
        for (int i = 0; i < mAdapter.getRealCount(); i++) {
            RadioButton radioButton = new RadioButton(getContext());
            radioButton.setButtonDrawable(null);
            RadioGroup.LayoutParams layoutParams = new RadioGroup.LayoutParams(RadioGroup.LayoutParams.WRAP_CONTENT, RadioGroup.LayoutParams.WRAP_CONTENT);
            radioButton.setPadding(5, 5, 5, 5);
            radioButton.setLayoutParams(layoutParams);

            mRadioGroup.addView(radioButton);
            if (i == 0) {
                radioButton.setChecked(true);
            }
            radioButton.setEnabled(false);

        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        int radioBtnPosition = position % mAdapter.getRealCount();
        RadioButton radioButton = (RadioButton) mRadioGroup.getChildAt(radioBtnPosition);
        radioButton.setChecked(true);

    }

    @Override
    public void onPageScrollStateChanged(int state) {
        switch (state) {
            case ViewPager.SCROLL_STATE_DRAGGING:
                if (!isAutoPlay)
                    return;
                mHandler.removeMessages(SLIDE_VIEWPAGER);
                isUser = true;
                break;
            case ViewPager.SCROLL_STATE_IDLE:
                if (isUser && isAutoPlay) {
                    mHandler.sendEmptyMessageDelayed(SLIDE_VIEWPAGER, delay_millis);
                    isUser = false;
                }
                break;
            case ViewPager.SCROLL_STATE_SETTLING:
                break;
            default:
                break;
        }
    }

    /**
     * 开始轮播
     */
    public void startAutoSlideBanner() {
        mHandler.sendEmptyMessageDelayed(SLIDE_VIEWPAGER, delay_millis);
        isAutoPlay = true;
    }

    /**
     * 结束轮播
     */
    public void stopAutoSlideBanner() {
        mHandler.removeMessages(SLIDE_VIEWPAGER);
        isAutoPlay = false;
    }

}
