package ga.vihanggarud.www.formactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText getName;
    EditText getRollNo;
    EditText getBranch;
    EditText getDiv;
    EditText getEmailID;

    Button submitButton;

    String name;
    String rollNo;
    String branch;
    String div;
    String emailID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void getDetails (View v) {

        submitButton = findViewById(R.id.submitButton);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getName = findViewById(R.id.enterName);
                name = getName.getText().toString();

                getRollNo = findViewById(R.id.enterRollNo);
                rollNo = getRollNo.getText().toString();

                getBranch = findViewById(R.id.enterBranch);
                branch = getBranch.getText().toString();

                getDiv = findViewById(R.id.enterDiv);
                div = getDiv.getText().toString();

                getEmailID = findViewById(R.id.enterEmailID);
                emailID = getEmailID.getText().toString();

                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(rollNo) || TextUtils.isEmpty(branch) || TextUtils.isEmpty(div) || TextUtils.isEmpty(emailID)) {

                    Toast.makeText(MainActivity.this, "Enter all the details", Toast.LENGTH_SHORT).show();
                    return;
                }

                Bundle bundle = new Bundle();
                bundle.putString("Name",name);
                bundle.putString("RollNo",rollNo);
                bundle.putString("Branch",branch);
                bundle.putString("Div",div);
                bundle.putString("EmailID",emailID);

                Intent intent = new Intent(MainActivity.this,Form.class);
                intent.putExtras(bundle);
                startActivity(intent);

                finish();
            }
        });
    }
}
