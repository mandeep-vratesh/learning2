package com.example.mandeep.learning;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Vibrator;
import android.support.annotation.RequiresApi;
import android.text.Html;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.ArrayList;

/**
 * Created by MANDEEP on 1/23/2017.
 */

public class Op_level4 extends Activity{
//    String[] string_input = {"4", "*", "(", "6", "+", "(", "8", "+", "4",")",")"};
    String[] string_input = {"4", "*", "(", "6", "+", "(", "8", "+", "4",")",")"};
    ArrayList<Node> nodes,active_nodes,result_nodes;
    private Button run, next, previous, again;
    private Game game = new Game();
    int pushIndex;

    @Override
    protected void onStart() {
        super.onStart();
        setContentView(R.layout.op_level);

        //creating layout
        final RelativeLayout options = (RelativeLayout) findViewById(R.id.options);
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
                        YoYo.with(Techniques.RubberBand)
                                .duration(1000)
                                .playOn(view);
                    }
                    else {
                        nodes.get(finalNode_index).setActive(false);
                        nodes.get(finalNode_index).getButton().getBackground().clearColorFilter();
                        YoYo.with(Techniques.RubberBand)
                                .duration(1000)
                                .playOn(view);
                    }
                }
            });

            //adding button to layout
            container.addView(nodes.get(node_index).getButton());
            //add animation
            YoYo.with(Techniques.StandUp)
                    .duration(1500)
                    .playOn(button);
            node_index++;
        }

        //CODE RELATED TO STAGE BUTTONS
        next = (Button) findViewById(R.id.next);
        previous = (Button) findViewById(R.id.previous);
        again = (Button) findViewById(R.id.again);

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent go_to_next_stage = new Intent(Op_level4.this,Op_level3.class);
                startActivity(go_to_next_stage);
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent go_to_next_stage = new Intent(Op_level4.this,Op_level5.class);
                startActivity(go_to_next_stage);
            }
        });

        again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent go_to_next_stage = new Intent(Op_level4.this,Op_level4.class);
                startActivity(go_to_next_stage);
            }
        });

        //CODE RELATED TO THE RUN BUTTON

        //create button object
        run = (Button) findViewById(R.id.run);
        //attach on click listener
        run.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
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
                    Toast toast = Toast.makeText(getApplicationContext(), "You are going good !", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.BOTTOM|Gravity.LEFT, 0, 0);
                    toast.show();
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
                    log.setTextSize(TypedValue.COMPLEX_UNIT_SP,30-nodes.size());
                    log.setText(Html.fromHtml(log_text));
                    log.setGravity(Gravity.CENTER_HORIZONTAL);
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
                                    YoYo.with(Techniques.RubberBand)
                                            .duration(1000)
                                            .playOn(view);
                                } else {
                                    i.setActive(false);
                                    i.getButton().getBackground().clearColorFilter();
                                    YoYo.with(Techniques.RubberBand)
                                            .duration(1000)
                                            .playOn(view);
                                }
                            }
                        });

                    //refreshing the container
                    nodes = game.mountAgain(nodes,container);

                    //TODO: if the container has only one node left, show you won and ask the learner to move to the next level
                    if(nodes.size() == 1){
                        toast = Toast.makeText(getApplicationContext(), "You won !", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.BOTTOM|Gravity.LEFT, 0, 0);
                        toast.show();
                        //disable run button
                        run.setVisibility(View.INVISIBLE);
                        //reset size of layout
                        options.getLayoutParams().height = ViewGroup.LayoutParams.WRAP_CONTENT;
                        //bring in all the buttons
                        next.setVisibility(View.VISIBLE);
                        previous.setVisibility(View.VISIBLE);
                        again.setVisibility(View.VISIBLE);
                        YoYo.with(Techniques.BounceInLeft)
                                .duration(750)
                                .playOn(next);
                        YoYo.with(Techniques.BounceInRight)
                                .duration(750)
                                .playOn(previous);
                        YoYo.with(Techniques.BounceInDown)
                                .duration(1000)
                                .playOn(again);
                    }
                }else{
                    Vibrator v = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                    v.vibrate(200);
                    Toast toast = Toast.makeText(getApplicationContext(), "Check for the highest operator", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.BOTTOM|Gravity.LEFT, 0, 0);
                    toast.show();                }
            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent go_to_practice = new Intent(Op_level4.this,Operator_practice.class);
        startActivity(go_to_practice);
    }
}
