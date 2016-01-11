package com.tests.test.javaapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Question extends AppCompatActivity {
    ArrayList<String> Qussnt=new ArrayList(), Quslog=new ArrayList(),Anss=new ArrayList(),Ansl=new ArrayList();
    TextView v1,vscore;
    int lvl=0,score=0;
    String qus="",rightans="";
    RadioButton radioans1,radioans2,radioans3,radioans4;
    Button btnBack,btnOk,btnHint;
    Intent intent;
    int mark=100,livel;
    public Question() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        intent=getIntent();
       setListeners();
        score=Integer.parseInt(intent.getStringExtra("score"));
        livel=Integer.parseInt(intent.getStringExtra("livel"));
        try {
            int resId=Integer.parseInt(intent.getStringExtra("lvs"));
            openstream(resId);
            resId=Integer.parseInt(intent.getStringExtra("lvl"));
            openstream(resId);


        }catch (Exception ex){v1.setText(ex.getMessage());}

        setQuestion();
    }

    private void setListeners() {
        v1=(TextView) findViewById(R.id.textView);
        vscore=(TextView)findViewById(R.id.score);
        radioans1=(RadioButton) findViewById(R.id.Ans1);
        radioans2=(RadioButton) findViewById(R.id.Ans2);
        radioans3=(RadioButton) findViewById(R.id.Ans3);
        radioans4=(RadioButton) findViewById(R.id.Ans4);
        btnBack=(Button) findViewById(R.id.btnBack);
        btnOk=(Button) findViewById(R.id.btnOK);
        btnHint=(Button) findViewById(R.id.btnHint);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scoreUp(score);
                finish();
            }
        });
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioGroup rg=(RadioGroup)findViewById(R.id.RG1);
                RadioButton tmpr;
                try{

                    int id=rg.getCheckedRadioButtonId();
                    tmpr=(RadioButton) findViewById(id);
                    if(tmpr.getText()==rightans){
                        Toast.makeText(Question.this ,"Correct", Toast.LENGTH_SHORT).show();
                        score+=mark;
                        scoreUp(score);
                        tmpr.setChecked(false);
                        mark=100;
                        setQuestion();

                    }else{
                        Toast.makeText(Question.this ,"Wrong", Toast.LENGTH_SHORT).show();
                        mark-=25;
                    }
                }catch (Exception ex){
                    v1.setText(ex.getMessage());
                }
            }
        });
    }

    private void scoreUp(int mark) {
        try {

            PrintStream pr=new PrintStream(openFileOutput("file1.txt",MODE_PRIVATE));

            pr.println(mark);
            pr.println(livel);
            pr.close();

        } catch (FileNotFoundException e) {
            v1.setText(e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            v1.setText(e.getMessage());
        }
        setScore();
    }

    private void setScore() {
        vscore.setText(score+"");
    }

    private void openstream(int x) {
        InputStream inputf=getResources().openRawResource(x);
        try{
            BufferedReader br=new BufferedReader(new InputStreamReader(inputf));
            String line;
            while (true){
                if((line=br.readLine())==null)
                    break;
                line=line.substring(1,line.length());
                v1.setText(line + "");
                Qussnt.add(line);
                for(int k=0;k<4;k++) {
                    line=br.readLine();
                    Anss.add(line);
                }
            }
        }catch(Exception ex) {v1.setText(ex.getMessage());}
    }

    protected void setQuestion(){
        Random rnd=new Random();
        int temp = rnd.nextInt(Qussnt.size());
        qus =Qussnt.get(temp);
        Qussnt.remove(temp);
        v1.setText(qus);
        ArrayList<String> temparry=new ArrayList<>();
        for(int i=0;i<4;i++){
            temparry.add(String.valueOf(Anss.get(temp*4)));
            if(temparry.get(i).charAt(0)=='*'){

                String tmpstring=temparry.get(i).substring(1, temparry.get(i).length());
                temparry.add(i,tmpstring);
                rightans=temparry.get(i);
            }
            Anss.remove(temp*4);
        }
        String tempstr[]=new String[4];
        for(int i=0;i<4;i++){
            int randval=rnd.nextInt(4-i);
            tempstr[i]=temparry.get(randval);
            temparry.remove(randval);
        }
        radioans1.setText(tempstr[0]);
        radioans2.setText(tempstr[1]);
        radioans3.setText(tempstr[2]);
        radioans4.setText(tempstr[3]);
    }


}
