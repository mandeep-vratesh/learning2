package com.example.mandeep.learning;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

/**
 * Created by MANDEEP on 1/23/2017.
 */

public class Operator_practice extends Activity {
    private Button stage1, stage2, custom;

    @Override
    protected void onStart() {
        super.onStart();
        YoYo.with(Techniques.BounceInUp)
                .duration(800)
                .playOn(findViewById(R.id.custom));
        YoYo.with(Techniques.BounceInUp)
                .duration(750)
                .playOn(findViewById(R.id.stage1));
        YoYo.with(Techniques.BounceInUp)
                .duration(700)
                .playOn(findViewById(R.id.stage2));
        YoYo.with(Techniques.BounceInUp)
                .duration(600)
                .playOn(findViewById(R.id.stage3));
        YoYo.with(Techniques.BounceInUp)
                .duration(550)
                .playOn(findViewById(R.id.stage4));
        YoYo.with(Techniques.BounceInUp)
                .duration(500)
                .playOn(findViewById(R.id.stage5));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.operator_practice);

        custom = (Button) findViewById(R.id.custom);
        stage1 = (Button) findViewById(R.id.stage1);
        stage2 = (Button) findViewById(R.id.stage2);

        custom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent go_to_learn = new Intent(Operator_practice.this,Op_custom_create.class);
                startActivity(go_to_learn);
            }
        });

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
