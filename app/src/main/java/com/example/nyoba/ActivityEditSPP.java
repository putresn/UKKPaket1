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

public class ActivityEditSPP extends AppCompatActivity {
    String utahun, unominal, IdSpp, id;
    EditText tahun, nominal;
    Button updatesppp;
    DatabaseReference createspp;
    ModelSPP modelspp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_s_p_p);
        tahun = (EditText)findViewById(R.id.tahunsppcu);
        nominal = (EditText)findViewById(R.id.nominalsppcu);
        updatesppp = (Button)findViewById(R.id.buttonsubmitcsppu);
        createspp = FirebaseDatabase.getInstance().getReference("SPP");
        modelspp = new ModelSPP();

        updatesppp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateSpp();
            }
        });

        utahun = getIntent().getStringExtra("tahun");
        unominal = getIntent().getStringExtra("nominal");

        tahun.setText(utahun);
        nominal.setText(unominal);

    }

    private void updateSpp() {
        String tahunp = modelspp.setTahun(tahun.getText().toString());
        String nominalp = modelspp.setNominal(nominal.getText().toString().trim());

        id = IdSpp;
        ModelSPP mspp = new ModelSPP(id, tahunp, nominalp);
        createspp.child(id).setValue(mspp).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(ActivityEditSPP.this, "Update SPP Succes", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),ActivityDataSPP.class));
            }
        });
    }
}
