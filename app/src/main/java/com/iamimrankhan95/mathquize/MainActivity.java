package com.iamimrankhan95.mathquize;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int result=0,score=0,total=0,totalTime=33,highScore=0;
    boolean isFinished=false;
    TextView textViewRemaining;
    TextView textViewQuestion;
    TextView textViewScore;
    TextView textViewFeedback;
    TextView textView00;
    TextView textView01;
    TextView textView10;
    TextView textView11;
    TextView textViewHighScore;
    Button buttonPlayAgain;
    GridLayout gridLayoutOptions;
    TextView textViewGo;
    CountDownTimer countDownTimer;
    public void startQuiz(View view){
        textViewGo=(TextView) view;
        textViewGo.setVisibility(View.GONE);
        gridLayoutOptions.setVisibility(View.VISIBLE);
        textViewRemaining.setVisibility(View.VISIBLE);
        textViewQuestion.setVisibility(View.VISIBLE);
        textViewScore.setVisibility(View.VISIBLE);
        textViewFeedback.setVisibility(View.VISIBLE);
        textViewHighScore.setVisibility(View.VISIBLE);
        textViewHighScore.setText("High Score: "+this.highScore);
        textViewFeedback.setText(" ");
        result=generateQuestion();
        this.totalTime=10;
        startCountDown();
        placeOptions();
    }
    public void playAgainBtn(View view){
        gridLayoutOptions.setVisibility(View.INVISIBLE);
        textViewRemaining.setVisibility(View.INVISIBLE);
        textViewQuestion.setVisibility(View.INVISIBLE);
        textViewScore.setVisibility(View.INVISIBLE);
        textViewFeedback.setVisibility(View.INVISIBLE);
        textViewGo.setVisibility(View.VISIBLE);
        buttonPlayAgain.setVisibility(View.INVISIBLE);
        textViewHighScore.setVisibility(View.INVISIBLE);
        this.total=0;this.score=0;this.result=0;this.isFinished=false;
        textViewScore.setText("0/0");
        textViewFeedback.setText(" ");
    }
    public void checkResult(View view){
        if(!isFinished){
            TextView textViewForCheck=(TextView)view;
            int rstCheck=Integer.parseInt(textViewForCheck.getText().toString());
            if(rstCheck==result){
                score++;
                textViewFeedback.setText("Correct!");
                countDownTimer.cancel();
                totalTime+=4;
                startCountDown();
            }else{
                textViewFeedback.setText("Wrong!");
            }
            total++;
            updateTextEditorScore();
            result=generateQuestion();
            placeOptions();
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Initialize();
    }

    private void Initialize() {
         textViewRemaining=findViewById(R.id.textViewRemainingTime); textViewRemaining.setVisibility(View.INVISIBLE);
         textViewQuestion=findViewById(R.id.textViewQuestion); textViewQuestion.setVisibility(View.INVISIBLE);
         textViewScore=findViewById(R.id.textViewTotalScore); textViewScore.setVisibility(View.INVISIBLE);
         textViewFeedback=findViewById(R.id.textViewFeedback); textViewFeedback.setVisibility(View.INVISIBLE);
         textViewHighScore=findViewById(R.id.textViewHighScore);textViewHighScore.setVisibility(View.INVISIBLE);
         textView00=findViewById(R.id.textView00);
         textView01=findViewById(R.id.textView01);
         textView10=findViewById(R.id.textView10);
         textView11=findViewById(R.id.textView11);
         buttonPlayAgain=findViewById(R.id.buttonPlayAgain); buttonPlayAgain.setVisibility(View.INVISIBLE);
         gridLayoutOptions=findViewById(R.id.gridLayoutOptions); gridLayoutOptions.setVisibility(View.INVISIBLE);
    }
    protected void updateTextEditorRemaining(long timeRemaining){
        String min,sec2;
        long sec;
        //min=Integer.toString(time/60); sec2 = time%60;
        sec=timeRemaining/1000;
        if(sec<=9){
            sec2="0"+sec;
        }else{
            sec2=Long.toString(sec);
        }
        textViewRemaining.setText(sec2+'s');
    }
    protected int generateRandomInteger(){
        Random rand = new Random();
        int number = rand.nextInt(49);
        return number;
    }
    protected void updateTextEditorScore(){
        String totalStr,scoreStr;
        totalStr=Integer.toString(total);
        scoreStr=Integer.toString(score);
//        min=Integer.toString(time/60); sec2 = time%60;
//        sec=Integer.toString(sec2);
//        if(sec2<=9){
//            sec="0"+sec2;
//        }
        textViewScore.setText( totalStr+"/"+scoreStr);
    }
    protected int generateQuestion(){
        int fNum,lNum,result;
        fNum=generateRandomInteger();lNum=generateRandomInteger();
        result=fNum+lNum;
//        String operator=fNum/2==0? "+":"-";
//        if(operator.equals('+')){
//            result=fNum+lNum;
//        }else{
//            result=fNum-lNum;
//        }
        textViewQuestion.setText(Integer.toString(fNum)+'+'+Integer.toString(lNum));
        return result;
    }
    private void startCountDown() {
        countDownTimer=new CountDownTimer(totalTime*1000,1000){
            @Override
            public void onTick(long millisUntilFinished) {
                totalTime=(int)((millisUntilFinished/1000));
                updateTextEditorRemaining(millisUntilFinished);
            }

            @Override
            public void onFinish() {
                textViewFeedback.setText("Your score: "+score);
                buttonPlayAgain.setVisibility(View.VISIBLE);
                highScore=score;
                System.out.println("finished");
                System.out.println("totalTime:"+ totalTime);
                isFinished=true;
            }
        }.start();
    }
    protected void placeOptions(){
        int num;
        for(;;){
             num=generateRandomInteger();
            if(num!=result){
                textView00.setText(Integer.toString(num));
                break;
            }
        }
        for(;;){
            num=generateRandomInteger();
            if(num!=result){
                textView01.setText(Integer.toString(num));
                break;
            }
        }
        for(;;){
            num=generateRandomInteger();
            if(num!=result){
                textView10.setText(Integer.toString(num));
                break;
            }
        }
        for(;;){
            num=generateRandomInteger();
            if(num!=result){
                textView11.setText(Integer.toString(num));
                break;
            }
        }
        Random rand=new Random();
        int pos=rand.nextInt(4);
        String res= Integer.toString(result);
        if(pos==1){
            textView00.setText(res);
        }else if(pos==2){
            textView01.setText(res);
        }else if(pos==3){
            textView10.setText(res);
        }else{
            textView00.setText(res);
        }
    }
}
