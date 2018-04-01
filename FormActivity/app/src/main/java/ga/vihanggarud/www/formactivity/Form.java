package ga.vihanggarud.www.formactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Form extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        TextView name = findViewById(R.id.name);
        TextView rollNo = findViewById(R.id.rollNo);
        TextView branch = findViewById(R.id.branch);
        TextView div = findViewById(R.id.div);
        TextView emailID = findViewById(R.id.emailID);

        Button back = findViewById(R.id.backButton);

        Bundle bundle = getIntent().getExtras();

        assert bundle != null;
        name.setText(bundle.getCharSequence("Name"));
        rollNo.setText(bundle.getCharSequence("RollNo"));
        branch.setText(bundle.getCharSequence("Branch"));
        div.setText(bundle.getCharSequence("Div"));
        emailID.setText(bundle.getCharSequence("EmailID"));

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Form.this,MainActivity.class));
                finish();
            }
        });
    }
}
