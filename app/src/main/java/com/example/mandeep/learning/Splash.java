package com.example.mandeep.learning;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

/**
 * Created by MANDEEP on 3/7/2017.
 */

public class Splash extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
    }

    @Override
    protected void onStart() {
        super.onStart();

        YoYo.with(Techniques.RubberBand)
                .duration(2000)
                .playOn(findViewById(R.id.elearning1));
        YoYo.with(Techniques.Landing)
                .duration(2000)
                .playOn(findViewById(R.id.elearning2));
        YoYo.with(Techniques.ZoomInDown)
                .duration(2000)
                .playOn(findViewById(R.id.elearning3));
        YoYo.with(Techniques.Flash)
                .duration(2000)
                .playOn(findViewById(R.id.elearning4));
        YoYo.with(Techniques.Swing)
                .duration(2000)
                .playOn(findViewById(R.id.elearning5));
        YoYo.with(Techniques.BounceIn)
                .duration(2000)
                .playOn(findViewById(R.id.elearning6));
        YoYo.with(Techniques.Shake)
                .duration(2000)
                .playOn(findViewById(R.id.elearning7));
        YoYo.with(Techniques.RollIn)
                .duration(2000)
                .playOn(findViewById(R.id.elearning8));
        YoYo.with(Techniques.FadeInLeft)
                .duration(2000)
                .playOn(findViewById(R.id.elearning9));
        YoYo.with(Techniques.Landing)
                .duration(2000)
                .playOn(findViewById(R.id.elearning10));

        //start main activity after 3 seconds
        new CountDownTimer(3000, 1000) {

            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {
                Intent go_to_main = new Intent(Splash.this, MainActivity.class);
                startActivity(go_to_main);
            }
        }.start();
    }
}
