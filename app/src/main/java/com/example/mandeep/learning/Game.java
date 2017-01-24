package com.example.mandeep.learning;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by MANDEEP on 1/24/2017.
 */

public class Game {
    private HashMap<String, Integer> static_table = new HashMap<String, Integer>();

    public Game(){
        static_table.put("*",2);
        static_table.put("+",3);
        static_table.put("++",1);
    }

    /**
     * The method finds out the operator with max precedence in the given array of operators.
     * @param input the array of operators.
     * @return the operator with max precedence
     */
    public String findHighestOperator(String[] input){
        String hasMaxPrecedence = input[0];

        for (String i : input) {
            if(static_table.get(i) < static_table.get(hasMaxPrecedence)){
                hasMaxPrecedence = i;
            }
        }
        return hasMaxPrecedence;
    }

    public void remove(ArrayList<Integer> arrayList, int i) {
        arrayList.remove(Integer.valueOf(i));
        //remove the before ones
        //remove the element
        //remove the following ones
    }
}
