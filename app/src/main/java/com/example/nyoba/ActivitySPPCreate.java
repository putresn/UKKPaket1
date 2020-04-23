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

public class ActivitySPPCreate extends AppCompatActivity {
    EditText tahunspp, nominalspp;
    Button submitspp;
    DatabaseReference createspp;
    ModelSPP modelspp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s_p_p_create);
        tahunspp = (EditText)findViewById(R.id.tahunsppc);
        nominalspp = (EditText)findViewById(R.id.nominalsppc);
        submitspp = (Button)findViewById(R.id.buttonsubmitcspp);
        createspp = FirebaseDatabase.getInstance().getReference("SPP");
        modelspp = new ModelSPP();
        submitspp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createSPP();
            }
        });

    }
    private void createSPP() {
        String tahunsppp = modelspp.setTahun(tahunspp.getText().toString());
        String nominalsppp = modelspp.setNominal(nominalspp.getText().toString().trim());

        String id = createspp.push().getKey();
        ModelSPP mpp = new ModelSPP(tahunsppp, nominalsppp, id);
        createspp.child(id).setValue(mpp).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(ActivitySPPCreate.this, "Create SPP Succes", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),ActivityDataSPP.class));
            }
        });
    }
}
