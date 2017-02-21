package com.example.mandeep.learning;


import android.app.Activity;
import java.lang.String;
import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This custom level lets user enter his/her own problem statement based on operator precedence.
 * The game should first validate the statement then use methods from Game.java to make it playable.
 * Created by NKS on 2/5/2017.
 */


public class Op_custom_level extends Activity{

    String[] string_input;

    ArrayList<Node> nodes,active_nodes,result_nodes;
    private Button run;
    private Game game = new Game();
    int pushIndex;

    @Override
    protected void onStart() {
        super.onStart();
        setContentView(R.layout.op_level);
        //reading intent
        Bundle bundle = getIntent().getExtras();
        string_input = bundle.getStringArray("input");

        //creating layout
        final LinearLayout container = (LinearLayout) findViewById(R.id.container);
        //arraylist of node
        nodes = new ArrayList<>();
        active_nodes = new ArrayList<>();
        result_nodes = new ArrayList<>();
        int node_index=0;

        //adding buttons to the layout
        for (String i : string_input) {
            //create a button
            Button button = new Button(getApplicationContext());
            button.setText(i);
            button.setGravity(Gravity.CENTER);
            //adding button to the node
            nodes.add(new Node(button,false));
            //setting on click listener
            final int finalNode_index = node_index;
            nodes.get(node_index).getButton().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(!nodes.get(finalNode_index).isActive()) {
                        nodes.get(finalNode_index).setActive(true);
                        nodes.get(finalNode_index).getButton().getBackground().setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);
                    }
                    else {
                        nodes.get(finalNode_index).setActive(false);
                        nodes.get(finalNode_index).getButton().getBackground().clearColorFilter();
                    }
                }
            });

            //adding button to layout
            container.addView(nodes.get(node_index).getButton());

            node_index++;
        }



        //CODE RELATED TO THE RUN BUTTON

        //create button object
        run = (Button) findViewById(R.id.run);
        //attach on click listener
        run.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //clear the active nodes and result nodes
                active_nodes.clear();
                result_nodes.clear();

                //find the active buttons and put them in an arraylist
                for (Node i: nodes) {
                    if(i.isActive()){
                        active_nodes.add(i);
                    }
                }

                //send this arraylist to find the highest precedence operator and the elements to be removed
                int withHighestPrecedence = game.findHighestOperator(nodes);

                //add result nodes
                game.getNodesToRemove(result_nodes, withHighestPrecedence, nodes);

                //compare the active nodes and the result nodes from above
                if(game.compare(active_nodes,result_nodes)){
                    Toast.makeText(getApplicationContext(), "You are going good !", Toast.LENGTH_SHORT).show();

                    //get all nodes and add to the log linearlayout
                        //create linear layout object
                        LinearLayout log_container = (LinearLayout) findViewById(R.id.log);
                        //create text view
                        TextView log = new TextView(getApplicationContext());
                        String log_text = "";

                        for (Node i: nodes) {
                            if(i.isActive()){
                                //make the button text italic
                                log_text += "<b><i>"+i.getButton().getText().toString()+"</i></b>";
                            }else{
                                //keep it simple
                                log_text += i.getButton().getText().toString();
                            }
                        }
                        //set text to textview
                        log.setTextSize(TypedValue.COMPLEX_UNIT_SP,20);
                        log.setText(Html.fromHtml(log_text));
                        //add animation
                        Animation animate= AnimationUtils.loadAnimation(getApplicationContext(), R.anim.translate_up);
                        log.startAnimation(animate);
                        //add this to container
                        log_container.addView(log);

                    int result = game.calculateResult(active_nodes);
                    for (Node i: active_nodes) {
                        pushIndex = nodes.indexOf(i);
                        nodes.remove(i);
                    }

                    //creating a button with the result
                    Button button = new Button(getApplicationContext());
                    button.setText(""+result);
                    button.setGravity(Gravity.CENTER);
                    //adding button to the nodes
                    nodes.add(pushIndex, new Node(button,false));

                    for (final Node i : nodes)
                        i.getButton().setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if (!i.isActive()) {
                                    i.setActive(true);
                                    i.getButton().getBackground().setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);
                                } else {
                                    i.setActive(false);
                                    i.getButton().getBackground().clearColorFilter();
                                }
                            }
                        });

                    //refreshing the container
                    game.mountAgain(nodes,container);

                    //if the container has only one node left, show you won and ask the learner to move to the next level
                    if(nodes.size() == 1){
                        Toast.makeText(getApplicationContext(), "You won !", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Vibrator v = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                    v.vibrate(200);
                    Toast.makeText(getApplicationContext(), "Check for the highest operator", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
