package com.example.mandeep.learning;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by NKS on 4/10/2017.
 */

public class Hint extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hint);

        TextView hint = (TextView) findViewById(R.id.hinttext);
        Button ok = (Button) findViewById(R.id.ok);

        switch(getIntent().getExtras().get("type").toString()){
            case "*" : hint.setText("* has greater priority than + or -");break;
            case "bracket" : hint.setText("Brackets are solved first. In case of multiple brackets, the innermost brackets are solved first.");break;
            default: hint.setText("mandeep");
        }


        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go_to_next_stage;

                switch(getIntent().getExtras().get("type").toString()){
                    case "*" : go_to_next_stage = new Intent(Hint.this,Op_level2.class);
                        startActivity(go_to_next_stage);break;
                    case "bracket" : go_to_next_stage = new Intent(Hint.this,Op_level4.class);
                        startActivity(go_to_next_stage);break;
                }
            }
        });
    }
}
