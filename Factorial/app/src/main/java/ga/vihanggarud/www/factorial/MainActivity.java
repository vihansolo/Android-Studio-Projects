package ga.vihanggarud.www.factorial;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText mGetNumber;
    Button mFactorial;
    TextView mSetFactorial;

    long number,fact=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mGetNumber = findViewById(R.id.enterNumber);
        mFactorial = findViewById(R.id.factorialButton);
        mSetFactorial = findViewById(R.id.setFactorial);

        mFactorial.setOnClickListener(new View.OnClickListener() {

            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {

                number = Integer.parseInt(mGetNumber.getText().toString());

                while(number > 0) {

                    fact = fact * number;
                    number--;
                }

                mSetFactorial.setText(fact+"");
                fact = 1;
            }
        });
    }
}
