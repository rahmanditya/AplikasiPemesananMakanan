package com.example.aplikasipemesananmakanan.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplikasipemesananmakanan.Helper.ManagementCart;
import com.example.aplikasipemesananmakanan.Interface.ChangeNumberItemsListener;
import com.example.aplikasipemesananmakanan.Model.Menu;
import com.example.aplikasipemesananmakanan.R;

import java.util.ArrayList;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> {
    private ArrayList<Menu> listMenu;
    private ManagementCart managementCart;
    private ChangeNumberItemsListener changeNumberItemsListener;

    public MenuAdapter(ArrayList<Menu> listMenu, Context context , ChangeNumberItemsListener changeNumberItemsListener) {
        this.listMenu = listMenu;
        this.managementCart = new ManagementCart(context);
        this.changeNumberItemsListener = changeNumberItemsListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_menu,parent,false);

        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Menu menu = listMenu.get(position);
        holder.txtNama.setText(menu.getNama());
        holder.txtDesc.setText(menu.getDeskripsi());
        holder.txtHrg.setText(Float.toString(menu.getHarga()));
        holder.txtTot.setText(String.valueOf(Math.round((menu.getNumberInCart() * menu.getHarga()) * 100)/100 ));
        holder.txtJumlah.setText(Integer.toString(menu.getNumberInCart()));
        holder.imgFoto.setImageResource(menu.getId_gambar());

        holder.btnPls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                managementCart.plusNumberFood(listMenu, position, new ChangeNumberItemsListener() {
                    @Override
                    public void changed() {
                        notifyDataSetChanged();
                        changeNumberItemsListener.changed();
                    }
                });
            }
        });

        holder.btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                managementCart.minusNumberFood(listMenu, position, new ChangeNumberItemsListener() {
                    @Override
                    public void changed() {
                        notifyDataSetChanged();
                        changeNumberItemsListener.changed();
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return listMenu.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txtNama;
        public TextView txtDesc;
        public TextView txtHrg;
        public TextView txtJumlah;
        public TextView txtTot;
        public ImageView imgFoto;
        public Button btnMinus,btnPls,btnBayar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtNama = (TextView) itemView.findViewById(R.id.txtTotalRiwayatHarga);
            txtDesc = (TextView) itemView.findViewById(R.id.txtTotalItemPesanan);
            txtHrg = (TextView) itemView.findViewById(R.id.txtHrg);
            txtJumlah = (TextView) itemView.findViewById(R.id.txtJmlItem);
            txtTot = (TextView) itemView.findViewById(R.id.txtTotalHarga);
            imgFoto = (ImageView) itemView.findViewById(R.id.imgFoto);
            btnMinus = (Button) itemView.findViewById(R.id.btnMin);
            btnPls = (Button) itemView.findViewById(R.id.btnPlus);
            btnBayar = (Button) itemView.findViewById(R.id.tblBayar);


        }
    }
}
