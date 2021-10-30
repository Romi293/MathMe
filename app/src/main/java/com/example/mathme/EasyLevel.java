package com.example.mathme;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Random;

public class EasyLevel extends AppCompatActivity implements View.OnClickListener {

    FloatingActionButton exitButton;
    Button falseButton;
    Button trueButton;
    public static int score = 0;
    Random rand = new Random();
    int randNum1;
    int randNum2;
    int randAnswer;
    String numStr;
    String[] opsArr = {"+", "-"};
    String randOp = opsArr[rand.nextInt(2)];
    static String whichLevel;
    static int i=0;
    static int count=0, y=0;


    static TextView scoreText;
    TextView num1Tv;
    TextView num2Tv;
    TextView opTv;
    TextView answerTv;

    TextView countDownText;
    CountDownTimer countDownTimer;
    long timerLeftMillSeconds = 30000;
    boolean timerRunning;

    MediaPlayer correctAnswerSound;
    MediaPlayer wrongAnswerSound;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy_level);
        count= Levels.i;
        whichLevel="bla";
        scoreText = findViewById(R.id.updatedScore);

        trueButton = findViewById(R.id.trueBtn);
        trueButton.setOnClickListener(this);

        falseButton = findViewById(R.id.falseBtn);
        falseButton.setOnClickListener(this);

        scoreText = findViewById(R.id.updatedScore);


        countDownText = findViewById(R.id.timerTextTimeEasy);

        exitButton = findViewById(R.id.exitBtnFromEasy);
        exitButton.setOnClickListener(this);

        num1Tv = findViewById(R.id.num1Easy);
        num2Tv = findViewById(R.id.num2Easy);
        opTv = findViewById(R.id.opEasy);
        answerTv = findViewById(R.id.resultEasy);

        correctAnswerSound = MediaPlayer.create(this, R.raw.correct_answer);
        wrongAnswerSound = MediaPlayer.create(this, R.raw.wrong_answer);

        startTimer();
        generateQuestion();
    }

    private void startTimer() {
        countDownTimer = new CountDownTimer(timerLeftMillSeconds, 1000) {
            @Override
            public void onTick(long l) {
                timerLeftMillSeconds = l;
                updateTimer();
            }

            @Override
            public void onFinish() {

            }
        }.start();

        timerRunning = true;
    }

    public void stopTimer() {
        countDownTimer.cancel();
        timerRunning = false;
        Intent intent = new Intent(this, Result.class);

        startActivity(intent);
    }

    @SuppressLint("ResourceAsColor")
    private void updateTimer() {
        int minutes = (int) timerLeftMillSeconds / 60000;
        int seconds = (int) timerLeftMillSeconds % 60000 / 1000;

        String timeLeftText;

        timeLeftText = "0" + minutes + ":";
        if (seconds < 10) {
            timeLeftText += "0";
            countDownText.setTextColor(Color.parseColor("#E31111"));
        }
        timeLeftText += seconds;

        if(minutes == 0 && seconds == 0)
            stopTimer();

        countDownText.setText(timeLeftText);
    }


    @Override
    public void onClick(View view) {
        if(view == trueButton)
            checkAnswer(true);

        if(view == falseButton)
            checkAnswer(false);

        if (view == exitButton){
            Intent exitIntent = new Intent(this, Home.class);
            startActivity(exitIntent);
        }
    }

    public void generateQuestion() {
        randNum1 = rand.nextInt(5);
        randNum2 = rand.nextInt(5);
        randAnswer = rand.nextInt(7);
        randOp = opsArr[rand.nextInt(2)];

        if (opTv.getText().toString().equals("-")) {
            while (randNum2 > randNum1 || randAnswer > randNum1 || randAnswer > randNum2) {
                randNum1 = rand.nextInt(5);
                randNum2 = rand.nextInt(5);
                randAnswer = rand.nextInt(7);
            }
        }

        numStr = new Integer(randNum1).toString();
        num1Tv.setText(numStr);

        numStr = new Integer(randNum2).toString();
        num2Tv.setText(numStr);

        numStr = new Integer(randAnswer).toString();
        answerTv.setText(numStr);

        opTv.setText(randOp);


    }

    private void checkAnswer(boolean userChoosing) {
        String sumStr;
        switch (randOp) {
            case "+":
                if (randAnswer == randNum1 + randNum2) {
                    if (userChoosing) {
                        correctAnswerSound.start();
                        score += 5;
                        sumStr = new Integer(score).toString(); //converts int to string
                        scoreText.setText(sumStr);
                        Toast.makeText(EasyLevel.this, "Correct!", Toast.LENGTH_SHORT).show();
                        //generateQuestion();
                    }
                    else {
                        wrongAnswerSound.start();
                        Toast.makeText(EasyLevel.this, "Incorrect!", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    if (!userChoosing) {
                        correctAnswerSound.start();
                        score += 5;
                        sumStr = new Integer(score).toString(); //converts int to string
                        scoreText.setText(sumStr);
                        Toast.makeText(EasyLevel.this, "Correct!", Toast.LENGTH_SHORT).show();
                        //generateQuestion();
                    }
                    else {
                        wrongAnswerSound.start();
                        Toast.makeText(EasyLevel.this, "Incorrect!", Toast.LENGTH_SHORT).show();
                        //generateQuestion();
                    }
                }

            case "-":
                if (randAnswer == randNum1 - randNum2) {
                    if (userChoosing) {
                        score += 5;
                        sumStr = new Integer(score).toString(); //converts int to string
                        scoreText.setText(sumStr);
                        Toast.makeText(EasyLevel.this, "Correct!", Toast.LENGTH_SHORT).show();
                        //generateQuestion();
                    }
                    else {
                        Toast.makeText(EasyLevel.this, "Incorrect!", Toast.LENGTH_SHORT).show();
                        break;
                    }
                }
                else {
                    if (!userChoosing) {
                        score += 5;
                        sumStr = new Integer(score).toString(); //converts int to string
                        scoreText.setText(sumStr);
                        Toast.makeText(EasyLevel.this, "Correct!", Toast.LENGTH_SHORT).show();
                        //generateQuestion();
                    }
                    else {
                        Toast.makeText(EasyLevel.this, "Incorrect!", Toast.LENGTH_SHORT).show();
                        break;
                    }
                }
        }
        generateQuestion();
        /*// If answer matches with the clicked button
        if (userChoose == currentQues.answerTrue) {
            score += 5;
            sum = new Integer(score).toString(); //converts int to string
            scoreText.setText(sum);
            toastMessageId = R.string.correct_answer;
            //generateQuestion();
        }
        else {
            toastMessageId = R.string.wrong_answer;
            //generateQuestion();
        }

        Toast.makeText(this, toastMessageId, Toast.LENGTH_SHORT).show();*/
    }


}
