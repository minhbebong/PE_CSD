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
public class Node {
    int info;
    Node next;

    public Node(int x) {
        this.info = x;
        this.next = null;
    }
    
}
