/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stack;

import java.util.EmptyStackException;

/**
 *
 * @author Admin
 */
public class LinkedStack {

    Node top;

    public LinkedStack() {
        this.top = null;
    }

    boolean isEmpty() {
        return top == null;
    }

    void clear() {
        top = null;
    }

    void push(int x) {
        Node p = new Node(x);
//        if (top == null) {
//            top = p;
//        } else {
//        }
            p.next = top;
            top = p;
    }

    int pop(){
        try{
        if(isEmpty()) throw new EmptyStackException();
        Node p = top;
        top = top.next;
        return p.info;
            
        } catch (EmptyStackException e){
            System.out.println("Stack is empty");
            return 0;
        }
    }

    int top()throws EmptyStackException{
//        try{
//        if(isEmpty()) throw new EmptyStackException();
//            return top.info;
//            
//        }catch (EmptyStackException e){
//            System.out.println("Stack is empty");
//            return 0;
//        }
        if(isEmpty()) throw new EmptyStackException();
        return top.info;
    }

    void traverse() {
        Node q = top;
        while (q != null) {
            System.out.print(q.info + " ");
            q = q.next;
        }
        System.out.println("");
    }
    void convertDecimalToBinary(int n){
        LinkedStack temp = new LinkedStack();
        while(n != 0){
            temp.push(n%2);
            n /= 2;
        }
        while(!temp.isEmpty()){
            System.out.print(temp.pop());
        }
    }
}
