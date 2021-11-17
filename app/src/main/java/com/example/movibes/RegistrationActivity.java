package com.example.movibes;

import android.content.DialogInterface;
import android.os.Bundle;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.AuthResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

    public class RegistrationActivity extends AppCompatActivity {

        private EditText txtEmail, txtPassword;
        private Button btnCancel;
        private ProgressBar progressbar;
        private FirebaseAuth mAuth;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_register);

            // taking FirebaseAuth instance
            mAuth = FirebaseAuth.getInstance();

            // initialising all views through id defined above
            txtEmail = findViewById(R.id.txtEmail);
            txtPassword = findViewById(R.id.txtPassword);
            Button btnRegister = findViewById(R.id.btnRegister);
            btnCancel = findViewById(R.id.btnCancel);
            progressbar = findViewById(R.id.progressbar);


            // Set on Click Listener on Registration button
            btnRegister.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    registerNewUser();
                }
            });



        }


        private void registerNewUser() {

            // show the visibility of progress bar to show loading
            progressbar.setVisibility(View.VISIBLE);

            // Take the value of two edit texts in Strings
            String email, password;
            email = txtEmail.getText().toString();
            password = txtPassword.getText().toString();


            // Validations for input email and password
            if (TextUtils.isEmpty(email)) {
                Toast.makeText(getApplicationContext(),
                        "Please enter email!!",
                        Toast.LENGTH_LONG)
                        .show();
                return;
            }
            if (TextUtils.isEmpty(password)) {
                Toast.makeText(getApplicationContext(),
                        "Please enter password!!",
                        Toast.LENGTH_LONG)
                        .show();
                return;
            }


            // create new user or register new user
            mAuth
                    .createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(getApplicationContext(),
                                        "Registration successful!",
                                        Toast.LENGTH_LONG)
                                        .show();

                                // hide the progress bar
                                progressbar.setVisibility(View.GONE);

                                // if the user created intent to login activity
                                Intent intent
                                        = new Intent(RegistrationActivity.this,
                                        Introduction.class);
                                startActivity(intent);
                            } else {

                                // Registration failed
                                Toast.makeText(
                                        getApplicationContext(),
                                        "Registration failed!!"
                                                + " Please try again later",
                                        Toast.LENGTH_LONG)
                                        .show();

                                // hide the progress bar
                                progressbar.setVisibility(View.GONE);
                            }
                        }
                    });



            btnCancel.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    new AlertDialog.Builder(RegistrationActivity.this)

                            .setIcon(R.drawable.ic_launcher_background)

                            .setTitle("Alert Dialog Box Title")

                            .setMessage("Are you sure you want cancel?")

                            .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                                    startActivity(intent);
                                }
                            })
                            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    Intent i = new Intent(RegistrationActivity.this, RegistrationActivity.class);
                                }
                            })
                            .setNeutralButton("NO", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent i = new Intent(RegistrationActivity.this, RegistrationActivity.class);
                                }

                            })
                            .show();

                }
            });
        }

    }




