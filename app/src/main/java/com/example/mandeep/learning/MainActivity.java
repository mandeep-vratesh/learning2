package com.example.mandeep.learning;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    private Button operator_learn, operator_practice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //relating with ids
        operator_learn = (Button) findViewById(R.id.operator_learn);
        operator_practice = (Button) findViewById(R.id.operator_practice);

        operator_learn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent go_to_learn = new Intent(MainActivity.this,Operator_learn.class);
                startActivity(go_to_learn);
            }
        });

        operator_practice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent go_to_learn = new Intent(MainActivity.this,Operator_learn.class);
                startActivity(go_to_learn);
            }
        });
    }
}
