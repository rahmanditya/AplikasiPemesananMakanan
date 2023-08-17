package com.example.aplikasipemesananmakanan.Riwayat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplikasipemesananmakanan.MainActivity;
import com.example.aplikasipemesananmakanan.R;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class MenuRiwayat extends AppCompatActivity {
    
    RecyclerView recyclerView;
    ArrayList<RiwayatModel> riwayatModelArrayList;
    RiwayatAdapter riwayatAdapter;
    FirebaseFirestore db;
    ProgressDialog progressDialog;
    Button btnHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riwayat);

        btnHome = findViewById(R.id.tblHome);

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Fetching Data...");
        progressDialog.show();
        
        recyclerView = findViewById(R.id.rec_riwayatPesanan);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        
        db = FirebaseFirestore.getInstance();
        riwayatModelArrayList = new ArrayList<RiwayatModel>();
        riwayatAdapter = new RiwayatAdapter(MenuRiwayat.this, riwayatModelArrayList);

        recyclerView.setAdapter(riwayatAdapter);
        
        EventChangeListener();

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MenuRiwayat.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

    private void EventChangeListener() {
        db.collection("History")//.orderBy("firstName", Query.Direction.ASCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if(error !=null){

                            if(progressDialog.isShowing())
                                progressDialog.dismiss();
                            Log.e("Firestore error",error.getMessage());
                            return;

                        }
                        for (DocumentChange dc : value.getDocumentChanges()){
                            if (dc.getType()==DocumentChange.Type.ADDED){

                                riwayatModelArrayList.add(dc.getDocument().toObject(RiwayatModel.class));

                            }

                            riwayatAdapter.notifyDataSetChanged();
                            if(progressDialog.isShowing())
                                progressDialog.dismiss();

                        }
                        
                    }
                });
    }
}