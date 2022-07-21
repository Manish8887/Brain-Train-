package com.example.braintraining;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button goButtton;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationOfCorrectAnswer;

    TextView resultTextView ;
    TextView scoreTextView;
    TextView timerTextView;
    Button button4;
    Button button3;
    Button button2;
    Button button;
    Button playAgain;
    ConstraintLayout gameLayout;

    int score;

    public void playAgain(View view)
    {
        score =0;
        timerTextView.setText("30s");
        scoreTextView.setText(Integer.toString(score));
        newQuestion();

        new CountDownTimer(30100 , 1000)
        {

            @Override
            public void onTick(long l) {

                timerTextView.setText(Integer.toString((int)l/1000) + "s");
            }

            @Override
            public void onFinish() {
                resultTextView.setText("Your Score : " + Integer.toString(score));
                playAgain.setVisibility(View.VISIBLE);
            }
        }.start();
        playAgain.setVisibility(View.INVISIBLE);
        resultTextView.setVisibility(View.INVISIBLE);
    }

    public void chooseAnswer(View view)
    {
        if(Integer.toString(locationOfCorrectAnswer).equals(view.getTag().toString()))
        {
            Log.i("Result" , "You Got it");
            resultTextView.setText("Correct :)");
            resultTextView.setVisibility(View.VISIBLE);
            score++;
        }
        else
        {
            Log.i("Result" , "Unfortunately! You are wromg!");
            resultTextView.setText("Wrong :(");
            resultTextView.setVisibility(View.VISIBLE);
        }
        scoreTextView.setText(Integer.toString(score));
        answers.clear();
        newQuestion();
//        resultTextView.setText("Correct");
//        else{
//            resultTextView.setText("Wrong");
//        }
    }

    public void start(View view)
    {
        goButtton.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(View.VISIBLE);

        new CountDownTimer(30100 , 1000)
        {

            @Override
            public void onTick(long l) {

                timerTextView.setText(Integer.toString((int)l/1000) + "s");
            }

            @Override
            public void onFinish() {
                resultTextView.setText("Your Score : " + Integer.toString(score));
                playAgain.setVisibility(View.VISIBLE);
            }
        }.start();
    }

    public void newQuestion()
    {
        TextView sumTextView = findViewById(R.id.sumTextView);

        Random rand = new Random();

        int a = rand.nextInt(99);
        int b = rand.nextInt(99);

        sumTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));

        locationOfCorrectAnswer = rand.nextInt(4);

        for (int i=0 ; i<4 ; i++) {
            if (i == locationOfCorrectAnswer)
                answers.add(a + b);
            else {
                int wrongAnswer = rand.nextInt(198);
                while(wrongAnswer == a+b)
                {
                    wrongAnswer = rand.nextInt(198);
                }

                answers.add(wrongAnswer);
            }
        }

        button4.setText(Integer.toString(answers.get(0)));
        button3.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button.setText(Integer.toString(answers.get(3)));

        scoreTextView = findViewById(R.id.scoreTextView);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button4 = findViewById(R.id.button4);
        button3 = findViewById(R.id.button3);
        button2 = findViewById(R.id.button2);
        button = findViewById(R.id.button);

        newQuestion();

        goButtton = findViewById(R.id.goButton);
        gameLayout = findViewById(R.id.gameLayout);

        goButtton.setVisibility(View.VISIBLE);
        gameLayout.setVisibility(View.INVISIBLE);



        resultTextView = findViewById(R.id.resultTextView);
        resultTextView.setVisibility(View.INVISIBLE);

        timerTextView = findViewById(R.id.timerTextView);



        playAgain = findViewById(R.id.playAgainButton);



    }
}