/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package queue;

/**
 *
 * @author Admin
 */
public class LinkedQueue {
    public static void main(String[] args) {
        MyQueue my = new MyQueue();
        my.enqueue(10);
        my.enqueue(5);
        my.enqueue(24);
        my.enqueue(2);
        my.enqueue(3);
        my.traverse();
        my.dequeue();
        my.traverse();
        System.out.println(my.front());
        my.convertDecimalRealNumberToBinary(0.0025);
    }
}
