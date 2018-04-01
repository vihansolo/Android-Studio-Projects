package ga.vihanggarud.www.calculatorapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText getNum1;
    EditText getNum2;
    TextView outputString;
    String s1,s2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onAddButtonClick(View v) {

        getNum1 = findViewById(R.id.enterNumber1);
        getNum2 = findViewById(R.id.enterNumber2);

        s1 = getNum1.getText().toString();
        s2 = getNum2.getText().toString();

        if((s1.isEmpty()) || (s2.isEmpty()))
            Toast.makeText(this, "Add all inputs", Toast.LENGTH_SHORT).show();

        else {

            outputString = findViewById(R.id.output);
            outputString.setText(String.format("%s", Double.parseDouble(s1) + Double.parseDouble(s2)));
        }
    }

    public void onSubButtonClick(View v) {

        getNum1 = findViewById(R.id.enterNumber1);
        getNum2 = findViewById(R.id.enterNumber2);

        s1 = getNum1.getText().toString();
        s2 = getNum2.getText().toString();

        if((s1.isEmpty()) || (s2.isEmpty()))
            Toast.makeText(this, "Add all inputs", Toast.LENGTH_SHORT).show();

        else {

            outputString = findViewById(R.id.output);
            outputString.setText(String.format("%s", Double.parseDouble(s1) - Double.parseDouble(s2)));
        }
    }

    public void onMulButtonClick(View v) {

        getNum1 = findViewById(R.id.enterNumber1);
        getNum2 = findViewById(R.id.enterNumber2);

        s1 = getNum1.getText().toString();
        s2 = getNum2.getText().toString();

        if((s1.isEmpty()) || (s2.isEmpty()))
            Toast.makeText(this, "Add all inputs", Toast.LENGTH_SHORT).show();

        else {

            outputString = findViewById(R.id.output);
            outputString.setText(String.format("%s", Double.parseDouble(s1) * Double.parseDouble(s2)));
        }
    }

    public void onDivButtonClick(View v) {

        getNum1 = findViewById(R.id.enterNumber1);
        getNum2 = findViewById(R.id.enterNumber2);

        s1 = getNum1.getText().toString();
        s2 = getNum2.getText().toString();

        if((s1.isEmpty()) || (s2.isEmpty()))
            Toast.makeText(this, "Add all inputs", Toast.LENGTH_SHORT).show();

        else {

            outputString = findViewById(R.id.output);
            outputString.setText(String.format("%s", Double.parseDouble(s1) / Double.parseDouble(s2)));
        }
    }

    public void onClearButtonClick(View v) {

        outputString = findViewById(R.id.output);
        outputString.setText("");

        getNum1.setText("");
        getNum2.setText("");
    }
}
