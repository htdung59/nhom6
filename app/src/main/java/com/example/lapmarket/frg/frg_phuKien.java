package com.example.lapmarket.frg;

import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lapmarket.R;
import com.example.lapmarket.adapter.PhuKienAdapter;
import com.example.lapmarket.adapter.SanphamAdapter;
import com.example.lapmarket.dao.PhuKienDAO;
import com.example.lapmarket.dao.SanPhamDAO;
import com.example.lapmarket.model.phukien;
import com.example.lapmarket.model.sanpham;

import java.util.ArrayList;


public class frg_phuKien extends Fragment {

    PhuKienDAO phuKienDAO;
    ArrayList<phukien> list;
    ArrayList<phukien> list1;

    PhuKienAdapter phuKienAdapter;
    RecyclerView recyclerPhukien;


    public frg_phuKien() {
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
        View view = inflater.inflate(R.layout.fragment_frg_phu_kien, container, false);
        recyclerPhukien = view.findViewById(R.id.recyclerView_phukien);
        SearchView searchView = view.findViewById(R.id.search_sanpham_phukien);

        loadData();


        //tìm kiếm
        list1 = phuKienDAO.selectPHUKIEN();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                list.clear();
                for (phukien pk : list1 ) {
                    if (String.valueOf(pk.getTenpk()).contains(newText) || String.valueOf(pk.getGia()).contains(newText) ){
                        list.add(pk);
                    }
                    phuKienAdapter.notifyDataSetChanged();
                }
                return false;
            }
        });
        return  view;
    }



    public void loadData(){
        phuKienDAO = new PhuKienDAO(getContext());
        list = phuKienDAO.selectPHUKIEN();

        GridLayoutManager gridLayoutManager = new GridLayoutManager(requireActivity(),2);
        recyclerPhukien.setLayoutManager(gridLayoutManager);
        phuKienAdapter = new PhuKienAdapter(getContext(), list, phuKienDAO);
        recyclerPhukien.setAdapter(phuKienAdapter);
    }
}