package com.example.nyoba;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
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

public class ActivityDataKelas extends AppCompatActivity {
    RecyclerView rView;
    List<ModelKelas> mKelas;
    ModelKelas modelkelas;
    AdapterKelasData mAdapter;
    DatabaseReference dReference;
    ValueEventListener vListener;
    ImageView buttonck;
    ImageView menukelas;
    Dialog dialogmenu;
    ImageView logout, homedatasiswa, menusiswa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_kelas);
        rView = (RecyclerView)findViewById(R.id.rDataKelas);
        rView.setHasFixedSize(true);
        rView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        modelkelas = new ModelKelas();
        mKelas = new ArrayList<>();
        mAdapter = new AdapterKelasData(getApplicationContext(),mKelas);
        rView.setAdapter(mAdapter);
        dReference = FirebaseDatabase.getInstance().getReference("Kelas");
        datakelas();
        buttonck = (ImageView)findViewById(R.id.buttonck);
        buttonck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ActivityKelasCreate.class));
                menusiswa = (ImageView)findViewById(R.id.menubarkelas);
                menusiswa.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogmenu = new Dialog(ActivityDataKelas.this);
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
    private void datakelas  (){
        vListener = dReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mKelas.clear();
                for (DataSnapshot datasnapshot:dataSnapshot.getChildren()){
                    ModelKelas mk = datasnapshot.getValue(ModelKelas.class);
                    mKelas.add(mk);
                }mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ActivityDataKelas.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}
