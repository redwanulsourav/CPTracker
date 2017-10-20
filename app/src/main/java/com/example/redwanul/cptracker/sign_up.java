package com.example.redwanul.cptracker;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class sign_up extends AppCompatActivity {
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        Button button = (Button) findViewById(R.id.button4);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText etFirstName = (EditText) findViewById(R.id.editText6);
                EditText etLastName = (EditText)  findViewById(R.id.editText8);
                EditText etPassword = (EditText)  findViewById(R.id.editText10);
                EditText etPassword2 = (EditText) findViewById(R.id.editText11);
                EditText etEmail = (EditText) findViewById(R.id.editText9);
                EditText etCodeforces = (EditText) findViewById(R.id.editText13);
                EditText etUva = (EditText) findViewById( R.id.editText14);

                String firstName = etFirstName.getText().toString();
                String lastName = etLastName.getText().toString();
                String password = etPassword.getText().toString();
                String confirmedPassword = etPassword2.getText().toString();
                String email            = etEmail.getText().toString();
                String codeforcesHandle = etCodeforces.getText().toString();
                String uvaUsername =    etUva.getText().toString();

                if( (firstName == null || firstName.isEmpty()) ||
                        (lastName == null || lastName.isEmpty()) ||
                        (email == null || email.isEmpty()) ||
                        (password == null || password.isEmpty()) ||
                        (confirmedPassword == null || confirmedPassword.isEmpty())){

                        CharSequence text = "Required fields left empty";
                        Toast.makeText(getApplicationContext(),text,Toast.LENGTH_SHORT).show();
                }
                else {
                    if (!password.equals(confirmedPassword)) {
                        CharSequence text = "Passwords do not match";
                        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
                    } else {
                        mAuth = FirebaseAuth.getInstance();
                        mAuth.createUserWithEmailAndPassword(email, password)
                                .addOnCompleteListener(sign_up.this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {

                                        // If sign in fails, display a message to the user. If sign in succeeds
                                        // the auth state listener will be notified and logic to handle the
                                        // signed in user can be handled in the listener.
                                        if (!task.isSuccessful()) {
                                            Toast.makeText(sign_up.this, "Creating account failed",
                                                    Toast.LENGTH_SHORT).show();
                                        }
                                        else{
                                            Toast.makeText(sign_up.this, "Creating account success",
                                                    Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(sign_up.this,home.class);
                                            startActivity(intent);
                                        }

                                        // ...
                                    }
                                });
                    }
                }

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
