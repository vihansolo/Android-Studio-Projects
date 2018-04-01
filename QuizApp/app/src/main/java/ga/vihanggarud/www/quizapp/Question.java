package ga.vihanggarud.www.quizapp;

class Question {

    private String mQuestion;
    private String mOption1;
    private String mOption2;
    private String mOption3;
    private String mOption4;
    private int mRightAns;

    Question() {

    }

    Question(String mQuestion, String mOption1, String mOption2, String mOption3, String mOption4, int mRightAns) {

        this.mQuestion = mQuestion;
        this.mOption1 = mOption1;
        this.mOption2 = mOption2;
        this.mOption3 = mOption3;
        this.mOption4 = mOption4;
        this.mRightAns = mRightAns;
    }

    String getmQuestion() {

        return mQuestion;
    }

    void setmQuestion(String mQuestion) {

        this.mQuestion = mQuestion;
    }

    String getmOption1() {

        return mOption1;
    }

    void setmOption1(String mOption1) {

        this.mOption1 = mOption1;
    }

    String getmOption2() {

        return mOption2;
    }

    void setmOption2(String mOption2) {

        this.mOption2 = mOption2;
    }

    String getmOption3() {

        return mOption3;
    }

    void setmOption3(String mOption3) {

        this.mOption3 = mOption3;
    }

    String getmOption4() {

        return mOption4;
    }

    void setmOption4(String mOption4) {

        this.mOption4 = mOption4;
    }

    int getmRightAns() {

        return mRightAns;
    }

    void setmRightAns(int mRightAns) {

        this.mRightAns = mRightAns;
    }
}
