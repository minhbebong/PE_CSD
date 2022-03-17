/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exeLinkedList;

/**
 *
 * @author Admin
 */
public class LinkedList {
    public static void main(String[] args) {
        MyList my = new MyList();
        my.readFromFile("person.txt");
        my.traverse();
//        my.disWithCon();
//        my.traverseWithCon();
//        my.displayLast5WithCon();
        my.sortByIndex(3, 2);
        my.traverse();
    }
}
