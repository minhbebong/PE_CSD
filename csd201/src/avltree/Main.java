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
public class Main {
    public static void main(String[] args) {
        BSTree bst = new BSTree();
        int []arr;
        arr = new int[]{12, 7, 1, 3, 2, 5, 10, 8, 6, 9};
        for (int i = 0; i < arr.length; i++) {
            bst.insert(arr[i]);
        }
        bst.breadth();
        /*
          15
        10    30
      7     20    45
                40  50
        */
        
        bst.delete(15);
        bst.breadth();
    }
}
