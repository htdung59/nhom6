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
import com.example.lapmarket.adapter.SanphamAdapter;
import com.example.lapmarket.dao.SanPhamDAO;
import com.example.lapmarket.model.sanpham;

import java.util.ArrayList;


public class frg_gaming_doHoa extends Fragment {
    SanPhamDAO sanPhamDAO;
    ArrayList<sanpham> list;
    ArrayList<sanpham> list1;

    SanphamAdapter sanphamAdapter;
    RecyclerView recyclerSanpham;



    public frg_gaming_doHoa() {
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
        View view = inflater.inflate(R.layout.fragment_frg_gaming_do_hoa, container, false);

        recyclerSanpham = view.findViewById(R.id.recyclerView_gaming);
        SearchView searchView = view.findViewById(R.id.search_sanpham_gaming);
        loadData();

        //tìm kiếm
        list1 = sanPhamDAO.selectGAMING();
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

        sanphamAdapter = new SanphamAdapter(getContext(), list, sanPhamDAO, new SanphamAdapter.OnAddToCartClickListener() {
            @Override
            public void onAddToCartClick(sanpham sanPham) {
                onAddToCartClick(sanPham);
            }
        });


        return view;
    }

    public void loadData(){
        sanPhamDAO = new SanPhamDAO(getContext());
        list = sanPhamDAO.selectGAMING();

        GridLayoutManager gridLayoutManager = new GridLayoutManager(requireActivity(),2);
        recyclerSanpham.setLayoutManager(gridLayoutManager);

        sanphamAdapter = new SanphamAdapter(getContext(), list, sanPhamDAO, null);
        recyclerSanpham.setAdapter(sanphamAdapter);
    }

}