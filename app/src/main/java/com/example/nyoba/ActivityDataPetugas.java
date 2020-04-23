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

public class ActivityDataPetugas extends AppCompatActivity {
    RecyclerView rView;
    List<ModelUser> mUser;
    ModelUser modeluser;
    AdapterPetugasData mAdapter;
    DatabaseReference dReference;
    ValueEventListener vListener;
    ImageView buttoncp;
    ImageView menupetugas;
    Dialog dialogmenu;
    ImageView logout, homedatasiswa, menusiswa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_petugas);
        rView = (RecyclerView)findViewById(R.id.rDataPetugas);
        rView.setHasFixedSize(true);
        rView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        modeluser = new ModelUser();
        mUser = new ArrayList<>();
        mAdapter = new AdapterPetugasData(getApplicationContext(),mUser);
        rView.setAdapter(mAdapter);
        dReference = FirebaseDatabase.getInstance().getReference("User");
        datapetugas();
        buttoncp = (ImageView)findViewById(R.id.buttoncp);
        buttoncp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ActivityPetugasCreate.class));
            menusiswa = (ImageView)findViewById(R.id.menubarpetugas);
            menusiswa.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialogmenu = new Dialog(ActivityDataPetugas.this);
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
    private void datapetugas  (){
        vListener = dReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mUser.clear();
                for (DataSnapshot petugassnapshot:dataSnapshot.getChildren()){
                    ModelUser mu = petugassnapshot.getValue(ModelUser.class);
                    mUser.add(mu);
                }mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ActivityDataPetugas.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}
