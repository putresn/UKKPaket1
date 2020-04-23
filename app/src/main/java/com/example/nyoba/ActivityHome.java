package com.example.nyoba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;

public class ActivityHome extends AppCompatActivity implements View.OnClickListener{
    ImageView siswa;
    ImageView petugas;
    ImageView kelas;
    ImageView spp;
    ImageView logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        siswa = (ImageView)findViewById(R.id.siswa);
        siswa.setOnClickListener(this);
        petugas = (ImageView)findViewById(R.id.petugas);
        petugas.setOnClickListener(this);
        kelas = (ImageView)findViewById(R.id.kelas);
        kelas.setOnClickListener(this);
        spp =  (ImageView)findViewById(R.id.spp);
        spp.setOnClickListener(this);
        logout = (ImageView)findViewById(R.id.menubarhome);
        logout.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.siswa:
                startActivity(new Intent(getApplicationContext(),ActivityDataSiswa.class));
                break;

            case R.id.petugas:
                startActivity(new Intent(getApplicationContext(),ActivityDataPetugas.class));
                break;

            case R.id.kelas:
                startActivity(new Intent(getApplicationContext(),ActivityDataKelas.class));
                break;

            case R.id.spp:
                startActivity(new Intent(getApplicationContext(),ActivityDataSPP.class));
                break;

            case R.id.menubarhome:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(),ActivityLogin.class));

        }

    }
}
