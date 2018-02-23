package noelanthony.com.lostandfoundfinal;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class registerActivity extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth mAuth;
    EditText emailEditText, passwordEditText, confpassEditText, nameEditText;
    Button registerBtn;
    ProgressBar progressbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        emailEditText= (EditText) findViewById(R.id.emailEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);
        confpassEditText = (EditText) findViewById(R.id.confpassEditText);
        registerBtn = (Button) findViewById(R.id.registerBtn);
        progressbar = (ProgressBar) findViewById(R.id.progressbar);
        nameEditText = (EditText) findViewById(R.id.nameEditText);

        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.registerBtn).setOnClickListener(this);
        findViewById(R.id.backtologinTextView).setOnClickListener(this);
    }


    private void registerUser() {

        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();
        String confpass = confpassEditText.getText().toString().trim();

        //check email field if empty
        if(email.isEmpty()) {
            emailEditText.setError("Email is required");
            emailEditText.requestFocus();
            return;
        }

        //check email field if empty
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailEditText.setError("Please enter a valid email");
            emailEditText.requestFocus();
            return;
        }

        if(password.length()<6){
            passwordEditText.setError("Password should be more than 6 characters");
            passwordEditText.requestFocus();
            return;

        }

        //check password field if empty
        if (password.isEmpty()) {
            passwordEditText.setError("Password is required");
            passwordEditText.requestFocus();
            return;
        }
        if(!password.equals(confpass)){
            confpassEditText.setError("Password should match");
            confpassEditText.requestFocus();
            return;
        }
        progressbar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressbar.setVisibility(View.GONE);
                        if (task.isSuccessful()) {
                            finish();
                            startActivity(new Intent(registerActivity.this, newsFeedActivity.class));
                        } else {
                            if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                                Toast.makeText(getApplicationContext(), "You are already registered", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        // ...
                    }
                });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.registerBtn:
                registerUser();
                break;

            case R.id.backtologinTextView:
                finish();
                startActivity(new Intent(this, MainActivity.class));
                break;
    }
    }
}//end of class
