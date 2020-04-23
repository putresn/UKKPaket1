package com.example.nyoba;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ActivityEditPetugas extends AppCompatActivity {
    String uuser, upassword, unama, ulevel, IdPetugas, id;
    EditText user, password, nama, level;
    Button updatecp;
    DatabaseReference createpetugas;
    ModelUser modeluser;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_petugas);
        user = (EditText)findViewById(R.id.userpetugasu);
        password = (EditText)findViewById(R.id.passwordpetugasu);
        nama = (EditText)findViewById(R.id.namapetugasu);
        updatecp = (Button)findViewById(R.id.buttonsubmitcsu);
        level = (EditText)findViewById(R.id.levelpetugasu);
        createpetugas = FirebaseDatabase.getInstance().getReference("User");
        modeluser = new ModelUser();

        updatecp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatePetugas();
            }
        });

        uuser = getIntent().getStringExtra("username");
        upassword = getIntent().getStringExtra("password");
        unama = getIntent().getStringExtra("nama_petugas");
        ulevel = getIntent().getStringExtra("level");

        user.setText(uuser);
        password.setText(upassword);
        nama.setText(unama);
        level.setText(ulevel);

    }

    private void updatePetugas() {
        String usernamep = modeluser.setUsername(user.getText().toString());
        String passwordp = modeluser.setPassword(password.getText().toString().trim());
        String namap = modeluser.setNama_petugas(nama.getText().toString().trim());
        String levelp = modeluser.setLevel(level.getText().toString());

        id = IdPetugas;
        ModelUser mu = new ModelUser(id, levelp, namap, usernamep, passwordp);
        createpetugas.child(id).setValue(mu).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(ActivityEditPetugas.this, "Update Petugas Succes", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),ActivityDataPetugas.class));
            }
        });
    }


}
