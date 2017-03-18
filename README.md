# AdvertiseCompent
图片轮播demo


    首先，通过组合控件重写RelativeLayout，在ViewGroup中添加ViewPager和RadioGroup、RadioButton,并且给ViewPager设置适配器、给RadioGroup添加RadioButton；
    接着，通过Handler发送延时消息调用ViewPager的setCurrentItem（）自动轮播；
    然后，通过ViewPager的addOnPageChangeListener()设置用户手动滑动式，自动轮播停止事件；
    最后，通过在ListView中添加addHeaderView（）完成图片自动轮播demo。
    
