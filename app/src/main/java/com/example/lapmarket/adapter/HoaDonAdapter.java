package com.example.lapmarket.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lapmarket.R;
import com.example.lapmarket.dao.HoaDonDAO;
import com.example.lapmarket.model.hoadon;

import com.example.lapmarket.util.Amount;

import java.util.ArrayList;

public class HoaDonAdapter extends RecyclerView.Adapter<HoaDonAdapter.ViewHolder> {
    private Context context;
    private ArrayList<hoadon> list;

    private HoaDonDAO hoaDonDAO;


    public HoaDonAdapter(Context context, ArrayList<hoadon> list, HoaDonDAO hoaDonDAO) {
        this.context = context;
        this.list = list;
        this.hoaDonDAO = hoaDonDAO;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_thongtin_hoadon, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txt_mahd_hd.setText("Mã hóa đơn: " + list.get(position).getMahd());
        holder.txt_hoten_hd.setText("Họ tên: " + list.get(position).getHoten());
        holder.txt_tensp_hd.setText("Tên sản phẩm: " + list.get(position).getTensp());
        holder.txt_soluong_hd.setText("Số lượng: " + list.get(position).getSoluong());
        holder.txt_gia_hd.setText("Giá: " + Amount.moneyFormat( list.get(position).getGia() * list.get(position).getSoluong()));
        holder.txt_ngaymua_hd.setText("Ngày mua: " + list.get(position).getNgaymua());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context); //tạo dối tượng
                builder.setIcon(R.drawable.canhbao); //set icon
                builder.setMessage("Bạn chắc chắn muốn hủy đơn");


                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //
                        boolean check = hoaDonDAO.deleteHD(list.get(holder.getAdapterPosition()).getMahd());
                        if (check){
                            loadData();

                            Toast.makeText(context, "Hủy đơn thành công", Toast.LENGTH_SHORT).show();

                        }else {
                            Toast.makeText(context, "Hủy đơn thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                android.app.AlertDialog dialog = builder.create(); //tạo hộp thoại
                dialog.show();



            }
        });

        String trangThai;
        if (list.get(position).getTrangthai() == 1){
            trangThai = "Đã thanh toán";
            holder.btn_thanhToan.setVisibility(View.GONE);

        }else {
            trangThai = "Chưa thanh toán";
            holder.btn_thanhToan.setVisibility(View.VISIBLE);

        }
        holder.txt_trangthai_hd.setText("Trang Thái: " + trangThai);

        holder.btn_thanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                dialogThanhToan(holder);


            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_mahd_hd,txt_hoten_hd,txt_tensp_hd,txt_soluong_hd,txt_gia_hd,txt_ngaymua_hd,txt_trangthai_hd;
        Button btn_thanhToan;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_mahd_hd = itemView.findViewById(R.id.txt_mahd_hd);
            txt_hoten_hd = itemView.findViewById(R.id.txt_hoten_hd);
            txt_tensp_hd = itemView.findViewById(R.id.txt_tensp_hd);
            txt_soluong_hd = itemView.findViewById(R.id.txt_soluong_hd);
            txt_gia_hd = itemView.findViewById(R.id.txt_gia_hd);
            txt_ngaymua_hd = itemView.findViewById(R.id.txt_ngaymua_hd);
            txt_trangthai_hd = itemView.findViewById(R.id.txt_trangthai_hd);
            btn_thanhToan = itemView.findViewById(R.id.btn_thanhtoan_hd);
        }
    }

    public void dialogThanhToan(ViewHolder holder){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater layoutInflater = ((Activity)context).getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.item_thanhtoan, null);
        builder.setView(view);




        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                hoaDonDAO = new HoaDonDAO(context);
                if (hoaDonDAO.thayDoiTrangThai(list.get(holder.getAdapterPosition()).getMahd())){
                    list.clear();
                    list = hoaDonDAO.selectHoaDon();
                    hoaDonDAO.thayDoiTrangThai(1);
                    notifyDataSetChanged();
                }else {
                    Toast.makeText(context, "Thanh toán không thành công", Toast.LENGTH_SHORT).show();
                }

            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        AlertDialog dialog = builder.create(); //tạo hộp thoại
        dialog.show();


    }

    private void loadData(){
        list.clear();
        list = hoaDonDAO.selectHoaDon();
        notifyDataSetChanged();
    }
}

