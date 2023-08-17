package com.example.aplikasipemesananmakanan.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplikasipemesananmakanan.Helper.ManagementCart;
import com.example.aplikasipemesananmakanan.Interface.ChangeNumberItemsListener;
import com.example.aplikasipemesananmakanan.Model.Menu;
import com.example.aplikasipemesananmakanan.R;

import java.util.ArrayList;

public class PesananAdapter extends RecyclerView.Adapter<PesananAdapter.ViewHolder> {
    private ArrayList<Menu> listMenu;
    private ManagementCart managementCart;
    ChangeNumberItemsListener changeNumberItemsListener;

    public PesananAdapter(ArrayList<Menu> listMenu, Context context , ChangeNumberItemsListener changeNumberItemsListener) {
        this.listMenu = listMenu;
        this.managementCart = new ManagementCart(context);
        this.changeNumberItemsListener = changeNumberItemsListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pesanan,parent,false);

        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Menu menu = listMenu.get(position);
        holder.namaPesananTxt.setText(menu.getNama());
        holder.hrgPesananTxt.setText(Float.toString(menu.getHarga()));
        holder.jmlItemTxt.setText(Integer.toString(menu.getNumberInCart()));
    }

    @Override
    public int getItemCount() {
        return listMenu.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView namaPesananTxt;
        public TextView hrgPesananTxt;
        public TextView jmlItemTxt;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            namaPesananTxt = (TextView) itemView.findViewById(R.id.txtNamaPesananHarga);
            hrgPesananTxt = (TextView) itemView.findViewById(R.id.txtHargaPesanan);
            jmlItemTxt = (TextView) itemView.findViewById(R.id.txtItemPesanan);
        }
    }
}
