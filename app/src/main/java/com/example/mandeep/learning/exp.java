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


public class exp extends Activity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom);
        Bundle bundle = getIntent().getExtras();
        String message = bundle.getString("key");
        TextView txtView = (TextView) findViewById(R.id.exp);
        txtView.setText(message);

    }
}