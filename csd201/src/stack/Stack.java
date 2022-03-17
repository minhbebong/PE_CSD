/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stack;

/**
 *
 * @author Admin
 */
public class Stack {
    public static void main(String[] args) {
        LinkedStack ob = new LinkedStack();
//        ob.push(10);
//        ob.push(11);
//        ob.push(13);
//        ob.push(12);
        System.out.println(ob.pop());
        ob.traverse();
        ob.convertDecimalToBinary(7);
    }
}
