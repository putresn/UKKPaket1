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

public class ActivityEditSiswa extends AppCompatActivity {

    String unisn, unama, ukelas, unotelp, ualamat, Idsiswa;
    EditText nisn, nama, kelas, notelp, alamat;
    Button updatecs;
    DatabaseReference createsiswa;
    ModelSiswa modelsiswa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_siswa);
        nisn = (EditText)findViewById(R.id.snisnupdate);
        nama = (EditText)findViewById(R.id.snamaupdate);
        kelas = (EditText)findViewById(R.id.skelasupdate);
        notelp = (EditText)findViewById(R.id.stelpupdate);
        alamat = (EditText)findViewById(R.id.salamatupdate);
        updatecs = (Button)findViewById(R.id.buttonupdates);
        createsiswa = FirebaseDatabase.getInstance().getReference("Siswa");
        modelsiswa = new ModelSiswa();

        updatecs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateSiswa();
            }
        });

        unisn = getIntent().getStringExtra("nisn");
        unama = getIntent().getStringExtra("nama");
        ukelas = getIntent().getStringExtra("kelas");
        unotelp = getIntent().getStringExtra("notelp");
        ualamat = getIntent().getStringExtra("alamat");
        Idsiswa = getIntent().getStringExtra("id_siswa");

        nisn.setText(unisn);
        nama.setText(unama);
        kelas.setText(ukelas);
        notelp.setText(unotelp);
        alamat.setText(ualamat);

    }

    private void updateSiswa() {
        String nisnsiswa = modelsiswa.setNisn(nisn.getText().toString());
        String namasiswa = modelsiswa.setNama(nama.getText().toString().trim());
        String kelassiswa = modelsiswa.setKelas(kelas.getText().toString().trim());
        String notelpsiswa = modelsiswa.setNotelp(notelp.getText().toString());
        String alamatsiswa = modelsiswa.setAlamat(alamat.getText().toString());

        String id = Idsiswa;
        ModelSiswa ms = new ModelSiswa(nisnsiswa, namasiswa, kelassiswa, notelpsiswa, alamatsiswa, id);
        createsiswa.child(nisnsiswa).setValue(ms).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(ActivityEditSiswa.this, "Update Siswa Succes", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),ActivityDataSiswa.class));
            }
        });
    }
}
