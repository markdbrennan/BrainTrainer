package com.mark_brennan.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ArrayList<Integer> answers = new ArrayList<Integer>();
    TextView resultTextView;
    TextView scoreTextView;
    TextView questionTextView;
    TextView timeRemaining;
    Button startButton;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button playAgain;
    RelativeLayout mainLayout;
    int locationOfCorrectAnswer;
    int score = 0;
    int numberOfQuestions = 0;


    public void startGame(View view) {
        startButton.setVisibility(View.INVISIBLE);
        mainLayout.setVisibility(View.VISIBLE);
        generateQuestion();
        countdownTimer();
    }


    public void countdownTimer() {
        new CountDownTimer(30100, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeRemaining.setText(String.valueOf(millisUntilFinished / 1000) + "s");
            }

            @Override
            public void onFinish() {
                timeRemaining.setText("0s");
                resultTextView.setText("Your score: "+ Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
                playAgain.setVisibility(View.VISIBLE);
                button0.setClickable(false);
                button1.setClickable(false);
                button2.setClickable(false);
                button3.setClickable(false);
            }
        }.start();
    }


    public void playAgain(View view) {
        score = 0;
        numberOfQuestions = 0;
        scoreTextView.setText("0/0");
        resultTextView.setText("");
        playAgain.setVisibility(View.INVISIBLE);
        countdownTimer();
        generateQuestion();
        button0.setClickable(true);
        button1.setClickable(true);
        button2.setClickable(true);
        button3.setClickable(true);
    }


    public void generateQuestion() {

        Random random = new Random();

        int a = random.nextInt(21);
        int b = random.nextInt(21);

        questionTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));
        locationOfCorrectAnswer = random.nextInt(4);
        answers.clear();

        int incorrectAnswer;

        for (int i=0; i<4; i++) {
            if (i == locationOfCorrectAnswer) {
                answers.add(a + b);
            } else {
                incorrectAnswer = random.nextInt(41);
                while (incorrectAnswer == a + b) {
                    incorrectAnswer = random.nextInt(41);
                }
                answers.add(incorrectAnswer);
            }
        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));

    }



    public void chooseAnswer(View view) {
        if (view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))) {
            score++;
            resultTextView.setText("Correct!");
        } else {
            resultTextView.setText("Wrong!");
        }
        numberOfQuestions++;
        scoreTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
        generateQuestion();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionTextView = (TextView)findViewById(R.id.questionTextView);
        resultTextView = (TextView)findViewById(R.id.resultTextView);
        scoreTextView = (TextView)findViewById(R.id.scoreTextView);
        timeRemaining = (TextView)findViewById(R.id.timeRemaining);
        startButton = (Button)findViewById(R.id.startButton);
        mainLayout = (RelativeLayout)findViewById(R.id.mainLayout);
        button0 = (Button)findViewById(R.id.button0);
        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        button3 = (Button)findViewById(R.id.button3);
        playAgain = (Button)findViewById(R.id.playAgain);
    }
}
