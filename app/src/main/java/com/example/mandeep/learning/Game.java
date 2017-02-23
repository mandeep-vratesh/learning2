package com.example.mandeep.learning;

import android.util.Log;
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
        //1 is highest, 100 is lowest
        precedence_table.put("++",1);
        pre_remove_table.put("++",1);
        post_remove_table.put("++",1);

        precedence_table.put("*",2);
        pre_remove_table.put("*",1);
        post_remove_table.put("*",1);

        precedence_table.put("+",3);
        pre_remove_table.put("+",1);
        post_remove_table.put("+",1);

        precedence_table.put("-",3);
        pre_remove_table.put("-",1);
        post_remove_table.put("-",1);
    }


    /**
     *
     * @param s
     * @return
     */
    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch(NumberFormatException e) {
            return false;
        } catch(NullPointerException e) {
            return false;
        }
        // only got here if we didn't return false
        return true;
    }
    /**
     * The method finds out the operator with max precedence in the given array of operators.
     * @param input the string array of operators.
     * @return the index of operator with max precedence
     */
    public int findHighestOperator(ArrayList<Node> input){
        String hasMaxPrecedence = input.get(0).getButton().getText().toString();
        int hasMaxPrecedenceIndex = 0,index=0;

        for(int i=0;i<input.size();i++){
            if(!isInteger(input.get(i).getButton().getText().toString())){
                hasMaxPrecedence = input.get(i).getButton().getText().toString();
                hasMaxPrecedenceIndex = i;
                break;
            }
        }

        for (Node i : input) {
            if(isInteger(i.getButton().getText().toString())){
                index++;
                continue;
            }
            if(precedence_table.get(i.getButton().getText().toString()) < precedence_table.get(hasMaxPrecedence)){
                hasMaxPrecedence = i.getButton().getText().toString();
                hasMaxPrecedenceIndex = index;
            }
        index++;
        }
        return hasMaxPrecedenceIndex;
    }

//    public void removeFromGame(ArrayList<Node> arrayList, String operator, int index) {
//        //TODO: get operator
//        //TODO: get operands
//        //TODO: solve them
//        //TODO: add it to index+post+1
//
//        //remove the following ones
//        for (int i=0; i<post_remove_table.get(operator); i++){
//            arrayList.remove(index+1);
//        }
//        //remove the element
//        arrayList.remove(index);
//        //remove the before ones
//        for (int i=0; i<pre_remove_table.get(operator); i++){
//            arrayList.remove(index-i-1);
//        }
//    }

    public void mountAgain(ArrayList<Node> arrayList, LinearLayout linearLayout){
        linearLayout.removeAllViews();
        for (Node i: arrayList ) {
            linearLayout.addView(i.getButton());
        }
        Log.d("===============>","mounted again");
    }

    /**
     *
     * @param arrayList
     * @param index
     * @param from
     */
    public void getNodesToRemove(ArrayList<Node> result_nodes, int index, ArrayList<Node> from) {
        for (int i=-pre_remove_table.get(from.get(index).getButton().getText().toString()); i<=post_remove_table.get(from.get(index).getButton().getText().toString());i++){
            result_nodes.add(from.get(index+i));
        }
    }

    /**
     * Compares the elements of two arrayLists
     * @param active_nodes the nodes which are active on screen
     * @param result_nodes the nodes which must be active
     * @return
     */
    public boolean compare(ArrayList<Node> active_nodes, ArrayList<Node> result_nodes) {
        for (Node i : active_nodes) {
            if(result_nodes.contains(i)){
                result_nodes.remove(i);
            }else{
                return false;
            }
        }
        if(result_nodes.isEmpty())
            return true;
        else
            return false;
    }

    /**
     * Calculates the result of a string of expression given the string expression has maximum of 2 operators and 1 operand
     * @param active_nodes the set of nodes containing the string expression
     * @return the result of the expression
     */
    public int calculateResult(ArrayList<Node> active_nodes) {
        int answer = 0, operator1 = 0, operator2 = 0;
        String text, operand = "";
        Boolean flag = true;

        for (Node i : active_nodes) {
            //get node button text
            text = i.getButton().getText().toString();
            //parse them
            try{
                if(flag){
                    operator1 = Integer.parseInt(text);
                    flag = false;
                }else{
                    operator2 = Integer.parseInt(text);
                }
            }catch (Exception e){
                operand = text;
            }

            //solve them
            switch (operand){
                case "+" : answer = operator1 + operator2;break;
                case "-" : answer = operator1 - operator2;break;
                case "*" : answer = operator1 * operator2;break;
                case "/" : answer = operator1 / operator2;break;
                case "++": answer = operator1++;break;
                case "--": answer = operator1--;break;
                default: answer = 0;
            }
        }
        return answer;
    }

    public static boolean isValidExpression(String[] userInput) {
        return true;
    }
}
