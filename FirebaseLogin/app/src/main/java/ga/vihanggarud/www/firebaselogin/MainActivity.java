package ga.vihanggarud.www.firebaselogin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
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

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    EditText mEmailID;
    EditText mPassword;
    Button mRegister;
    FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEmailID = findViewById(R.id.emailID);
        mPassword = findViewById(R.id.password);
        mRegister = findViewById(R.id.registerButton);
        mFirebaseAuth = FirebaseAuth.getInstance();

        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,RegisterActivity.class));
            }
        });
    }

    public void Login (View v) {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait...");
        progressDialog.show();

        (mFirebaseAuth.signInWithEmailAndPassword(mEmailID.getText().toString(),mPassword.getText().toString())).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                progressDialog.dismiss();

                if (task.isSuccessful()) {

                    Intent intent = new Intent(MainActivity.this,NextPage.class);
                    intent.putExtra("Email", Objects.requireNonNull(mFirebaseAuth.getCurrentUser()).getEmail());
                    startActivity(intent);
                    finish();
                }

                else
                    Toast.makeText(MainActivity.this, "Incorrect Email ID or Password", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
