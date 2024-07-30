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
import com.example.lapmarket.adapter.QuanLyGamingAdapter;
import com.example.lapmarket.adapter.QuanLySanPhamAdapter;
import com.example.lapmarket.dao.SanPhamDAO;
import com.example.lapmarket.model.sanpham;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class frg_quanly_gaming extends Fragment {
    SanPhamDAO sanPhamDAO;
    ArrayList<sanpham> list;
    ArrayList<sanpham> list1;

    QuanLyGamingAdapter quanLyGamingAdapter;
    RecyclerView recyclerQuanliSP;


    public frg_quanly_gaming() {
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
        View view =  inflater.inflate(R.layout.fragment_frg_quanly_gaming, container, false);

        FloatingActionButton float_add_gaming = view.findViewById(R.id.float_add_gaming);

        recyclerQuanliSP = view.findViewById(R.id.recyclerView_quanli_gaming);
        SearchView searchView = view.findViewById(R.id.search_quanly_gaming);

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
                    quanLyGamingAdapter.notifyDataSetChanged();
                }
                return false;
            }
        });

        float_add_gaming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showdialogThem();
            }
        });
        return view;
    }

    public void showdialogThem(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.item_add_sanpham, null);
        builder.setView(view);


        //
        EditText edt_tensp_add = view.findViewById(R.id.edt_tensp_add);
        EditText edt_giasp_add = view.findViewById(R.id.edt_giasp_add);
        EditText edt_thuonghieu_add = view.findViewById(R.id.edt_thuonghieu_add);
        EditText edt_xuatxu_add = view.findViewById(R.id.edt_xuatxu_add);
        EditText edt_kichthuocmanhinh_add = view.findViewById(R.id.edt_kichthuocmanhinh_add);
        EditText edt_mausac_add = view.findViewById(R.id.edt_mausac_add);
        EditText edt_trongluong_add = view.findViewById(R.id.edt_trongluong_add);
        EditText edt_chatlieu_add = view.findViewById(R.id.edt_chatlieu_add);
        EditText edt_cpu_add = view.findViewById(R.id.edt_cpu_add);
        EditText edt_ocung_add = view.findViewById(R.id.edt_ocung_add);
        EditText edt_ram_add = view.findViewById(R.id.edt_ram_add);
        EditText edt_rom_add = view.findViewById(R.id.edt_rom_add);
        EditText edt_card_add = view.findViewById(R.id.edt_card_add);
        EditText edt_tocdocpu_add = view.findViewById(R.id.edt_tocdocpu_add);
        EditText edt_congusb_add = view.findViewById(R.id.edt_congusb_add);
        EditText edt_vantay_add = view.findViewById(R.id.edt_vantay_add);

        builder.setPositiveButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.setNegativeButton("Thêm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String tensp_add = edt_tensp_add.getText().toString();
                String giasp_add = edt_giasp_add.getText().toString();
                String thuonghieu_add = edt_thuonghieu_add.getText().toString();
                String xuatxu_add = edt_xuatxu_add.getText().toString();
                String kichthuocmanhinh_add = edt_kichthuocmanhinh_add.getText().toString();
                String mausac_add = edt_mausac_add.getText().toString();
                String trongluong_add = edt_trongluong_add.getText().toString();
                String chatlieu_add = edt_chatlieu_add.getText().toString();
                String cpu_add = edt_cpu_add.getText().toString();
                String ocung_add = edt_ocung_add.getText().toString();
                String ram_add = edt_ram_add.getText().toString();
                String rom_add = edt_rom_add.getText().toString();
                String card_add = edt_card_add.getText().toString();
                String tocdocpu_add = edt_tocdocpu_add.getText().toString();
                String congusb_add = edt_congusb_add.getText().toString();
                String vantay_add = edt_vantay_add.getText().toString();



                if ( tensp_add.isEmpty() || giasp_add.isEmpty()  || thuonghieu_add.isEmpty() || xuatxu_add.isEmpty() || kichthuocmanhinh_add.isEmpty() || mausac_add.isEmpty() || trongluong_add.isEmpty() || chatlieu_add.isEmpty() || cpu_add.isEmpty() || ocung_add.isEmpty() || ram_add.isEmpty() || rom_add.isEmpty() || card_add.isEmpty() || tocdocpu_add.isEmpty() || congusb_add.isEmpty() || vantay_add.isEmpty()){

                    Toast.makeText(getContext(), "Không được để trống dữ liệu", Toast.LENGTH_SHORT).show();

                }else {
                    boolean check = sanPhamDAO.addGAM(tensp_add, Integer.parseInt(giasp_add), thuonghieu_add, xuatxu_add , kichthuocmanhinh_add, mausac_add, trongluong_add, chatlieu_add, cpu_add, ocung_add, ram_add, rom_add, card_add,tocdocpu_add,congusb_add,vantay_add );

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
        sanPhamDAO = new SanPhamDAO(getContext());
        list = sanPhamDAO.selectGAMING();


        GridLayoutManager gridLayoutManager = new GridLayoutManager(requireActivity(),2);
        recyclerQuanliSP.setLayoutManager(gridLayoutManager);

        quanLyGamingAdapter = new QuanLyGamingAdapter(getContext(), list, sanPhamDAO);
        recyclerQuanliSP.setAdapter(quanLyGamingAdapter);
    }
}