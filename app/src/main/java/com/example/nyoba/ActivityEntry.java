package com.example.nyoba;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ActivityEntry extends AppCompatActivity {
    EditText jumlah;
    ArrayList<String> arrayentry, arrayentryu;
    Spinner nama, tanggal;
    Button submite;
    String entryy, apawelah, entryu, apawelahh;
    DatabaseReference createentry, siswa, tanggalspp;
    ModelEntry modelentry;
    Dialog dialogmenu;
    ImageView logout, homedatasiswa, menusiswa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);

        menusiswa = (ImageView)findViewById(R.id.menubarentry);
        menusiswa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogmenu = new Dialog(ActivityEntry.this);
                dialogmenu.setContentView(R.layout.logoutmenup);
                dialogmenu.setCancelable(true);


                logout = dialogmenu.findViewById(R.id.logoutds);
                homedatasiswa = dialogmenu.findViewById(R.id.history);
                homedatasiswa.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getApplicationContext(),ActivityHistory.class));
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





        nama = (Spinner) findViewById(R.id.namaentry);
        tanggal = (Spinner) findViewById(R.id.tanggalentry);
        jumlah = (EditText) findViewById(R.id.jumlahentry);
        submite = (Button) findViewById(R.id.buttonentry);
        createentry = FirebaseDatabase.getInstance().getReference("Pembayaran");
        siswa = FirebaseDatabase.getInstance().getReference("Siswa");
        tanggalspp = FirebaseDatabase.getInstance().getReference("SPP");
        modelentry = new ModelEntry();
        arrayentry = new ArrayList<>();
        arrayentryu = new ArrayList<>();
        tanggalspp.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot forsnapp:dataSnapshot.getChildren()){
                    entryu = forsnapp.child("tahun").getValue(String.class);
                    arrayentryu.add(entryu);

                    ArrayAdapter<String> arrayvare = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, arrayentryu);
                    arrayvare.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    tanggal.setAdapter(arrayvare);
                    tanggal.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            apawelahh = parent.getItemAtPosition(position).toString();
                            Toast.makeText(ActivityEntry.this, apawelahh, Toast.LENGTH_SHORT).show();
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });

                }

            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        siswa.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot forsnap:dataSnapshot.getChildren()){

                    entryy = forsnap.child("nama").getValue(String.class);
                    arrayentry.add(entryy);



                    ArrayAdapter<String> arrayvar = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, arrayentry);
                    arrayvar.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    nama.setAdapter(arrayvar);
                    nama.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            apawelah = parent.getItemAtPosition(position).toString();
                            Toast.makeText(ActivityEntry.this, apawelah, Toast.LENGTH_SHORT).show();

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        submite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createEntry();
            }
        });

    }

    private void createEntry() {
        String jumlahe = modelentry.setJumlah(jumlah.getText().toString().trim());

        String id = createentry.push().getKey();
        ModelEntry me = new ModelEntry(id, apawelah, apawelahh, jumlahe);
        createentry.child(id).setValue(me).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(ActivityEntry.this, "Create Pembayaran Succes", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),ActivityEntry.class));
            }
        });
    }



}