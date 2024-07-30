package com.example.lapmarket.frg;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lapmarket.R;

import com.example.lapmarket.adapter.PhotoAdapter;
import com.example.lapmarket.adapter.SanphamAdapter;
import com.example.lapmarket.dao.GioHangDAO;
import com.example.lapmarket.dao.SanPhamDAO;
import com.example.lapmarket.model.photo;
import com.example.lapmarket.model.sanpham;

import android.view.View;

import java.util.ArrayList;
import java.util.List;


import me.relex.circleindicator.CircleIndicator;


public class frg_trangchu extends Fragment {

    private Handler handler;
    private Runnable runnable;

    private ViewPager viewPager;
    private CircleIndicator circleIndicator;
    private PhotoAdapter photoAdapter;

    SanPhamDAO sanPhamDAO;
    ArrayList<sanpham> list;
    ArrayList<sanpham> list1;

    SanphamAdapter sanphamAdapter;
    RecyclerView recyclerSanpham;



    public frg_trangchu() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_frg_trangchu, container, false);

        recyclerSanpham = view.findViewById(R.id.recyclerView_Sanpham);
        SearchView searchView = view.findViewById(R.id.search_sanpham);

        sanphamAdapter = new SanphamAdapter(getContext(), list, sanPhamDAO, new SanphamAdapter.OnAddToCartClickListener() {
            @Override
            public void onAddToCartClick(sanpham sanPham) {
                addToCart(sanPham);
            }
        });

        viewPager = view.findViewById(R.id.viewPager);
        circleIndicator = view.findViewById(R.id.circleindicator);
        photoAdapter = new PhotoAdapter(getContext(), getListPhoto(), viewPager);
        viewPager.setAdapter(photoAdapter);
        circleIndicator.setViewPager(viewPager);
        photoAdapter.registerDataSetObserver(circleIndicator.getDataSetObserver());


        //
        slideshow();

        loadData();

        sanphamAdapter = new SanphamAdapter(getContext(), list, sanPhamDAO, new SanphamAdapter.OnAddToCartClickListener() {
            @Override
            public void onAddToCartClick(sanpham sanPham) {
                addToCart(sanPham);
            }
        });



        //tìm kiếm
        list1 = sanPhamDAO.selectSANPHAM();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                list.clear();
                for (sanpham tv : list1 ) {
                    if (String.valueOf(tv.getTensp()).contains(newText) || String.valueOf(tv.getGia()).contains(newText) ){
                        list.add(tv);
                    }
                    sanphamAdapter.notifyDataSetChanged();
                }
                return false;
            }
        });




        return view;
    }

    private void addToCart(sanpham sanPham) {
        GioHangDAO gioHangDAO = new GioHangDAO(getContext());
        gioHangDAO.addToCart(sanPham);

        // Cập nhật dữ liệu trên RecyclerView của frg_trangchu
        list = sanPhamDAO.selectSANPHAM(); // Lấy danh sách sản phẩm mới
        sanphamAdapter.updateData(list);
    }



    private List<photo> getListPhoto(){
        List<photo> list = new ArrayList<>();
        list.add(new photo(R.drawable.laptop0));
        list.add(new photo(R.drawable.laptop1));
        list.add(new photo(R.drawable.laptop2));
        list.add(new photo(R.drawable.laptop3));

        return list;
    }

    public void slideshow(){
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                int currentItem = viewPager.getCurrentItem();
                int nextItem = (currentItem + 1) % photoAdapter.getCount();
                viewPager.setCurrentItem(nextItem, true);
                handler.postDelayed(this, 3000);
            }
        };
        handler.postDelayed(runnable, 3000);
    }



    public void loadData(){
        sanPhamDAO = new SanPhamDAO(getContext());
        list = sanPhamDAO.selectSANPHAM();

        GridLayoutManager gridLayoutManager = new GridLayoutManager(requireActivity(),2);
        recyclerSanpham.setLayoutManager(gridLayoutManager);

        sanphamAdapter = new SanphamAdapter(getContext(), list, sanPhamDAO, new SanphamAdapter.OnAddToCartClickListener() {
            @Override
            public void onAddToCartClick(sanpham sanPham) {
                addToCart(sanPham);
            }
        });

        recyclerSanpham.setAdapter(sanphamAdapter);
    }

    @Override
    public void onDestroyView() {
        // Dừng tự động cuộn khi Fragment bị hủy
        if (handler != null && runnable != null) {
            handler.removeCallbacks(runnable);
        }
        super.onDestroyView();
    }
}