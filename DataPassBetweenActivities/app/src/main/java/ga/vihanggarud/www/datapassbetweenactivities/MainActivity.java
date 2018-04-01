package ga.vihanggarud.www.datapassbetweenactivities;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText getName;
    EditText getAge;

    RadioGroup getGender;

    Button submitButton;

    String name;
    String age;

    LinearLayout main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        main = findViewById(R.id.main);

        final Button mPopUp = findViewById(R.id.popUpMenu);

        mPopUp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                PopupMenu popupMenu = new PopupMenu(MainActivity.this,mPopUp);
                popupMenu.getMenuInflater().inflate(R.menu.menu_main_pop,popupMenu.getMenu());
                popupMenu.show();

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        switch (item.getItemId()) {

                            case R.id.red :
                                main.setBackgroundColor(Color.RED);
                                return true;

                            case R.id.blue :
                                main.setBackgroundColor(Color.BLUE);
                                return true;

                            case R.id.magenta :
                                main.setBackgroundColor(Color.MAGENTA);
                                return true;
                        }

                        return false;
                    }
                });
            }
        });
    }

    public void getDetails (View v) {

        submitButton = findViewById(R.id.submitButton);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getName = findViewById(R.id.nameOfPerson);
                name = getName.getText().toString();

                getAge = findViewById(R.id.ageOfPerson);
                age = getAge.getText().toString();

                if (TextUtils.isEmpty(name)) {

                    getName.setError("Enter name here...");
                }

                if (TextUtils.isEmpty(age)) {

                    getAge.setError("Enter age here...");
                    return;
                }

                getGender = findViewById(R.id.genderOfPerson);

                Bundle bundle = new Bundle();
                bundle.putString("Name",name);
                bundle.putString("Age",age);

                int gen_id = getGender.getCheckedRadioButtonId();

                if (gen_id == -1) {

                    Toast.makeText(MainActivity.this, "Select Gender", Toast.LENGTH_SHORT).show();
                    return;
                }

                else {

                    RadioButton radioButton = findViewById(gen_id);

                    String genderSelected = radioButton.getText().toString();
                    bundle.putString("Gender",genderSelected);
                }

                Intent intent = new Intent(MainActivity.this,second.class);
                intent.putExtras(bundle);
                startActivity(intent);

                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main_pop,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.red :
                main.setBackgroundColor(Color.RED);

                Toast.makeText(this, "User has selected red color", Toast.LENGTH_SHORT).show();

                return true;

            case R.id.blue :
                main.setBackgroundColor(Color.BLUE);

                Toast.makeText(this, "User has selected blue color", Toast.LENGTH_SHORT).show();

                return true;

            case R.id.magenta :
                main.setBackgroundColor(Color.MAGENTA);

                Toast.makeText(this, "User has selected magenta color", Toast.LENGTH_SHORT).show();

                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
