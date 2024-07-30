package com.example.lapmarket.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lapmarket.R;
import com.example.lapmarket.dao.PhuKienDAO;
import com.example.lapmarket.model.phukien;
import com.example.lapmarket.util.Amount;

import java.util.ArrayList;

public class QuanLyPhuKienAdapter extends RecyclerView.Adapter<QuanLyPhuKienAdapter.ViewHolder> {

    private Context context;
    private ArrayList<phukien> list;

    private PhuKienDAO phuKienDAO;

    public QuanLyPhuKienAdapter(Context context, ArrayList<phukien> list, PhuKienDAO phuKienDAO) {
        this.context = context;
        this.list = list;
        this.phuKienDAO = phuKienDAO;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_admin_phukien, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.txt_tenpk_ql.setText(list.get(position).getTenpk());
        holder.txt_gia_ql.setText(Amount.moneyFormat(list.get(position).getGia()));


        holder.txt_xemthem_ql.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showdialogXemThem(list.get(holder.getAdapterPosition()));
            }
        });

        holder.btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context); //tạo dối tượng
                builder.setIcon(R.drawable.canhbao); //set icon
                builder.setMessage("Bạn chắc chắn muốn xóa");


                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //
                        boolean check = phuKienDAO.deletePhuKien(list.get(holder.getAdapterPosition()).getMapk());
                        if (check){
                            loadData();
                            Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(context, "Không xóa", Toast.LENGTH_SHORT).show();
                    }
                });
                android.app.AlertDialog dialog = builder.create(); //tạo hộp thoại
                dialog.show();


            }
        });

        holder.btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogUpdate(list.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txt_tenpk_ql, txt_gia_ql, txt_xemthem_ql;
        Button btn_delete, btn_update;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_tenpk_ql = itemView.findViewById(R.id.txt_tensp_phukien_ql);
            txt_gia_ql = itemView.findViewById(R.id.txt_giasp_phukien_ql);
            txt_xemthem_ql = itemView.findViewById(R.id.txt_xemthem_phukien_ql);
            btn_delete = itemView.findViewById(R.id.btn_delete_phukien);
            btn_update = itemView.findViewById(R.id.btn_update_phukien);
        }
    }

    public void dialogUpdate(phukien pk){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater layoutInflater = ((Activity)context).getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.item_update_phukien, null);
        builder.setView(view);

        //
        EditText edt_tenpk_update = view.findViewById(R.id.edt_tenpk_update);
        EditText edt_gia_update = view.findViewById(R.id.edt_gia_update);
        EditText edt_dungluong_update = view.findViewById(R.id.edt_dungluong_update);
        EditText edt_loairam_update = view.findViewById(R.id.edt_loairam_update);
        EditText edt_hotro_update = view.findViewById(R.id.edt_hotro_update);
        EditText edt_voltage_update = view.findViewById(R.id.edt_voltage_update);
        EditText edt_busram_update = view.findViewById(R.id.edt_busram_update);
        EditText edt_hangsanxuat_update = view.findViewById(R.id.edt_hangsanxuat_update);


        //
        edt_tenpk_update.setText(pk.getTenpk());
        edt_gia_update.setText(String.valueOf(pk.getGia()));
        edt_dungluong_update.setText(pk.getDungluong());
        edt_loairam_update.setText(pk.getLoairam());
        edt_hotro_update.setText(pk.getHotro());
        edt_voltage_update.setText(pk.getVoltage());
        edt_busram_update.setText(pk.getBusram());
        edt_hangsanxuat_update.setText(pk.getHangsanxuat());


        //
        builder.setNegativeButton("Cập Nhật", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String tenpk_update = edt_tenpk_update.getText().toString();
                int gia_update = Integer.parseInt(edt_gia_update.getText().toString());
                String dungluong_update = edt_dungluong_update.getText().toString();
                String loairam_update = edt_loairam_update.getText().toString();
                String hotro_update = edt_hotro_update.getText().toString();
                String voltage_update = edt_voltage_update.getText().toString();
                String busram_update = edt_busram_update.getText().toString();
                String hangsanxuat_update = edt_hangsanxuat_update.getText().toString();
                int mapk = pk.getMapk();

                    if (tenpk_update.isEmpty() || String.valueOf(gia_update).isEmpty() || dungluong_update.isEmpty() || loairam_update.isEmpty() || hotro_update.isEmpty() || voltage_update.isEmpty() || busram_update.isEmpty() || hangsanxuat_update.isEmpty()){

                }else {


                    boolean check = phuKienDAO.updatePhuKien(mapk, tenpk_update, gia_update, dungluong_update, loairam_update, hotro_update, voltage_update, busram_update, hangsanxuat_update);
                    if (check) {
                        Toast.makeText(context, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                        loadData();
                    } else {
                        Toast.makeText(context, "Cập nhật không thành công", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });

        //
        builder.setPositiveButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
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
        txt_gia_chitiet_pk.setText(Amount.moneyFormat(pk.getGia()));
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

    public void loadData(){
        list.clear();
        list = phuKienDAO.selectPHUKIEN();
        notifyDataSetChanged();
    }
}
