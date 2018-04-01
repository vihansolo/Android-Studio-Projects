package ga.vihanggarud.www.loginactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText enterPassword;
    EditText enterUsername;
    String Pass;
    String User;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onSubmit(View v) {

        enterUsername = findViewById(R.id.username);
        enterPassword = findViewById(R.id.password);
        Pass = enterPassword.getText().toString();
        User = enterUsername.getText().toString();

        if (Pass.equals("123456") && User.equals("vihang")) {

            Intent intent = new Intent(this,OnLogin.class);
            startActivity(intent);
        }

        else
            Toast.makeText(this, "Enter correct username or password", Toast.LENGTH_SHORT).show();
    }
}
