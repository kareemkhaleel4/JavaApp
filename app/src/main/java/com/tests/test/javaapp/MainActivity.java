package com.tests.test.javaapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    Button btn1,btn2,btn3,btn4;
    int score=0,livel=1;
    TextView v1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setListeners();
        getScore();
        setScore();

    }
    @Override
    public void onResume(){
        super.onResume();
        setScore();
    }
    @Override
    public  void onRestart(){
        super.onRestart();
        setScore();
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        setScore();
    }


    private void setScore() {
        getScore();
        test();
        v1.setText(score + "");
    }

    private void test() {
        if(score>650)
            btn2.setEnabled(true);
        if(score>1300)
            btn3.setEnabled(true);
        if(score>1950)
            btn4.setEnabled(true);
    }

    private void getScore()  {
        InputStream inputf= null;
        try {
            inputf = openFileInput("file1.txt");
        } catch (FileNotFoundException e) {
            v1.setText(e.getMessage());
        }
        //getResources().openRawResource(Integer.parseInt(getResources().getIdentifier("raw/score", "raw", getPackageName()) + ""));
        try{
            BufferedReader br=new BufferedReader(new InputStreamReader(inputf));
            score=Integer.parseInt(br.readLine());
            livel=Integer.parseInt(br.readLine());

        }catch(Exception ex) {v1.setText(ex.getMessage());}
    }

    private void setListeners() {
        btn1=(Button)findViewById(R.id.button1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this, Question.class);
                i.putExtra("lvs", getResources().getIdentifier("raw/lv1s", "raw", getPackageName()) + "");
                i.putExtra("lvl", getResources().getIdentifier("raw/lv1l", "raw", getPackageName()) + "");
                i.putExtra("score",score+"");
                i.putExtra("livel", 1+"");
                startActivity(i);
            }
        });
        btn2=(Button)findViewById(R.id.button2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this, Question.class);
                i.putExtra("lvs", getResources().getIdentifier("raw/lv2s", "raw", getPackageName()) + "");
                i.putExtra("lvl", getResources().getIdentifier("raw/lv2l", "raw", getPackageName()) + "");
                i.putExtra("score",score+"");
                i.putExtra("livel", 2+"");
                startActivity(i);
            }
        });
        btn3=(Button)findViewById(R.id.button3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this, Question.class);
                i.putExtra("lvs", getResources().getIdentifier("raw/lv3s", "raw", getPackageName()) + "");
                i.putExtra("lvl", getResources().getIdentifier("raw/lv3l", "raw", getPackageName()) + "");
                i.putExtra("score",score+"");
                i.putExtra("livel", 3+"");
                startActivity(i);
            }
        });
        btn4=(Button)findViewById(R.id.button4);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this, Question.class);
                i.putExtra("lvs", getResources().getIdentifier("raw/lv4s", "raw", getPackageName()) + "");
                i.putExtra("lvl", getResources().getIdentifier("raw/lv4l", "raw", getPackageName()) + "");
                i.putExtra("score",score+"");
                i.putExtra("livel", 4+"");
                startActivity(i);
            }
        });
        v1=(TextView) findViewById(R.id.textView2);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
