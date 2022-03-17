/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exeTreeObj;

import bstree_obj.*;

/**
 *
 * @author Admin
 */
public class Node {
    Person info;
    Node left;
    Node right;

    public Node() {
    }

    public Node(Person info) {
        this.info = info;
        this.left = null;
        this.right = null;
    }
    
}
