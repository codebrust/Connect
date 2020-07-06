package com.kisanthapa.connect;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.kisanthapa.connect.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;

public class SigninActivity extends AppCompatActivity {
    private Button mRegBtn;
    private TextInputLayout mLoginEmail;
    private TextInputLayout mLoginPassword;
    private Button mSignInBtn;

    private FirebaseAuth mAuth;

    Context context;

    private DatabaseReference mUserDatabase;

    //Progress Dialog
    ProgressDialog mStartProgressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        context = this;

        mRegBtn = (Button)findViewById(R.id.start_reg_btn);
        mLoginEmail = (TextInputLayout)findViewById(R.id.start_email);
        mLoginPassword = (TextInputLayout)findViewById(R.id.start_password);
        mSignInBtn = (Button)findViewById(R.id.start_Signin_btn);

        mAuth = FirebaseAuth.getInstance();
        mUserDatabase = FirebaseDatabase.getInstance().getReference().child("Users");

        //For progress Dailog
        mStartProgressDialog = new ProgressDialog(this);
        mSignInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = mLoginEmail.getEditText().getText().toString();
                String password = mLoginPassword.getEditText().getText().toString();

                if (!TextUtils.isEmpty(email) || !TextUtils.isEmpty(password)){

                    loginUser(email, password);

                    //Initializing progress dailog
                    mStartProgressDialog.setTitle("Logging In");
                    mStartProgressDialog.setMessage("Please Wait");
                    mStartProgressDialog.setCanceledOnTouchOutside(false);
                    mStartProgressDialog.show();
                }
            }
        });
        mRegBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"Register Activity",Toast.LENGTH_SHORT).show();
                Intent register_Intent = new Intent(context, RegisterActivity.class);
                startActivity(register_Intent);
            }
        });
    }
    private void loginUser(String email, String password) {

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){

                    mStartProgressDialog.dismiss();

                    String current_user_id = mAuth.getCurrentUser().getUid();
                    String deviceToken = FirebaseInstanceId.getInstance().getToken(); //getting token id of an user

                    mUserDatabase.child(current_user_id).child("device_token").setValue(deviceToken).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            Intent mainIntent = new Intent(SigninActivity.this, MainActivity.class);
                            //preventing returning to the Login Activity after logging in
                            mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(mainIntent);
                            finish();

                        }
                    });


                }else {
                    mStartProgressDialog.hide();
                    Toast.makeText(SigninActivity.this, "Invalid User!!!", Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}
