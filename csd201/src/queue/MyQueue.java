/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package queue;

import java.util.EmptyStackException;

/**
 *
 * @author Admin
 */
public class MyQueue {

    Node front;
    Node rear;

    public MyQueue() {
        front = null;
        rear = null;
    }

    public boolean isEmpty() {
        return front == null;
    }

    public void clear() {
        front = null;
        rear = null;
    }

    public void enqueue(int x) {
        Node p = new Node(x);
        if (rear == null) {
            rear = p;
            front = p;
        } else {
            p.next = front;
            front = p;
        }
    }

    public int dequeue() {
//        try {
//            if (isEmpty()) {
//                throw new EmptyStackException();
//            }
//            Node p = head;
//            head = head.next;
//            return p.info;
//        } catch (EmptyStackException e){
//            System.out.println("Stack is empty");
//            return 0;
//        }
        if(isEmpty()) throw new EmptyStackException();
                    Node p = front;
        if(front == rear){
            front = null;
            rear = null;
            return p.info;
        }
        
        while(p.next != rear){
            p = p.next;
        }
        p.next = null;
        int n = rear.info;
        rear = p;
        return n;        
    }
    public int front(){
        try {
            if (isEmpty()) {
                throw new EmptyStackException();
            }
            return front.info;
        } catch (EmptyStackException e){
            System.out.println("Stack is empty");
            return -1;
        }        
    }
    public void traverse(){
        Node q = front;
        while(q != null){
            System.out.print(q.info+" ");
            q = q.next;
        }
        System.out.println("");
    }
    void convertDecimalRealNumberToBinary(double n){
        int a;
        MyQueue temp = new MyQueue();
        while(true){
            if(n >= 0.5){
                temp.enqueue(1);
                n = n * 2 - 1;
            } else {
                temp.enqueue(0);
                n = n * 2;
            }
            if(n == 0){
                break;
            }
        }
        
        System.out.print("0.");
        while(!temp.isEmpty()){
            System.out.print(temp.dequeue());
        }
        System.out.println("");
    }
}
