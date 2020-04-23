package com.example.nyoba;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActionBar;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.ColorSpace;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ActivityDataSiswa extends AppCompatActivity {
    RecyclerView rView;
    List<ModelSiswa> mSiswa;
    ModelSiswa modelsiswa;
    AdapterSiswaData mAdapter;
    DatabaseReference dReference;
    ValueEventListener vListener;
    ImageView buttoncs;
    ImageView menusiswa;
    Dialog dialogmenu;
    ImageView logout, homedatasiswa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_siswa);
        rView = (RecyclerView)findViewById(R.id.rDataSiswa);
        rView.setHasFixedSize(true);
        rView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        modelsiswa = new ModelSiswa();
        mSiswa = new ArrayList<>();
        mAdapter = new AdapterSiswaData(getApplicationContext(),mSiswa);
        rView.setAdapter(mAdapter);
        dReference = FirebaseDatabase.getInstance().getReference("Siswa");
        datasiswa();
        buttoncs = (ImageView)findViewById(R.id.buttoncs);
        buttoncs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ActivitySiswaCreate.class));
        menusiswa = (ImageView)findViewById(R.id.menubarsiswa);
        menusiswa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogmenu = new Dialog(ActivityDataSiswa.this);
                dialogmenu.setContentView(R.layout.logoutmenu);
                dialogmenu.setCancelable(true);


                logout = dialogmenu.findViewById(R.id.logoutds);
                homedatasiswa = dialogmenu.findViewById(R.id.homeds);
                homedatasiswa.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getApplicationContext(),ActivityHome.class));
                    }
                });
                logout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        FirebaseAuth.getInstance().signOut();
                        startActivity(new Intent(getApplicationContext(),ActivityLogin.class));
                    }
                });
                dialogmenu.show();
            }
        });



            }
        });

    }
    private void datasiswa  (){
        vListener = dReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mSiswa.clear();
                for (DataSnapshot siswasnapshot:dataSnapshot.getChildren()){
                    ModelSiswa ms = siswasnapshot.getValue(ModelSiswa.class);
                    mSiswa.add(ms);
                }mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ActivityDataSiswa.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }



}
