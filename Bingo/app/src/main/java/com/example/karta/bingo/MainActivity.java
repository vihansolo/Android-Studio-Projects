package com.example.karta.bingo;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ArrayList<TextView> bingoGridBoxes;
    ArrayList<TextView> bingoAlphabets;

    Random rand;

    boolean rowCount[];
    boolean columnCount[];
    int bingoCount;

    boolean isForwardDiagonalStrikedOff;
    boolean isBackwardDiagonalStrikedOff;

    boolean bingoGrid[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeGlobalVariables();
        randomizeNumberGrid();
    }

    void initializeGlobalVariables(){
        rand = new Random();

        bingoCount=-1;

        bingoGrid = new boolean[25];

        for(int i=0;i<25;i++){
            bingoGrid[i]=true;
        }

        isForwardDiagonalStrikedOff = false;
        isBackwardDiagonalStrikedOff = false;

        rowCount= new boolean[]{true,true,true,true,true};
        columnCount= new boolean[]{true,true,true,true,true};

        bingoAlphabets = new ArrayList<TextView>();

        bingoAlphabets.add((TextView)findViewById(R.id.b));
        bingoAlphabets.add((TextView)findViewById(R.id.i));
        bingoAlphabets.add((TextView)findViewById(R.id.n));
        bingoAlphabets.add((TextView)findViewById(R.id.g));
        bingoAlphabets.add((TextView)findViewById(R.id.o));

        bingoGridBoxes = new ArrayList<TextView>();

        bingoGridBoxes.add((TextView)findViewById(R.id.c00));
        bingoGridBoxes.add((TextView)findViewById(R.id.c01));
        bingoGridBoxes.add((TextView)findViewById(R.id.c02));
        bingoGridBoxes.add((TextView)findViewById(R.id.c03));
        bingoGridBoxes.add((TextView)findViewById(R.id.c04));
        bingoGridBoxes.add((TextView)findViewById(R.id.c10));
        bingoGridBoxes.add((TextView)findViewById(R.id.c11));
        bingoGridBoxes.add((TextView)findViewById(R.id.c12));
        bingoGridBoxes.add((TextView)findViewById(R.id.c13));
        bingoGridBoxes.add((TextView)findViewById(R.id.c14));
        bingoGridBoxes.add((TextView)findViewById(R.id.c20));
        bingoGridBoxes.add((TextView)findViewById(R.id.c21));
        bingoGridBoxes.add((TextView)findViewById(R.id.c22));
        bingoGridBoxes.add((TextView)findViewById(R.id.c23));
        bingoGridBoxes.add((TextView)findViewById(R.id.c24));
        bingoGridBoxes.add((TextView)findViewById(R.id.c30));
        bingoGridBoxes.add((TextView)findViewById(R.id.c31));
        bingoGridBoxes.add((TextView)findViewById(R.id.c32));
        bingoGridBoxes.add((TextView)findViewById(R.id.c33));
        bingoGridBoxes.add((TextView)findViewById(R.id.c34));
        bingoGridBoxes.add((TextView)findViewById(R.id.c40));
        bingoGridBoxes.add((TextView)findViewById(R.id.c41));
        bingoGridBoxes.add((TextView)findViewById(R.id.c42));
        bingoGridBoxes.add((TextView)findViewById(R.id.c43));
        bingoGridBoxes.add((TextView)findViewById(R.id.c44));
    }

    void strikeForwardDiagonal(){
        for(int i = 4 ; i <= 20 ; i+=4)
            bingoGridBoxes.get(i).setTextColor(Color.BLACK);

        isForwardDiagonalStrikedOff=true;
    }

    void strikeBackwardDiagonal(){
        for(int i = 0 ; i <= 24 ; i+=6)
            bingoGridBoxes.get(i).setTextColor(Color.BLACK);

        isBackwardDiagonalStrikedOff=true;
    }

    void strikeRow(int i){
        for(int j = i*5 ; j < i*5+5 ; j++)
            bingoGridBoxes.get(j).setTextColor(Color.BLACK);
    }

    void strikeColumn(int i){
        for(int j = i ; j <= 4*5+i ; j+=5)
            bingoGridBoxes.get(j).setTextColor(Color.BLACK);
    }

    void checkIfBingo(){
        if(bingoCount<4)
            bingoCount++;

        bingoAlphabets.get(bingoCount).setTextColor(Color.RED);

        if(bingoCount==4){
            Toast.makeText(this, "You win", Toast.LENGTH_LONG).show();

            bingoGrid = new boolean[25];

            for(int i=0;i<25;i++){
                bingoGrid[i]=false;
            }
        }
    }

    boolean isForwardDiagonalComplete(){
        for(int i = 4 ; i <= 20 ; i+=4){
            if(bingoGrid[i]==true)
                return false;
        }

        return true;
    }

    boolean isBackwardDiagonalComplete(){
        for(int i = 0 ; i <= 24 ; i+=6){
            if(bingoGrid[i]==true)
                return false;
        }

        return true;
    }

    void checkIfAnyBingoConditionIsSatified(){
        if(isForwardDiagonalComplete()&&isForwardDiagonalStrikedOff==false){
            checkIfBingo();
            strikeForwardDiagonal();
        }
        if(isBackwardDiagonalComplete()&&isBackwardDiagonalStrikedOff==false){
            checkIfBingo();
            strikeBackwardDiagonal();
        }

        int j;

        for(int i=0; i<5; i++){
            for(j=0 ; j<5; j++){
                if(bingoGrid[i*5+j]==true){
                    break;
                }
            }
            if(j==5&&rowCount[i]==true){
                rowCount[i]=false;
                checkIfBingo();
                strikeRow(i);
            }
        }

        for(int i=0; i<5; i++){
            for(j=0 ; j<5; j++){
                if(bingoGrid[j*5+i]==true){
                    break;
                }
            }
            if(j==5&&columnCount[i]==true){
                columnCount[i]=false;
                checkIfBingo();
                strikeColumn(i);
            }
        }
    }

    void onBoxClicked(View view){
        int tag = Integer.parseInt(view.getTag().toString());

        if(bingoGrid[tag]==true){
            bingoGrid[tag] = false;

            view.setBackgroundResource(R.drawable.rectblack);
            ((TextView)view).setTextColor(Color.WHITE);

            checkIfAnyBingoConditionIsSatified();

            Toast.makeText(this, bingoCount+"", Toast.LENGTH_SHORT).show();
        }
    }

    int[] generateRandNums(){
        boolean []nums = new boolean[26];
        nums[0] = true;

        for(int i=1;i<=25;i++){
            nums[i]=false;
        }

        int randNums[] = new int[25];

        for(int i=0;i<25;i++){
            int randNum = rand.nextInt(26);

            while(true){
                int j;

                for(j =0 ; j<25 ; j++){
                    if(nums[randNum])
                        break;
                }

                if(j==25) {
                    nums[randNum]=true;
                    break;
                }

                randNum=rand.nextInt(26);
            }

            randNums[i]=randNum;
        }

        return randNums;
    }

    void randomizeNumberGrid(){
        int []randNums = generateRandNums();

        for(int  i = 0 ; i < 25 ; i ++){
            bingoGridBoxes.get(i).setText(String.valueOf(randNums[i]));
            bingoGridBoxes.get(i).setBackgroundResource(R.drawable.rect);
            bingoGridBoxes.get(i).setTextColor(Color.BLACK);
        }
    }
}