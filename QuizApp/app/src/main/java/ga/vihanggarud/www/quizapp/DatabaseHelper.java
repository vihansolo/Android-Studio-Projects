package ga.vihanggarud.www.quizapp;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import static android.provider.BaseColumns._ID;
import static ga.vihanggarud.www.quizapp.QuizContainer.QuizTable.ANS_COLUMN;
import static ga.vihanggarud.www.quizapp.QuizContainer.QuizTable.OPTION1_COLUMN;
import static ga.vihanggarud.www.quizapp.QuizContainer.QuizTable.OPTION2_COLUMN;
import static ga.vihanggarud.www.quizapp.QuizContainer.QuizTable.OPTION3_COLUMN;
import static ga.vihanggarud.www.quizapp.QuizContainer.QuizTable.OPTION4_COLUMN;
import static ga.vihanggarud.www.quizapp.QuizContainer.QuizTable.QUESTION_COLUMN;
import static ga.vihanggarud.www.quizapp.QuizContainer.QuizTable.QUESTION_TABLE_NAME;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "QuizApp.db";
    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase db;

    DatabaseHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        this.db = db;

        final String QB_TABLE = "CREATE TABLE " +
                QUESTION_TABLE_NAME + " ( " +
                _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QUESTION_COLUMN + " TEXT, " +
                OPTION1_COLUMN + " TEXT, " +
                OPTION2_COLUMN + " TEXT, " +
                OPTION3_COLUMN + " TEXT, " +
                OPTION4_COLUMN + " TEXT, " +
                ANS_COLUMN + " INTEGER " +
                " );";

        db.execSQL(QB_TABLE);

        generateQuestionFunction();
    }

    private void generateQuestionFunction () {

        Question q1 = new Question("The best football player in the world","Gonzalo Higuain","Cristiano Ronaldo","Lionel Messi","Neymar Jr.",2);
        addQuestion(q1);

        Question q2 = new Question("The capital of India","Mumbai","New Delhi","Banglore","Chennai",2);
        addQuestion(q2);

        Question q3 = new Question("Author of Harry Potter","Dan Brown","Arthur Canon Doyle","Agatha Christie","J.K. Rowling",4);
        addQuestion(q3);

        Question q4 = new Question("70th element in the periodic table","Rubidium","Lawrencium","Ytterbium","Strontium",3);
        addQuestion(q4);

        Question q5 = new Question("Current President of India","Pranab Mukherjee","Narenda Modi","Devendra Fadnavis","Ram Nath Kovind",4);
        addQuestion(q5);
    }

    private void addQuestion (Question qb) {

        ContentValues contentValues = new ContentValues();

        contentValues.put(QUESTION_COLUMN, qb.getmQuestion());
        contentValues.put(OPTION1_COLUMN, qb.getmOption1());
        contentValues.put(OPTION2_COLUMN, qb.getmOption2());
        contentValues.put(OPTION3_COLUMN, qb.getmOption3());
        contentValues.put(OPTION4_COLUMN, qb.getmOption4());
        contentValues.put(ANS_COLUMN, qb.getmRightAns());

        db.insert(QUESTION_TABLE_NAME, null, contentValues);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    List<Question> getQuestionSet() {

        List<Question> questionSetsList = new ArrayList<>();

        db = getReadableDatabase();

        @SuppressLint("Recycle")
        Cursor c = db.rawQuery("SELECT * FROM " + QUESTION_TABLE_NAME, null);

        if (c.moveToFirst()) {

            do {
                Question question = new Question();

                question.setmQuestion(c.getString(c.getColumnIndex(QUESTION_COLUMN)));
                question.setmOption1(c.getString(c.getColumnIndex(OPTION1_COLUMN)));
                question.setmOption2(c.getString(c.getColumnIndex(OPTION2_COLUMN)));
                question.setmOption3(c.getString(c.getColumnIndex(OPTION3_COLUMN)));
                question.setmOption4(c.getString(c.getColumnIndex(OPTION4_COLUMN)));
                question.setmRightAns(c.getInt(c.getColumnIndex(ANS_COLUMN)));

                questionSetsList.add(question);
            }

            while (c.moveToNext());
        }

        c.close();

        return questionSetsList;
    }
}
