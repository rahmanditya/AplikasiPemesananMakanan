package com.example.aplikasipemesananmakanan.Riwayat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplikasipemesananmakanan.R;

import java.util.ArrayList;

public class RiwayatAdapter extends RecyclerView.Adapter<RiwayatAdapter.MyViewHolder> {

    Context context;
    ArrayList<RiwayatModel> riwayatModelArrayList;

    public RiwayatAdapter(Context context, ArrayList<RiwayatModel> riwayatModelArrayList) {
        this.context = context;
        this.riwayatModelArrayList = riwayatModelArrayList;
    }

    @NonNull
    @Override
    public RiwayatAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.item_riwayat,parent,false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RiwayatAdapter.MyViewHolder holder, int position) {

        RiwayatModel riwayatModel = riwayatModelArrayList.get(position);

        holder.totalItem.setText(riwayatModel.totalItem);
        holder.totalHarga.setText(riwayatModel.totalHarga);
        holder.Pembayaran.setText(riwayatModel.radioPembayaran);
        holder.Tgl.setText(riwayatModel.saveTgl);

    }

    @Override
    public int getItemCount() {
        return riwayatModelArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView totalItem, totalHarga,Pembayaran, Tgl;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            totalItem = itemView.findViewById(R.id.tvTotalItem);
            totalHarga = itemView.findViewById(R.id.tvTotalHarga);
            Pembayaran = itemView.findViewById(R.id.tvPembayaran);
            Tgl = itemView.findViewById(R.id.tvTgl);
        }
    }
}
