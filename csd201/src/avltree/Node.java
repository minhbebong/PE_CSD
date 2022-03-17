/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avltree;

/**
 *
 * @author Admin
 */
public class Node {
    int info;
    Node left;
    Node right;
    int height;
    public Node() {
    }

    public Node(int info) {
        this.info = info;
        this.left = null;
        this.right = null;
        this.height = 1;
    }
    
}
