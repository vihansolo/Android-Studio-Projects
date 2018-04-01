package ga.vihanggarud.www.fragmentapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onFrag1Click (View v) {

        Toast.makeText(this, "Fragment 1 clicked", Toast.LENGTH_SHORT).show();
    }

    public void onFrag2Click (View v) {

        Toast.makeText(this, "Fragment 2 clicked", Toast.LENGTH_SHORT).show();
    }
}
