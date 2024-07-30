package com.example.lapmarket.frg;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lapmarket.R;
import com.example.lapmarket.adapter.PhuKienAdapter;
import com.example.lapmarket.adapter.QuanLyPhuKienAdapter;
import com.example.lapmarket.dao.PhuKienDAO;
import com.example.lapmarket.model.phukien;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class frg_quanly_phukien extends Fragment {


    PhuKienDAO phuKienDAO;
    ArrayList<phukien> list;
    ArrayList<phukien> list1;

    QuanLyPhuKienAdapter quanLyPhuKienAdapter;
    RecyclerView recyclerPhukien;

    public frg_quanly_phukien() {
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
        View view = inflater.inflate(R.layout.fragment_frg_quanly_phukien, container, false);
        FloatingActionButton float_add_pk = view.findViewById(R.id.float_add_pk);

        recyclerPhukien = view.findViewById(R.id.recyclerView_quanli_pk);
        SearchView searchView = view.findViewById(R.id.search_quanly_pk);

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
                    quanLyPhuKienAdapter.notifyDataSetChanged();
                }
                return false;
            }
        });


        float_add_pk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showdialogThem();
            }
        });

        return  view;


    }

    public void showdialogThem(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.item_add_phukien, null);
        builder.setView(view);


        EditText edt_tenpk_add = view.findViewById(R.id.edt_tenpk_add);
        EditText edt_gia_add = view.findViewById(R.id.edt_gia_add);
        EditText edt_duongluong_add = view.findViewById(R.id.edt_duongluong_add);
        EditText edt_loairam_add = view.findViewById(R.id.edt_loairam_add);
        EditText edt_hotro_add = view.findViewById(R.id.edt_hotro_add);
        EditText edt_voltage_add = view.findViewById(R.id.edt_voltage_add);
        EditText edt_busram_add = view.findViewById(R.id.edt_busram_add);
        EditText edt_hangsanxuat_add = view.findViewById(R.id.edt_hangsanxuat_add);

        builder.setPositiveButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });




        builder.setNegativeButton("Thêm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String tenpk_add = edt_tenpk_add.getText().toString();
                String gia_add = edt_gia_add.getText().toString();
                String duongluong_add = edt_duongluong_add.getText().toString();
                String loairam_add = edt_loairam_add.getText().toString();
                String hotro_add = edt_hotro_add.getText().toString();
                String voltage_add = edt_voltage_add.getText().toString();
                String busram_add = edt_busram_add.getText().toString();
                String hangsanxuat_add = edt_hangsanxuat_add.getText().toString();




                if ( tenpk_add.isEmpty() || gia_add.isEmpty()  || duongluong_add.isEmpty() || loairam_add.isEmpty() || hotro_add.isEmpty() || voltage_add.isEmpty() || busram_add.isEmpty() || hangsanxuat_add.isEmpty()){

                    Toast.makeText(getContext(), "Không được để trống dữ liệu", Toast.LENGTH_SHORT).show();

                }else {
                    boolean check = phuKienDAO.ThemPhuKien(tenpk_add, Integer.parseInt(gia_add), duongluong_add, loairam_add , hotro_add, voltage_add, busram_add, hangsanxuat_add);

                    if (check){
                        Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                        loadData();

                    }else {
                        Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                    }
                }




            }
        });



        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }


    public void loadData(){
        phuKienDAO = new PhuKienDAO(getContext());
        list = phuKienDAO.selectPHUKIEN();

        GridLayoutManager gridLayoutManager = new GridLayoutManager(requireActivity(),2);

        recyclerPhukien.setLayoutManager(gridLayoutManager);

        quanLyPhuKienAdapter = new QuanLyPhuKienAdapter(getContext(), list, phuKienDAO);
        recyclerPhukien.setAdapter(quanLyPhuKienAdapter);
    }
}