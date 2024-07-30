package com.example.lapmarket.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lapmarket.util.Amount;
import com.example.lapmarket.R;
import com.example.lapmarket.dao.PhuKienDAO;
import com.example.lapmarket.model.phukien;

import java.util.ArrayList;

public class PhuKienAdapter extends RecyclerView.Adapter<PhuKienAdapter.ViewHolder> {

    private Context context;
    private ArrayList<phukien> list;

    private PhuKienDAO phuKienDAO;

    public PhuKienAdapter(Context context, ArrayList<phukien> list, PhuKienDAO phuKienDAO) {
        this.context = context;
        this.list = list;
        this.phuKienDAO = phuKienDAO;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_recycler_phukien, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.txt_tenpk.setText(list.get(position).getTenpk());

        holder.txt_gia.setText(Amount.moneyFormat(list.get(position).getGia()));


        holder.txt_xemthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showdialogXemThem(list.get(holder.getAdapterPosition()));
            }
        });



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txt_tenpk, txt_gia, txt_xemthem;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_tenpk = itemView.findViewById(R.id.txt_tensp_phukien);
            txt_gia = itemView.findViewById(R.id.txt_giasp_phukien);
            txt_xemthem = itemView.findViewById(R.id.txt_xemthem_phukien);



        }
    }

    private void showdialogXemThem(phukien pk){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater layoutInflater = ((Activity)context).getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.item_chitiet_phukien, null);
        builder.setView(view);



        //anh xa

        TextView txt_tenpk_chitiet_pk = view.findViewById(R.id.txt_tenpk_chitiet_pk);
        TextView txt_gia_chitiet_pk = view.findViewById(R.id.txt_gia_chitiet_pk);
        TextView txt_ram_chitiet_pk = view.findViewById(R.id.txt_ram_chitiet_pk);
        TextView txt_loairam_chitiet_pk = view.findViewById(R.id.txt_loairam_chitiet_pk);
        TextView txt_busram_chitiet_pk = view.findViewById(R.id.txt_busram_chitiet_pk);
        TextView txt_hotro_chitiet_pk = view.findViewById(R.id.txt_hotro_chitiet_pk);
        TextView txt_voltage_chitiet_pk = view.findViewById(R.id.txt_voltage_chitiet_pk);
        TextView txt_hangsanxuat_chitiet_pk = view.findViewById(R.id.txt_hangsanxuat_chitiet_pk);


        //code


        txt_tenpk_chitiet_pk.setText(pk.getTenpk());
        txt_gia_chitiet_pk.setText(Amount.moneyFormat(pk.getGia() ));
        txt_ram_chitiet_pk.setText(pk.getDungluong());
        txt_loairam_chitiet_pk.setText(pk.getLoairam());
        txt_busram_chitiet_pk.setText(pk.getBusram());
        txt_hotro_chitiet_pk.setText(pk.getHotro());
        txt_voltage_chitiet_pk.setText(pk.getVoltage());
        txt_hangsanxuat_chitiet_pk.setText(pk.getHangsanxuat());



        builder.setPositiveButton("Đóng", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

}
