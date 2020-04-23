package com.example.nyoba;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ActivityLogin extends AppCompatActivity {

    private EditText Username, Password;
    private Button Buttonlogin;
    private FirebaseAuth mAuth;
    private DatabaseReference dRef;
    private String string;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Username = (EditText)findViewById(R.id.login);
        Password = (EditText)findViewById(R.id.password);
        Buttonlogin = (Button)findViewById(R.id.buttonlogin);
        mAuth = FirebaseAuth.getInstance();
        dRef = FirebaseDatabase.getInstance().getReference("User");

        Buttonlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = Username.getText().toString().trim();
                String password = Password.getText().toString().trim();
                if (TextUtils.isEmpty(username)){
                    Toast.makeText(ActivityLogin.this, "Enter Username", Toast.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(password)){
                    Toast.makeText(ActivityLogin.this, "Enter Password", Toast.LENGTH_SHORT).show();
                }

                mAuth.signInWithEmailAndPassword(username, password)
                        .addOnCompleteListener(ActivityLogin.this, new OnCompleteListener<AuthResult>() {

                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (!task.isSuccessful()){
                                    Toast.makeText(ActivityLogin.this, "Sign In Failed", Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    FirebaseUser CurrentUser = mAuth.getCurrentUser();
                                    string = CurrentUser.getUid();
                                    dRef.child(string).addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            String userType = dataSnapshot.child("level").getValue().toString();
                                            if (userType.equals("Admin")){
                                                Toast.makeText(ActivityLogin.this, userType, Toast.LENGTH_SHORT).show();
                                                startActivity(new Intent(getApplicationContext(),ActivityHome.class));
                                            }else if (userType.equals("Petugas")){
                                                Toast.makeText(ActivityLogin.this,userType, Toast.LENGTH_SHORT).show();
                                                startActivity(new Intent(getApplicationContext(),ActivityEntry.class));
                                            }else if (userType.equals(null)){
                                                startActivity(new Intent(getApplicationContext(),ActivityEntry.class));
                                            }
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }
                                    });
                                }

                            }
                        });

            }
        });

    }
}
