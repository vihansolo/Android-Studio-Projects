package ga.vihanggarud.www.loginwithattempts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText username;
    EditText password;
    String user,pass;
    int attemptCounter = 6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onSubmitClick (View view) {

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        user = username.getText().toString();
        pass = password.getText().toString();

        if (attemptCounter <= 1)
            Toast.makeText(this, "Maximum Login Attempts Reached", Toast.LENGTH_SHORT).show();

        else if (!user.equals("vihang") || !pass.equals("123456")) {

            attemptCounter--;
            Toast.makeText(this, "Invalid Login \nRemaining Attempts = " + attemptCounter, Toast.LENGTH_SHORT).show();
        }

        else {

            Intent intent = new Intent(MainActivity.this, OnLogin.class);
            startActivity(intent);

            attemptCounter = 6;
        }
    }
}
