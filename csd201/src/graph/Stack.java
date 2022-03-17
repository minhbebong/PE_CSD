/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph;

import java.util.LinkedList;

/**
 *
 * @author Admin
 */
public class Stack {
    LinkedList<Integer> t;

    public Stack() {
        t = new LinkedList<>();
    }
    boolean isEmpty(){
        return t.isEmpty();
    }
    void clear(){
        t.clear();
    }
    void push(int x){
        t.addLast(x);
    }
    Integer pop(){
        if(isEmpty()) return null;
        return t.removeLast();
    }
    Integer top(){
        if(isEmpty()) return null;
        return t.getLast();
    }
}
