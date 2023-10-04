package com.example.katiasproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class Signupactivity extends AppCompatActivity {

    EditText fullNameEditText ;
    EditText emailtxt ;
    EditText passwordEditText;
    EditText repasswordEditText;
    Button signUp;
    TextView errortxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signupactivity);
        fullNameEditText=findViewById(R.id.fullNameEditText);
        emailtxt=findViewById(R.id.emailtxt);
        passwordEditText=findViewById(R.id.passwordtxt);
        repasswordEditText=findViewById(R.id.repasswordEditText);
        signUp =findViewById(R.id.signUp);
        errortxt=findViewById(R.id.errortxt);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewAccount();
            }
        });
    }
    private void createNewAccount(){
        errortxt.setVisibility(View.GONE);
        if(fullNameEditText.getText().toString().equals("")){
            errortxt.setVisibility(View.VISIBLE);
            errortxt.setText("please enter your full name");
            return;
        }
        if(emailtxt.getText().toString().equals("")){
            errortxt.setVisibility(View.VISIBLE);
            errortxt.setText("please enter your email");
            return;
        }
        if(passwordEditText.getText().toString().equals("")){
            errortxt.setVisibility(View.VISIBLE);
            errortxt.setText("please enter your password");
            return;
        }
        if(repasswordEditText.getText().toString().equals("")){
            errortxt.setVisibility(View.VISIBLE);
            errortxt.setText("please confirm your password");
            return;
        }
        if(!repasswordEditText.getText().toString().equals(passwordEditText.getText().toString())){
            errortxt.setVisibility(View.VISIBLE);
            errortxt.setText("password dosn't match");
            return;
        }
        final FirebaseAuth mAuth=FirebaseAuth.getInstance();
        mAuth.createUserWithEmailAndPassword(emailtxt.getText().toString(), passwordEditText.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            FirebaseUser user = mAuth.getCurrentUser();

                            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                    .setDisplayName(fullNameEditText.getText().toString())
                                    .build();

                            user.updateProfile(profileUpdates)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                startActivity(new Intent(Signupactivity.this, MainActivity.class));
                                            }
                                        }
                                    });
                        } else {

                            //Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            errortxt.setVisibility(View.VISIBLE);
                            errortxt.setText(task.getException().getMessage());

                        }
                    }
                });









    }
}