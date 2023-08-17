package com.example.aplikasipemesananmakanan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplikasipemesananmakanan.Adapter.MenuAdapter;
import com.example.aplikasipemesananmakanan.Helper.ManagementCart;
import com.example.aplikasipemesananmakanan.Interface.ChangeNumberItemsListener;
import com.example.aplikasipemesananmakanan.Model.Menu;
import com.example.aplikasipemesananmakanan.Riwayat.MenuRiwayat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapter;
    private RecyclerView recMenu;
    private ManagementCart managementCart;
    TextView jmlTxt,totalTxt;
    private Button btnBayar,btnRiwayat;
    private ArrayList<Menu> listMenu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        managementCart=new ManagementCart(this);
        initData();
        initView();
        initList();
        jumlahTotal();
        tombol();
    }

    private void initView(){
        recMenu=findViewById(R.id.rec_listPesanan);
        jmlTxt=findViewById(R.id.jmlItem);
        totalTxt=findViewById(R.id.txtTotalHrg);
        btnBayar=findViewById(R.id.tblBayar);
        btnRiwayat=findViewById(R.id.tblRiwayat);
    }

    private void initData(){
        this.listMenu = new ArrayList<>();
        listMenu.add(new Menu("Mie Cakep","Mie dengan rasa gurih pedas"
                ,16000F,R.drawable.mie_cakep,4));
        listMenu.add(new Menu("Mie Ganteng","Mie dengan rasa manis pedas"
                ,15000F,R.drawable.mie_ganteng,1));
        listMenu.add(new Menu("Dimsum","Sajian dimsum asli cina"
                ,9000F,R.drawable.dimsum,1));
        listMenu.add(new Menu("Es Jeruk","Es/panas jeruk"
                ,3000F,R.drawable.es_jeruk,4));
    }

    private void initList(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        recMenu.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MenuAdapter(listMenu, this, new ChangeNumberItemsListener() {
            @Override
            public void changed() {
                jumlahTotal();
            }
        });

        recMenu.setAdapter(adapter);
    }

    private void jumlahTotal(){
        int totalItem=Math.round(managementCart.getTotalItem());
        double total=Math.round(managementCart.getTotalHarga());
        totalTxt.setText("Rp."+total);
        jmlTxt.setText(""+totalItem);
    }

    private void tombol(){
        btnBayar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DaftarPesanan.class);
                v.getContext().startActivities(new Intent[]{intent});
            }
        });

        btnRiwayat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, MenuRiwayat.class);
                startActivity(i);
                finish();
            }
        });
    }
}