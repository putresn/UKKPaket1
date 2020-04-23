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

public class ActivityHistory extends AppCompatActivity {

    RecyclerView rView;
    List<ModelEntry> mEntry;
    ModelEntry modelentry;
    AdapterEntry mAdapter;
    DatabaseReference dReference;
    ValueEventListener vListener;
    ImageView buttonce;
    ImageView menusiswa;
    Dialog dialogmenu;
    ImageView logout, homedatasiswa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        rView = (RecyclerView) findViewById(R.id.rHistory);
        rView.setHasFixedSize(true);
        rView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        modelentry = new ModelEntry();
        mEntry = new ArrayList<>();
        mAdapter = new AdapterEntry(getApplicationContext(), mEntry);
        rView.setAdapter(mAdapter);
        dReference = FirebaseDatabase.getInstance().getReference("Pembayaran");
        dataentry();
        menusiswa = (ImageView) findViewById(R.id.menubarhistory);
        menusiswa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogmenu = new Dialog(ActivityHistory.this);
                dialogmenu.setContentView(R.layout.logoutmenu);
                dialogmenu.setCancelable(true);


                logout = dialogmenu.findViewById(R.id.logoutds);
                homedatasiswa = dialogmenu.findViewById(R.id.homeds);
                homedatasiswa.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getApplicationContext(), ActivityHome.class));
                    }
                });
                logout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        FirebaseAuth.getInstance().signOut();
                        startActivity(new Intent(getApplicationContext(), ActivityLogin.class));
                    }
                });
                dialogmenu.show();
            }
        });


    }
    private void dataentry  () {
        vListener = dReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mEntry.clear();
                for (DataSnapshot entrysnapshot : dataSnapshot.getChildren()) {
                    ModelEntry me = entrysnapshot.getValue(ModelEntry.class);
                    mEntry.add(me);
                }
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ActivityHistory.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}



