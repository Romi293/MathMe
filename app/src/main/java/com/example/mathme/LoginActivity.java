package com.example.mathme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    FloatingActionButton loginButton;
    static EditText emailId,password, nickname;
    TextView tvSignUp;
    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    static String str1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mFirebaseAuth = FirebaseAuth.getInstance();
        emailId= findViewById(R.id.EmailRegister);
        password = findViewById(R.id.PasswordRegister);
        tvSignUp=findViewById(R.id.NotRegistered);
        loginButton = findViewById(R.id.loginBtn);

        authStateListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser = mFirebaseAuth.getCurrentUser();
                if(!(firebaseUser!= null))
                {
                    Toast.makeText(LoginActivity.this,"you are in!",Toast.LENGTH_SHORT).show();
                    Intent i= new Intent(LoginActivity.this, Home.class);
                    startActivity(i);

                }
                else
                {
                    Toast.makeText(LoginActivity.this,"DRAKARIS!!",Toast.LENGTH_SHORT).show();
                }

            }

        };
        nickname=findViewById(R.id.nickName);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailId.getText().toString();
                String pwd = password.getText().toString();
                if (email.isEmpty()) {
                    emailId.setError("Please Enter Email");
                    emailId.requestFocus();
                } else if (pwd.isEmpty()) {
                    password.setError("Please enter password");
                    password.requestFocus();
                } else if (email.isEmpty() && pwd.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Fields are empty", Toast.LENGTH_SHORT).show();
                } else if (!(email.isEmpty() && pwd.isEmpty())) {
                    mFirebaseAuth.signInWithEmailAndPassword(email,pwd).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful())
                            {
                                Toast.makeText(LoginActivity.this, "We have an error", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                Intent intToHome = new Intent(LoginActivity.this, Home.class);
                                str1=nickname.getText().toString();

                                startActivity(intToHome);
                            }
                        }
                    });

                }
                else
                {
                    Toast.makeText(LoginActivity.this,"Error!",Toast.LENGTH_SHORT).show();
                }
            }
        });
        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intSignUp = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intSignUp);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mFirebaseAuth.addAuthStateListener(authStateListener);
    }
}