package com.garudvihang0gmail.studyleague;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText inputString;
    TextView outputString;
//    String string;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        outputString = findViewById(R.id.enterText);
//        outputString.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(MainActivity.this, "Enter Text You Dumbass.", Toast.LENGTH_SHORT).show();
//            }
//        });
    }

//    public void onClickButton(View v) {
//
//        inputString = findViewById(R.id.enterText);
//        string = String.valueOf(inputString.getText());
//
//        if (v.getId() == R.id.submitButton) {
//
//
//            Toast.makeText(this, string, Toast.LENGTH_SHORT).show();
//        }
//    }

    public void onSubmit(View v) {

        inputString = findViewById(R.id.enterText);
        Toast.makeText(MainActivity.this,inputString.getText(),Toast.LENGTH_SHORT).show();

        outputString = findViewById(R.id.output);
        outputString.setText(inputString.getText());
    }
}
