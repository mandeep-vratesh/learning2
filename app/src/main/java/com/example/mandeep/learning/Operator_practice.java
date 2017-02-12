package com.example.mandeep.learning;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by MANDEEP on 1/23/2017.
 */

public class Operator_practice extends Activity {
    private Button stage1, stage2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.practice);

        stage1 = (Button) findViewById(R.id.stage1);
        stage2 = (Button) findViewById(R.id.stage2);

        stage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent go_to_learn = new Intent(Operator_practice.this,Op_level1.class);
                startActivity(go_to_learn);
            }
        });

        stage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent go_to_learn = new Intent(Operator_practice.this,Op_level2.class);
                startActivity(go_to_learn);
            }
        });
    }
}
