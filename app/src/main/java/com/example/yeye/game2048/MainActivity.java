package com.example.yeye.game2048;

import android.app.Activity;
//import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.view.View.OnClickListener;


public class MainActivity extends Activity {
    public MainActivity() {
        mainActivity = this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvScore = (TextView) findViewById(R.id.tvScore);
        pause = (Button) findViewById(R.id.pause);
        reButton = (Button) findViewById(R.id.reButton);

        reButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
               // flg = false;
               GameView.reStartGame();
                System.out.println("teStart");
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void clearScore() {
        score = 0;
        showScore();
    }

    public void showScore() {
        String s = String.valueOf(score);
        tvScore.setText(s);
        System.out.println("score:" + score);
        return;
    }

    public void addScore(int s) {
        score += s;
        showScore();
    }

    public int getScore() {
        return score;
    }

    private int score = 0;
   // public boolean flg = true;
    private TextView tvScore;
    private Button reButton;
    private Button pause;

    private static MainActivity mainActivity = null;
    public static MainActivity getMainActivity() {
        return mainActivity;
    }
}

