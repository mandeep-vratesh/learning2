package com.example.mandeep.learning;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Vibrator;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by MANDEEP on 1/23/2017.
 */

public class Op_level2 extends Activity{
    String[] string_input = {"1", "*", "2", "+", "3", "-", "8"};
    ArrayList<Node> nodes,active_nodes,result_nodes;
    private Button run;
    private Game game = new Game();
    int pushIndex;

    @Override
    protected void onStart() {
        super.onStart();
        setContentView(R.layout.op_level);

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
                    //TODO:solve the expression
                    int result = game.calculateResult(active_nodes);
                    for (Node i: active_nodes) {
                        pushIndex = nodes.indexOf(i);
                        nodes.remove(i);
                    }

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

                    for (Node i: nodes) {
                        Log.d("===============>",i.getButton().getText().toString()+" "+nodes.indexOf(i));
                    }

                    //refreshing the container
                    game.mountAgain(nodes,container);
                }else{
                    Vibrator v = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                    v.vibrate(200);
                    Toast.makeText(getApplicationContext(), "Check for the highest operator", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
