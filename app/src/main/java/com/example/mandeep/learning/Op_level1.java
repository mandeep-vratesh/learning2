package com.example.mandeep.learning;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;

/**
 * Created by MANDEEP on 1/23/2017.
 */

public class Op_level1 extends Activity{
    String[] string_input = {"*","2", "+", "3","*"};
    ArrayList<Node> nodes,active_nodes,result_nodes;
    private Button run;
    private Game game = new Game();

    @Override
    protected void onStart() {
        super.onStart();
        setContentView(R.layout.op_level1);

        //creating layout
        final LinearLayout container = (LinearLayout) findViewById(R.id.container);
        //arraylist of node
        nodes = new ArrayList<>();
        int node_index=0;

        //adding buttons to the layout
        for (String i : string_input) {
            //create a button
            Button button = new Button(this);
            button.setText(i);
            button.setGravity(Gravity.CENTER);
            //adding button to the node
            nodes.add(new Node(button,true));
            //setting on click listener
            final int finalNode_index = node_index;
            nodes.get(node_index).getButton().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(nodes.get(finalNode_index).isActive()) {
                        nodes.get(finalNode_index).setActive(false);
                        nodes.get(finalNode_index).getButton().getBackground().setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);
                    }
                    else {
                        nodes.get(finalNode_index).setActive(true);
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

                //clear the active nodes
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
                //if(compare(active_nodes,result_nodes)){
                    //remove the active nodes from game
                //}else{
                    //vibrate();
                //}
                //game.removeFromGame(nodes,nodes.get(withHighestPrecedence).getButton().getText().toString(),withHighestPrecedence);

                for (Node i: result_nodes) {
                    System.out.println(i.getButton().getText());
                }

                //game.mountAgain(nodes,container);
            }
        });
    }
}
