package com.example.naseem.restaurent;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


/**
 * Created by Naseem on 01-02-2018.
 */

public class MyCustomPagerAdapter extends PagerAdapter {


    private static int currentPage = 0;
    private static int NUM_PAGES = 0;

    private Context context;
    private Integer [] images={R.drawable.img1, R.drawable.img2, R.drawable.img3, R.drawable.img4};
    private LayoutInflater layoutInflater;
    public MyCustomPagerAdapter(Context context) {
        this.context = context;

    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view ==  object;
    }

    @Override
    public Object instantiateItem(ViewGroup container,  int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.item, null);

        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        imageView.setImageResource(images[position]);


        ViewPager vp=(ViewPager) container;
        vp.addView(view,0);
        return view;


    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ViewPager vp=(ViewPager) container;
        View view=(View) object;
        vp.removeView(view);
    }


}