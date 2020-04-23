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

public class ActivityPetugasCreate extends AppCompatActivity {

    EditText username, nama, level, password;
    Button submitcp;
    DatabaseReference createpetugas;
    ModelUser modeluser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_petugas_create);
        username = (EditText)findViewById(R.id.userpetugas);
        nama = (EditText)findViewById(R.id.namapetugas);
        level = (EditText)findViewById(R.id.levelpetugas);
        password = (EditText)findViewById(R.id.passwordpetugas);
        submitcp = (Button)findViewById(R.id.buttonsubmitcs);
        createpetugas = FirebaseDatabase.getInstance().getReference("User");
        modeluser = new ModelUser();
        submitcp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createPetugas();
            }
        });

    }

    private void createPetugas() {
        String usernamep = modeluser.setUsername(username.getText().toString());
        String namap = modeluser.setNama_petugas(nama.getText().toString().trim());
        String levelp = modeluser.setLevel(level.getText().toString().trim());
        String passwordp = modeluser.setPassword(password.getText().toString());

        String id = createpetugas.push().getKey();
        ModelUser ms = new ModelUser(id, levelp, namap, usernamep, passwordp);
        createpetugas.child(id).setValue(ms).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(ActivityPetugasCreate.this, "Create Petugas Succes", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),ActivityDataPetugas.class));
            }
        });
    }
}
