package com.example.mandeep.learning;

import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by MANDEEP on 1/24/2017.
 */

public class Game {
    //the precedence table
    private HashMap<String, Integer> precedence_table = new HashMap<String, Integer>();
    //pre elements to be removed
    private HashMap<String, Integer> pre_remove_table = new HashMap<String, Integer>();
    //post elements to be removed
    private HashMap<String, Integer> post_remove_table = new HashMap<String, Integer>();

    public Game(){
        precedence_table.put("*",2);
        pre_remove_table.put("*",1);
        post_remove_table.put("*",1);

        precedence_table.put("+",3);
        pre_remove_table.put("+",1);
        post_remove_table.put("+",1);

        precedence_table.put("++",1);
        pre_remove_table.put("++",1);
        post_remove_table.put("++",1);
    }

    /**
     * The method finds out the operator with max precedence in the given array of operators.
     * @param input the string array of operators.
     * @return the operator with max precedence
     */
    public String findHighestOperator(String[] input){
        String hasMaxPrecedence = input[0];

        for (String i : input) {
            if(precedence_table.get(i) < precedence_table.get(hasMaxPrecedence)){
                hasMaxPrecedence = i;
            }
        }
        return hasMaxPrecedence;
    }

    public void removeFromGame(ArrayList<Node> arrayList, String operator, int index) {
        //TODO: get operator
        //TODO: get operands
        //TODO: solve them
        //TODO: add it to index+post+1

//        System.out.println(arrayList);
//        System.out.println(operator);
//        System.out.println(index);
        //remove the following ones
        for (int i=0; i<post_remove_table.get(operator); i++){
            arrayList.remove(index+1);
        }
        //remove the element
        arrayList.remove(index);
        //remove the before ones
        for (int i=0; i<pre_remove_table.get(operator); i++){
            arrayList.remove(index-i-1);
        }
    }

    public void mountAgain(ArrayList<Node> arrayList, LinearLayout linearLayout){
        linearLayout.removeAllViews();
        for (Node i: arrayList ) {
            linearLayout.addView(i.getButton());
        }
    }
}
