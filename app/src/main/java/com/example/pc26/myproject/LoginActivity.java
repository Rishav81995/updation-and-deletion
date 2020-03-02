package com.example.pc26.myproject;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity
{
    EditText e1, e2;
    Button b1;
    TextView t1, t2,t3;
    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener  mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("User Registration");
        e1 = (EditText) findViewById(R.id.et1);
        e2 = (EditText) findViewById(R.id.et2);
        t1 = (TextView) findViewById(R.id.tv1);
        t2 = (TextView) findViewById(R.id.tv2);
        t3 = (TextView) findViewById(R.id.tv3);
        b1 = (Button) findViewById(R.id.btn1);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mfirebaseuser = mFirebaseAuth .getCurrentUser();
                if (mfirebaseuser != null) {
                    Toast.makeText(LoginActivity.this, "You Are Logged In", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(i);
                    finish();
                }
                else
                    {
                    Toast.makeText(LoginActivity.this, "Please LogIn", Toast.LENGTH_SHORT).show();
                }
            }
        };
        b1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String a = e1.getText().toString();
                String b = e2.getText().toString();
               // String c = e5.getText().toString();
                if (a.isEmpty())
                {
                    e1.setError("Please enter user name");
                    e1.requestFocus();
                }
                else if (b.isEmpty())
                {
                    e2.setError("Please enter password ");
                    e2.requestFocus();
                }

                else if (a.isEmpty() && b.isEmpty())
                {
                    Toast.makeText(LoginActivity.this, "Feilds are Empty", Toast.LENGTH_SHORT).show();
                } else if (!a.isEmpty() && !b.isEmpty())
                {
                    mFirebaseAuth.signInWithEmailAndPassword(a,b).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>()
                    {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task)
                        {
                            if(task.isSuccessful() )
                            {
                                Intent i = new Intent(LoginActivity.this,HomeActivity.class);
                                startActivity(i);
                                finish();
                           }
                            else
                            {
                                Toast.makeText(LoginActivity.this, "LogIn Error ,Pleaae LogIn Again ", Toast.LENGTH_SHORT).show();

                            }

                        }
                    });
                } else {
                    Toast.makeText(LoginActivity.this, "Error Occured!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        t3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }
}