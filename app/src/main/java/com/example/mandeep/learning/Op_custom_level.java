package com.example.mandeep.learning;


import android.app.Activity;
import java.lang.String;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * This custom level lets user enter his/her own problem statement based on operator precedence.
 * The game should first validate the statement then use methods from Game.java to make it playable.
 * Created by NKS on 2/5/2017.
 */


public class Op_custom_level extends Activity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.op_custom_level);
        printtext();
    }
    private void printtext(){

        final TextView expressionV = (TextView) findViewById(R.id.expression);
        Button buttonC = (Button) findViewById(R.id.buttonC);
        Button button1 = (Button) findViewById(R.id.button1);
        Button button2 = (Button) findViewById(R.id.button2);
        Button button3 = (Button) findViewById(R.id.button3);
        Button button4 = (Button) findViewById(R.id.button4);
        Button button5 = (Button) findViewById(R.id.button5);
        Button button6 = (Button) findViewById(R.id.button6);
        Button button7 = (Button) findViewById(R.id.button7);
        Button button8 = (Button) findViewById(R.id.button8);
        Button button9 = (Button) findViewById(R.id.button9);
        Button button0 = (Button) findViewById(R.id.button0);
        Button buttonAdd = (Button) findViewById(R.id.buttonAdd);
        Button buttonSub = (Button) findViewById(R.id.buttonSub);
        Button buttonMul = (Button) findViewById(R.id.buttonMul);
        Button buttonDiv = (Button) findViewById(R.id.buttonDiv);
        Button buttonOpen = (Button) findViewById(R.id.buttonOpen);
        Button buttonClose = (Button) findViewById(R.id.buttonClose);
        final Button buttonNext = (Button) findViewById(R.id.buttonNext);

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent go_to_learn = new Intent(Op_custom_level.this,exp.class);
                go_to_learn.putExtra("key",expressionV.getText());
                startActivity(go_to_learn);
            }
        });

        buttonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuilder builder = new StringBuilder(expressionV.getText());
                builder.deleteCharAt(expressionV.getText().length() - 1);
                expressionV.setText(builder.toString());
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s=expressionV.getText()+"1";
                expressionV.setText(s);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s=expressionV.getText()+"2";
                expressionV.setText(s);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s=expressionV.getText()+"3";
                expressionV.setText(s);
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s=expressionV.getText()+"4";
                expressionV.setText(s);
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s=expressionV.getText()+"5";
                expressionV.setText(s);
            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s=expressionV.getText()+"6";
                expressionV.setText(s);
            }
        });
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s=expressionV.getText()+"7";
                expressionV.setText(s);
            }
        });
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s=expressionV.getText()+"8";
                expressionV.setText(s);
            }
        });
        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s=expressionV.getText()+"0";
                expressionV.setText(s);
            }
        });
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s=expressionV.getText()+"+";
                expressionV.setText(s);
            }
        });
        buttonSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s=expressionV.getText()+"-";
                expressionV.setText(s);
            }
        });
        buttonDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s=expressionV.getText()+"/";
                expressionV.setText(s);
            }
        });
        buttonMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s=expressionV.getText()+"*";
                expressionV.setText(s);
            }
        });
        buttonOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s=expressionV.getText()+"(";
                expressionV.setText(s);
            }
        });
        buttonClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s=expressionV.getText()+")";
                expressionV.setText(s);
            }
        });
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s=expressionV.getText()+"9";
                expressionV.setText(s);
            }
        });
    }
}
