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

public class ActivityDataSPP extends AppCompatActivity {

    RecyclerView rView;
    List<ModelSPP> mSpp;
    ModelSPP modelspp;
    AdapterSPPData mAdapter;
    DatabaseReference dReference;
    ValueEventListener vListener;
    ImageView buttonspp;
    ImageView menuspp;
    Dialog dialogmenu;
    ImageView logout, homedatasiswa, menusiswa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_s_p_p);
        rView = (RecyclerView)findViewById(R.id.rDataSpp);
        rView.setHasFixedSize(true);
        rView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        modelspp = new ModelSPP();
        mSpp = new ArrayList<>();
        mAdapter = new AdapterSPPData(getApplicationContext(),mSpp);
        rView.setAdapter(mAdapter);
        dReference = FirebaseDatabase.getInstance().getReference("SPP");
        dataspp();
        buttonspp = (ImageView)findViewById(R.id.buttoncspp);
        buttonspp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ActivitySPPCreate.class));
                menusiswa = (ImageView)findViewById(R.id.menubarspp);
                menusiswa.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogmenu = new Dialog(ActivityDataSPP.this);
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
    private void dataspp  (){
        vListener = dReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mSpp.clear();
                for (DataSnapshot sppsnapshot:dataSnapshot.getChildren()){
                    ModelSPP mspp = sppsnapshot.getValue(ModelSPP.class);
                    mSpp.add(mspp);
                }mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ActivityDataSPP.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }


}
