package com.example.aplikasipemesananmakanan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplikasipemesananmakanan.Adapter.PesananAdapter;
import com.example.aplikasipemesananmakanan.Helper.ManagementCart;
import com.example.aplikasipemesananmakanan.Interface.ChangeNumberItemsListener;

import com.example.aplikasipemesananmakanan.Riwayat.MenuRiwayat;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class DaftarPesanan extends AppCompatActivity {
    FirebaseFirestore db;
    private RecyclerView.Adapter adapter;
    private RecyclerView recPesanan;
    private ManagementCart managementCart;
    private TextView totalHargaPesanan,totalItemPesanan;
    RadioButton radChecked;
    RadioGroup radioGroup;
    private Button btnBayar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_pesanan);
        FirebaseApp.initializeApp(this);

        managementCart=new ManagementCart(this);

        initView();
        initList();
        jumlahTotal();
        pesanMenu();
    }
    private void initList(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        recPesanan.setLayoutManager(new LinearLayoutManager(this));
        adapter = new PesananAdapter(managementCart.getListCart(), this, new ChangeNumberItemsListener() {
            @Override
            public void changed() {
                jumlahTotal();
            }
        });

        recPesanan.setAdapter(adapter);
    }

    private void initView(){
        radioGroup = findViewById(R.id.radGroup);

        btnBayar=findViewById(R.id.tblBayar);
        recPesanan=findViewById(R.id.rec_listPesanan);

        totalHargaPesanan = findViewById(R.id.txtTotalHargaPesanan);
        totalItemPesanan = findViewById(R.id.txtTotItemPesanan);

        db = FirebaseFirestore.getInstance();
    }

    private void jumlahTotal(){
        int totalItem=Math.round(managementCart.getTotalItem());
        double total=Math.round(managementCart.getTotalHarga());
        totalHargaPesanan.setText("Rp."+total);
        totalItemPesanan.setText(""+totalItem);
    }

    public void pesanMenu(){
        btnBayar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = radioGroup.getCheckedRadioButtonId();
                radChecked=findViewById(selectedId);

                String saveTgl;
                Calendar calendar = Calendar.getInstance();

                SimpleDateFormat hariIni = new SimpleDateFormat("MM ,dd, yyyy");
                saveTgl = hariIni.format(calendar.getTime());

                String totalHarga = totalHargaPesanan.getText().toString();
                String totalItem = totalItemPesanan.getText().toString();
                String radioPembayaran = radChecked.getText().toString();

                Map<String,Object> history = new HashMap<>();

                history.put("totalHarga",totalHarga);
                history.put("totalItem", totalItem);
                history.put("saveTgl",saveTgl);
                history.put("radioPembayaran",radioPembayaran);

                db.collection("History")
                        .add(history)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                               Toast.makeText(DaftarPesanan.this,"Pembelian Sukses",Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(DaftarPesanan.this,"Pembelian Gagal",Toast.LENGTH_SHORT).show();
                            }
                        });
                Intent i = new Intent(DaftarPesanan.this, MenuRiwayat.class);
                startActivity(i);
                finish();
            }

        });
    }
}