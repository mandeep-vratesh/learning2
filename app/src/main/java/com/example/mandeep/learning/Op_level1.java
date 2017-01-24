package com.example.mandeep.learning;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;

/**
 * Created by MANDEEP on 1/23/2017.
 */

public class Op_level1 extends Activity{
    private Button bt1,bt2,bt3;
    private int val1,val2,val3;
    private boolean bool1=false,bool2=false,bool3=false;

    @Override
    protected void onStart() {
        super.onStart();
        setContentView(R.layout.op_level1);

        bt1 = (Button) findViewById(R.id.button1);
        bt2 = (Button) findViewById(R.id.button2);
        bt3 = (Button) findViewById(R.id.button3);

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(bool1) {
                    bool1 = false;
                    bt1.setBackgroundColor(Color.WHITE);
                }
                else {
                    bool1 = true;
                    bt1.setBackgroundColor(Color.RED);
                }
                //yoyoteasting
            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(bool2) {
                    bool2 = false;
                    bt2.setBackgroundColor(Color.WHITE);
                }
                else {
                    bool2 = true;
                    bt2.setBackgroundColor(Color.RED);
                }
            }
        });

        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(bool3) {
                    bool3 = false;
                    bt3.setBackgroundColor(Color.WHITE);
                }
                else {
                    bool3 = true;
                    bt3.setBackgroundColor(Color.RED);
                }
            }
        });
    }
}
