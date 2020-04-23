package com.example.nyoba;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ActivityLoginSiswa extends AppCompatActivity {

    private EditText Username, Password;
    private Button Buttonlogin;
    private FirebaseAuth mAuth;
    private DatabaseReference dRef;
    private String string;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_siswa);

        Username = (EditText)findViewById(R.id.logins);
        Password = (EditText)findViewById(R.id.passwords);
        Buttonlogin = (Button)findViewById(R.id.buttonlogins);
        mAuth = FirebaseAuth.getInstance();
        dRef = FirebaseDatabase.getInstance().getReference("Siswa");

        Buttonlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String nisn = Username.getText().toString().trim();
                final String notelp = Password.getText().toString().trim();
                if (TextUtils.isEmpty(nisn)){
                    Toast.makeText(ActivityLoginSiswa.this, "Enter Username", Toast.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(notelp)){
                    Toast.makeText(ActivityLoginSiswa.this, "Enter Password", Toast.LENGTH_SHORT).show();
                }

                dRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.child(nisn).exists()){
                            if (!nisn.isEmpty()){
                                ModelSiswa modelsiswa = dataSnapshot.child(nisn).getValue(ModelSiswa.class);
                                Toast.makeText(ActivityLoginSiswa.this, "Apawe", Toast.LENGTH_SHORT).show();
                                if (modelsiswa.getNotelp().equals(notelp)){
                                    Toast.makeText(ActivityLoginSiswa.this, "Login Success", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getApplicationContext(),ActivityHistory.class));
                                }else {
                                    Toast.makeText(ActivityLoginSiswa.this, "Login Failed", Toast.LENGTH_SHORT).show();
                                }
                            }else {
                                Toast.makeText(ActivityLoginSiswa.this, "Login Error", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

    }
}
