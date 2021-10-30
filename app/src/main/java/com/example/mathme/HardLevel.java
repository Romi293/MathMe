package com.example.mathme;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Random;

public class HardLevel extends AppCompatActivity implements View.OnClickListener {

    FloatingActionButton exitButton;
    Random rand = new Random();
    Button checkButton;
    int randNum1 = rand.nextInt(99);
    int randNum2 = rand.nextInt(99);
    String[] opsArr = {"+", "-"};
    String randOp = opsArr[rand.nextInt(2)];
    String str, str1;
    int correctAnswer = 0;
    int numFromUser;
    static int score = 0;
    static String whichLevel;

    MediaPlayer correctAnswerSound;
    MediaPlayer wrongAnswerSound;

    static TextView scoreText;
    TextView num1Tv;
    TextView num2Tv;
    TextView opTv;
    EditText answerFromUser;

    TextView countDownText;
    CountDownTimer countDownTimer;
    long timerLeftMillSeconds = 30000;
    boolean timerRunning;
    static int count, y=1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hard_level);

        num1Tv = findViewById(R.id.num1Hard);
        num2Tv = findViewById(R.id.num2Hard);
        opTv = findViewById(R.id.opHard);

        count=Levels.i;

        answerFromUser = findViewById(R.id.resultHard);

        scoreText = findViewById(R.id.scoreHard);

        exitButton = findViewById(R.id.exitBtnFromHard);
        exitButton.setOnClickListener(this);

        checkButton = findViewById(R.id.checkBtn);
        checkButton.setOnClickListener(this);

        countDownText = findViewById(R.id.timerTextTime);

        correctAnswerSound = MediaPlayer.create(this, R.raw.correct_answer);
        wrongAnswerSound = MediaPlayer.create(this, R.raw.wrong_answer);

        startTimer();
        generateQuestion();
    }

    public void generateQuestion() {
        randNum1 = rand.nextInt(99);
        randNum2 = rand.nextInt(99);
        randOp = opsArr[rand.nextInt(2)];

        if(opTv.getText().toString().equals("-")) {
            while (randNum2 > randNum1) {
                randNum1 = rand.nextInt(99);
                randNum2 = rand.nextInt(99);
            }
        }

        str = new Integer(randNum1).toString();
        num1Tv.setText(str);

        str = new Integer(randNum2).toString();
        num2Tv.setText(str);

        opTv.setText(randOp);
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
    public void onClick (View view){
        if (view == checkButton) {
            checkAnswer();
        }

        if (view == exitButton) {
            Intent exitIntent = new Intent(this, Home.class);
            startActivity(exitIntent);
        }
    }



    public void checkAnswer() {
        str1 = answerFromUser.getText().toString();
        numFromUser = Integer.parseInt(String.valueOf(str1));
        switch (randOp) {
            case "+":
                correctAnswer = randNum1 + randNum2;
                break;

            case "-":
                correctAnswer = randNum1 - randNum2;
                break;


        }
        if (numFromUser == correctAnswer) {
            correctAnswerSound.start();
            Toast.makeText(HardLevel.this, "Correct!", Toast.LENGTH_SHORT).show();
            score += 5;
            String sum = new Integer(score).toString(); //converts int to string
            scoreText.setText(sum);
        }
        else {
            wrongAnswerSound.start();
            Toast.makeText(HardLevel.this, "Incorrect!", Toast.LENGTH_SHORT).show();
        }
        answerFromUser.setText("");
        generateQuestion();
    }

}
