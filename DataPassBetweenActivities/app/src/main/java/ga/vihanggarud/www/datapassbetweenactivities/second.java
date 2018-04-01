package ga.vihanggarud.www.datapassbetweenactivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class second extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        TextView name = findViewById(R.id.nameOfPerson);
        TextView gender = findViewById(R.id.genderOfPerson);
        TextView age = findViewById(R.id.ageOfPerson);
        Button back = findViewById(R.id.backButton);

        Bundle bundle = getIntent().getExtras();

        assert bundle != null;
        name.setText(bundle.getCharSequence("Name"));
        gender.setText(bundle.getCharSequence("Gender"));
        age.setText(bundle.getCharSequence("Age"));

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(second.this,MainActivity.class));
                finish();
            }
        });
    }
}
