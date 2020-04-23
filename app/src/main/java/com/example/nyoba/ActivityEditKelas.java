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

public class ActivityEditKelas extends AppCompatActivity {
    String ukelas, IdKelas, id;
    EditText kelas;
    Button updateck;
    DatabaseReference createkelas;
    ModelKelas modelkelas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_kelas);
        kelas = (EditText)findViewById(R.id.namakelastextu);
        updateck = (Button)findViewById(R.id.buttonsubmitcku);
        createkelas = FirebaseDatabase.getInstance().getReference("Kelas");
        modelkelas = new ModelKelas();
        updateck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateKelas();
            }
        });

        ukelas = getIntent().getStringExtra("kelas");

        kelas.setText(ukelas);

    }
    private void updateKelas() {
        String kelasu = modelkelas.setKelas(kelas.getText().toString());

        id = IdKelas;
        ModelKelas mk = new ModelKelas(kelasu, id);
        createkelas.child(id).setValue(mk).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(ActivityEditKelas.this, "Update Kelas Succes", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),ActivityDataKelas.class));
            }
        });
    }

}
