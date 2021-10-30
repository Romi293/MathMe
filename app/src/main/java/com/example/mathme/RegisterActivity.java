package com.example.mathme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

public class RegisterActivity extends AppCompatActivity {

    FloatingActionButton registerButton;
    EditText emailId,password,nickname;
    TextView tvSignIn;
    FirebaseAuth mFirebaseAuth;
    static String str1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registerButton = findViewById(R.id.registerBtn);
        mFirebaseAuth = FirebaseAuth.getInstance();
        emailId= findViewById(R.id.EmailRegister);
        password = findViewById(R.id.PasswordRegister);
        nickname=findViewById(R.id.NickNameRegister);
        tvSignIn=findViewById(R.id.AlredayHaveAnAccount);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailId.getText().toString();// user enter email and the system copy it
                String pwd = password.getText().toString();//user enter password and the system copy it
                if (email.isEmpty() && pwd.isEmpty()) // case to email and password
                {
                    Toast.makeText(RegisterActivity.this, "Fields are empty", Toast.LENGTH_SHORT).show();
                    emailId.setError("Please enter your email");
                    emailId.requestFocus();
                }
                else if (pwd.isEmpty()) //case to password
                {
                    password.setError("Please enter password");
                    password.requestFocus();
                }
                else if (email.isEmpty()) // case of email
                {
                    emailId.setError("Please enter your email");
                    emailId.requestFocus();

                }
                else if (!(email.isEmpty() && pwd.isEmpty())) {
                    mFirebaseAuth.createUserWithEmailAndPassword(email, pwd).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(RegisterActivity.this, "User is already exists or error", Toast.LENGTH_SHORT).show();

                            } else {
                                startActivity(new Intent(RegisterActivity.this, Home.class));

                                str1=nickname.getText().toString();

                            }
                        }
                    });
                }
                else
                {
                    Toast.makeText(RegisterActivity.this,"Error,goddamm!",Toast.LENGTH_SHORT).show();
                }
            }
        });
        tvSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent screen = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(screen);

            }
        });


    }


    private void showChangeLanguageDialog() {
        final String[] listItems = {"Hebrew", "English"};
    }
}