package ga.vihanggarud.www.quizapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class Ques extends AppCompatActivity {

    public static final String FINAL_SCORE = "FinalScore";
    private static final long COUNTDOWN_TIMER = 20000;

    private TextView txtQuestion;
    private TextView txtScore;
    private TextView txtQuestionCount;
    private TextView txtCounter;
    private RadioGroup radioGroup;
    private RadioButton r1;
    private RadioButton r2;
    private RadioButton r3;
    private RadioButton r4;
    private Button mSubmit;

    private ColorStateList colorStateList;
    private ColorStateList colorStateListCountDown;
    private CountDownTimer countDownTimer;

    private long timeLeft;

    private List<Question> questionSetsList;

    private int qCounter;
    private Question currQuestion;
    private int qCountTotal;

    private int score;
    private boolean ans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ques);

        txtQuestion = findViewById(R.id.ques);
        txtScore =  findViewById(R.id.score);
        txtQuestionCount = findViewById(R.id.quesNo);
        txtCounter = findViewById(R.id.timer);
        radioGroup = findViewById(R.id.options);
        r1 = findViewById(R.id.option1);
        r2 = findViewById(R.id.option2);
        r3 = findViewById(R.id.option3);
        r4 = findViewById(R.id.option4);
        mSubmit = findViewById(R.id.submit);


        colorStateList = r1.getTextColors();
        colorStateListCountDown = txtCounter.getTextColors();

        DatabaseHelper questionDb = new DatabaseHelper(this);
        questionSetsList = questionDb.getQuestionSet();

        qCountTotal = questionSetsList.size();

        Collections.shuffle(questionSetsList);

        showQuestion();

        mSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (!ans) {

                    if (r1.isChecked() || r2.isChecked() || r3.isChecked() || r4.isChecked()) {

                        check();
                    }

                    else {

                        Toast.makeText(Ques.this, "Select an answer first", Toast.LENGTH_SHORT).show();
                    }
                }

                else {

                    showQuestion();
                }
            }
        });

    }

    @SuppressLint("SetTextI18n")
    private void showQuestion() {

        r1.setTextColor(colorStateList);
        r2.setTextColor(colorStateList);
        r3.setTextColor(colorStateList);
        r4.setTextColor(colorStateList);

        radioGroup.clearCheck();

        if (qCounter < qCountTotal ) {

            currQuestion = questionSetsList.get(qCounter);
            txtQuestion.setText(currQuestion.getmQuestion());

            r1.setText(currQuestion.getmOption1());
            r2.setText(currQuestion.getmOption2());
            r3.setText(currQuestion.getmOption3());
            r4.setText(currQuestion.getmOption4());

            qCounter++;

            txtQuestionCount.setText("Question: "+qCounter+" / "+qCountTotal);

            ans = false;

            mSubmit.setText("Confirm");

            timeLeft = COUNTDOWN_TIMER;
            startCountDown();
        }

        else {

            finishQuizActivity();
        }
    }

    private void startCountDown() {

        countDownTimer = new CountDownTimer(timeLeft,1000) {

            @Override
            public void onTick(long millisUntilFinished) {

                timeLeft = millisUntilFinished;
                updateCountDown();
            }

            @Override
            public void onFinish() {

                timeLeft = 0;
                updateCountDown();
                check();
            }
        }.start();
    }

    private void updateCountDown() {

        int min = (int) (timeLeft/1000) / 60 ;
        int sec =  (int) (timeLeft/1000) % 60;

        String timeFormat = String.format(Locale.getDefault(),"%02d:%02d",min,sec);
        txtCounter.setText(timeFormat);

        if (timeLeft < 10000) {

            txtCounter.setTextColor(Color.RED);
        }
        else {

            txtCounter.setTextColor(colorStateListCountDown);
        }
    }


    @SuppressLint("SetTextI18n")
    private void check() {

        ans =  true;

        countDownTimer.cancel();

        RadioButton radioSelected = findViewById(radioGroup.getCheckedRadioButtonId());
        int answer = radioGroup.indexOfChild(radioSelected) + 1;

        if(answer == currQuestion.getmRightAns())
        {
            score++;
            txtScore.setText("Score : "+score);
        }

        showRightAns();
    }

    @SuppressLint("SetTextI18n")
    private void showRightAns() {

        r1.setTextColor(Color.RED);
        r2.setTextColor(Color.RED);
        r3.setTextColor(Color.RED);
        r4.setTextColor(Color.RED);

        switch (currQuestion.getmRightAns()) {

            case 1 :
                r1.setTextColor(Color.GREEN);
                txtQuestion.setText("Answer 1 is Correct");
                break;

            case 2 :
                r2.setTextColor(Color.GREEN);
                txtQuestion.setText("Answer 2 is Correct");
                break;

            case 3 :
                r3.setTextColor(Color.GREEN);
                txtQuestion.setText("Answer 3 is Correct");
                break;

            case 4 :
                r4.setTextColor(Color.GREEN);
                txtQuestion.setText("Answer 4 is Correct");
                break;
        }

        if (qCounter < qCountTotal )  {

            mSubmit.setText("Next");
        }

        else {

            mSubmit.setText("Finish");
        }
    }


    private void finishQuizActivity() {

        Intent rIntent = new Intent();
        rIntent.putExtra(FINAL_SCORE,score);
        setResult(RESULT_OK,rIntent);
        finish();
    }

    private long onBackPressedTime;

    @Override
    public void onBackPressed() {

        if (onBackPressedTime + 2000 > System.currentTimeMillis() ) {

            finishQuizActivity();
        }

        else {

            Toast.makeText(Ques.this,"Press Back Again",Toast.LENGTH_SHORT).show();
        }

        onBackPressedTime = System.currentTimeMillis();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {

            countDownTimer.cancel();
        }
    }
}
