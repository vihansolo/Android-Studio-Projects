package ga.vihanggarud.www.quizapp;

import android.provider.BaseColumns;

final class QuizContainer {

    private QuizContainer() {

    }

    static class QuizTable implements BaseColumns {

        static final String QUESTION_TABLE_NAME = "quizQuestion";
        static final String QUESTION_COLUMN = "question";
        static final String OPTION1_COLUMN = "option1";
        static final String OPTION2_COLUMN = "option2";
        static final String OPTION3_COLUMN = "option3";
        static final String OPTION4_COLUMN = "option4";
        static final String ANS_COLUMN = "answer";
    }
}
