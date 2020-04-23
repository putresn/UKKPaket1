package com.example.nyoba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ActivitySiswaCreate extends AppCompatActivity {

    EditText nisn, nama, kelas, notelp, alamat;
    Button sumbitcs;
    DatabaseReference createsiswa;
    ModelSiswa modelsiswa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_siswa_create);
        nisn = (EditText)findViewById(R.id.snisncreate);
        nama = (EditText)findViewById(R.id.snamacreate);
        kelas = (EditText)findViewById(R.id.skelascreate);
        notelp = (EditText)findViewById(R.id.stelpcreate);
        alamat = (EditText)findViewById(R.id.salamatcreate);
        sumbitcs = (Button)findViewById(R.id.buttonsubmits);
        createsiswa = FirebaseDatabase.getInstance().getReference("Siswa");
        modelsiswa = new ModelSiswa();
        sumbitcs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createSiswa();
            }
        });

    }

    private void createSiswa() {
        String nisnsiswa = modelsiswa.setNisn(nisn.getText().toString());
        String namasiswa = modelsiswa.setNama(nama.getText().toString().trim());
        String kelassiswa = modelsiswa.setKelas(kelas.getText().toString().trim());
        String notelpsiswa = modelsiswa.setNotelp(notelp.getText().toString());
        String alamatsiswa = modelsiswa.setAlamat(alamat.getText().toString());

        String id = createsiswa.push().getKey();
        ModelSiswa ms = new ModelSiswa(nisnsiswa, namasiswa, kelassiswa, notelpsiswa, alamatsiswa, id);
        createsiswa.child(nisnsiswa).setValue(ms).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(ActivitySiswaCreate.this, "Create Siswa Succes", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),ActivityDataSiswa.class));
            }
        });
    }
}
