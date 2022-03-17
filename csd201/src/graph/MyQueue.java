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
public class MyQueue {

    LinkedList<Integer> t;

    public MyQueue() {
        t = new LinkedList<>();
    }
    void clear(){
        t.clear();
    }
    boolean isEmpty(){
        return (t.isEmpty());
    }
    void enqueue(int p){
        t.addLast(p);
    }
    Integer dequeue(){
        if(isEmpty()) return -1;
        return (t.removeFirst());
    }
    Integer front(){
        if(isEmpty()) return -1;
        return (t.getFirst());
    }
}
