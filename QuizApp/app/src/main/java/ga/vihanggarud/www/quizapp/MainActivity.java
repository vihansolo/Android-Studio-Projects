package ga.vihanggarud.www.quizapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static int REQUEST_CODE = 1;
    public static final String PREFS = "shared_prefs";
    public static final String HIGH_SCORE = "high_score";

    private TextView txtHighScore;

    private int mHighScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtHighScore = findViewById(R.id.highScore);
        loadHighScore();
        
        Button startQuiz = findViewById(R.id.start);

        startQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivityForResult(new Intent(MainActivity.this,Ques.class),REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE) {
            
            if(resultCode == RESULT_OK ) {
                
                int score = data.getIntExtra(Ques.FINAL_SCORE,0);
                
                if ( score > mHighScore) {
                    
                    updateScore(score);
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private void updateScore(int score) {

        mHighScore = score;
        txtHighScore.setText("My High Score : " + mHighScore);

        SharedPreferences preferences = getSharedPreferences(PREFS,MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(HIGH_SCORE,mHighScore);
        editor.apply();
    }

    @SuppressLint("SetTextI18n")
    private void loadHighScore() {

        SharedPreferences preferences = getSharedPreferences(PREFS,MODE_PRIVATE);
        mHighScore = preferences.getInt(HIGH_SCORE,0);
        txtHighScore.setText("My High Score : " + mHighScore);
    }
}
