package com.example.lapmarket.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.lapmarket.R;
import com.example.lapmarket.model.photo;

import java.util.List;
import android.os.Handler;

public class PhotoAdapter extends PagerAdapter {
    private Context context;
    private List<photo> mListPhoto;
    private ViewPager viewPager;
    private Handler handler = new Handler();
    private Runnable autoScrollRunnable;


    public PhotoAdapter(Context context, List<photo> mListPhoto, ViewPager viewPager) {
        this.context = context;
        this.mListPhoto = mListPhoto;
        this.viewPager = viewPager;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.item_photo, container, false);

        ImageView img_photo = view.findViewById(R.id.img_photo);

        photo phoTo = mListPhoto.get(position);
        if (phoTo != null){
            Glide.with(context).load(phoTo.getPhotoID()).into(img_photo);

        }
        container.addView(view);


//        startAutoScroll();
//        stopAutoScroll();

        return view;
    }

    @Override
    public int getCount() {

        if (mListPhoto != null){
            return mListPhoto.size();
        }
        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object) ;
    }

    public void startAutoScroll() {
        autoScrollRunnable = new Runnable() {
            @Override
            public void run() {
                int currentItem = viewPager.getCurrentItem();

                int nextItem = (currentItem + 1) % getCount();

                viewPager.setCurrentItem(nextItem, true);
                handler.postDelayed(this, 4000); // Tự động cuộn sau mỗi 3 giây
            }
        };

        handler.postDelayed(autoScrollRunnable, 4000); // Bắt đầu tự động cuộn sau khi khởi tạo
    }

    public void stopAutoScroll() {
        handler.removeCallbacks(autoScrollRunnable);
    }
}
