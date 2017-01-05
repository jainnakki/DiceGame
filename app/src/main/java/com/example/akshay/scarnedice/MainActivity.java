package com.example.akshay.scarnedice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public int user_overall_score=0;
    public int user_turn_score=0;
    public int computer_overall_score=0;
    public int computer_turn_score=0;
    Random random = new Random();
    String reset_status="Your Score : 0 Computer Score : 0";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button Roll=(Button)findViewById(R.id.button0);
        Roll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num=rollDice();
                TextView label=(TextView)findViewById(R.id.gameStatus);
                if(num!=1){
                    user_turn_score+=num;
                    label.setText("Your Score : "+user_overall_score+" Computer Score : "+computer_overall_score+" your turn score : "+user_turn_score);
                }
                else{
                    user_turn_score=0;
                    label.setText("Your Score : "+user_overall_score+" Computer Score : "+computer_overall_score);
                    computerTurn();
                }
            }
        });

        Button Reset=(Button)findViewById(R.id.button2);
        Reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView label=(TextView)findViewById(R.id.gameStatus);
                Button Roll=(Button)findViewById(R.id.button0);
                Button Hold=(Button)findViewById(R.id.button1);
                user_turn_score=0;
                user_overall_score=0;
                computer_turn_score=0;
                computer_overall_score=0;
                label.setText(reset_status);
                Roll.setEnabled(true);
                Hold.setEnabled(true);
            }
        });
        Button Hold=(Button)findViewById(R.id.button1);
        Hold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView label = (TextView) findViewById(R.id.gameStatus);
                user_overall_score += user_turn_score;
                user_turn_score = 0;
                label.setText("Your Score : " + user_overall_score + " Computer Score : " + computer_overall_score);
                if (check_win() == false) {
                    computerTurn();
                }
            }
        });
    }
    public boolean check_win(){
        TextView label=(TextView)findViewById(R.id.gameStatus);
        Button Roll=(Button)findViewById(R.id.button0);
        Button Hold=(Button)findViewById(R.id.button1);
        if(user_overall_score>=100){
            label.setText("User wins!!");
            Roll.setEnabled(false);
            Hold.setEnabled(false);
            return true;
        }
        if(computer_overall_score>=100){
            label.setText("Computer Wins");
            Roll.setEnabled(false);
            Hold.setEnabled(false);
            return true;
        }
        return false;
    }
    public int rollDice(){
        int no=random.nextInt(7);
        ImageView d1=(ImageView)findViewById(R.id.imageView);
        switch(no){
            case 1: d1.setImageResource(R.drawable.dice1);
                break;
            case 2: d1.setImageResource(R.drawable.dice2);
                break;
            case 3: d1.setImageResource(R.drawable.dice3);
                break;
            case 4: d1.setImageResource(R.drawable.dice4);
                break;
            case 5: d1.setImageResource(R.drawable.dice5);
                break;
            case 6: d1.setImageResource(R.drawable.dice6);
                break;
            default: d1.setImageResource(R.drawable.dice1);
        }
        return no;
    }
    public  void computerTurn(){
        Button Hold=(Button)findViewById(R.id.button1);
        Button Reset=(Button)findViewById(R.id.button2);
        TextView label=(TextView)findViewById(R.id.gameStatus);
        Hold.setEnabled(false);
        Reset.setEnabled(false);
        int no;
        while(computer_turn_score<=20){
            no=rollDice();
            if(no==1){
                computer_turn_score=0;
                label.setText("Your Score : "+user_overall_score+" Computer Score : "+computer_overall_score+" computer rolled a one");
                break;
            }
            else{
                computer_turn_score+=no;
                label.setText("Your Score : "+user_overall_score+" Computer Score : "+computer_overall_score+" computer turn score : "+computer_turn_score+" computer rolled a "+no);
            }
        }
        if(computer_turn_score!=0){
            computer_overall_score+=computer_turn_score;
            if(check_win()==false) {
                label.setText("Your Score : " + user_overall_score + " Computer Score : " + computer_overall_score);
            }
        }
        Hold.setEnabled(true);
        Reset.setEnabled(true);
    }


}
