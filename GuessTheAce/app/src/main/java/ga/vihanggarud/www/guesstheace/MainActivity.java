package ga.vihanggarud.www.guesstheace;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ImageView leftCard,middleCard,rightCard;
    TextView highScore;
    Button newGame;

    List<Integer> cards;
    int countScore = 0;
    int finalHighScore = 0;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        leftCard = findViewById(R.id.leftCard);
        middleCard = findViewById(R.id.middleCard);
        rightCard = findViewById(R.id.rightCard);

        highScore = findViewById(R.id.highScore);
        highScore.setText("High Score : 0");

        newGame = findViewById(R.id.newGame);

        cards = new ArrayList<>();

        cards.add(101); // Ace of Spades
        cards.add(102); // Two of Spades
        cards.add(103); // Three of Spades


        newGame.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Collections.shuffle(cards);

                leftCard.setImageResource(R.drawable.playing_card_back);
                middleCard.setImageResource(R.drawable.playing_card_back);
                rightCard.setImageResource(R.drawable.playing_card_back);

                Animation leftAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.left);
                Animation middleAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.middle);
                Animation rightAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.right);

                leftCard.startAnimation(leftAnimation);
                middleCard.startAnimation(middleAnimation);
                rightCard.startAnimation(rightAnimation);

                leftCard.setClickable(true);
                middleCard.setClickable(true);
                rightCard.setClickable(true);

                if (finalHighScore < countScore) {

                    finalHighScore = countScore;
                    setHighScore();
                }
            }
        });

        //Shuffle Cards
        Collections.shuffle(cards);

        leftCard.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (cards.get(0) == 101) {

                    leftCard.setImageResource(R.drawable.playing_card_ace);
                    Toast.makeText(MainActivity.this, "You Guessed The ACE", Toast.LENGTH_SHORT).show();

                    countScore++;
                }

                else {

                    if (cards.get(0) == 102)
                        leftCard.setImageResource(R.drawable.playing_card_2);

                    else if (cards.get(0) == 103)
                        leftCard.setImageResource(R.drawable.playing_card_3);

                    if (cards.get(1) == 101)
                        middleCard.setImageResource(R.drawable.playing_card_ace);

                    else if (cards.get(1) == 102)
                        middleCard.setImageResource(R.drawable.playing_card_2);

                    else if (cards.get(1) == 103)
                        middleCard.setImageResource(R.drawable.playing_card_3);

                    if (cards.get(2) == 101)
                        rightCard.setImageResource(R.drawable.playing_card_ace);

                    else if (cards.get(2) == 102)
                        rightCard.setImageResource(R.drawable.playing_card_2);

                    else if (cards.get(2) == 103)
                        rightCard.setImageResource(R.drawable.playing_card_3);

                    setHighScore();
                }

                leftCard.setClickable(false);
                middleCard.setClickable(false);
                rightCard.setClickable(false);
            }
        });

        middleCard.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (cards.get(1) == 101) {

                    middleCard.setImageResource(R.drawable.playing_card_ace);
                    Toast.makeText(MainActivity.this, "You Guessed The ACE", Toast.LENGTH_SHORT).show();

                    countScore++;
                }

                else {

                    if (cards.get(1) == 102)
                        middleCard.setImageResource(R.drawable.playing_card_2);

                    else if (cards.get(1) == 103)
                        middleCard.setImageResource(R.drawable.playing_card_3);

                    if (cards.get(0) == 101)
                        leftCard.setImageResource(R.drawable.playing_card_ace);

                    else if (cards.get(0) == 102)
                        leftCard.setImageResource(R.drawable.playing_card_2);

                    else if (cards.get(0) == 103)
                        leftCard.setImageResource(R.drawable.playing_card_3);

                    if (cards.get(2) == 101)
                        rightCard.setImageResource(R.drawable.playing_card_ace);

                    else if (cards.get(2) == 102)
                        rightCard.setImageResource(R.drawable.playing_card_2);

                    else if (cards.get(2) == 103)
                        rightCard.setImageResource(R.drawable.playing_card_3);

                    setHighScore();
                }

                leftCard.setClickable(false);
                middleCard.setClickable(false);
                rightCard.setClickable(false);
            }
        });

        rightCard.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (cards.get(2) == 101) {

                    rightCard.setImageResource(R.drawable.playing_card_ace);
                    Toast.makeText(MainActivity.this, "You Guessed The ACE", Toast.LENGTH_SHORT).show();

                    countScore++;
                }

                else {

                    if (cards.get(2) == 102)
                        rightCard.setImageResource(R.drawable.playing_card_2);

                    else if (cards.get(2) == 103)
                        rightCard.setImageResource(R.drawable.playing_card_3);

                    if (cards.get(0) == 101)
                        leftCard.setImageResource(R.drawable.playing_card_ace);

                    else if (cards.get(0) == 102)
                        leftCard.setImageResource(R.drawable.playing_card_2);

                    else if (cards.get(0) == 103)
                        leftCard.setImageResource(R.drawable.playing_card_3);

                    if (cards.get(1) == 101)
                        middleCard.setImageResource(R.drawable.playing_card_ace);

                    else if (cards.get(1) == 102)
                        middleCard.setImageResource(R.drawable.playing_card_2);

                    else if (cards.get(1) == 103)
                        middleCard.setImageResource(R.drawable.playing_card_3);

                    setHighScore();
                }

                leftCard.setClickable(false);
                middleCard.setClickable(false);
                rightCard.setClickable(false);
            }
        });
    }

    @SuppressLint("SetTextI18n")
    public void setHighScore () {

        highScore = findViewById(R.id.highScore);
        highScore.setText("High Score : " + finalHighScore);

        countScore = 0;
    }
}
