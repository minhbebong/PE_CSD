/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exeTreeObj;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Admin
 */
public class Main {
    public static void main(String[] args) throws IOException {
        String fileName = "person.txt";
        BSTree bst = new BSTree();
        bst.readFromFile(fileName);
//        bst.inorder();
//        bst.preorder();
//        bst.postorder();
//System.out.println(bst.avg());
//        try{
//            File f = new File("q2.txt");
//            if(f.exists()){
//                f.delete();
//            }
//            FileWriter fw = new FileWriter(f, true);
//            bst.postorder2(bst.root, fw);
//           fw.close();
//        } catch (IOException e){
//            System.out.println(e);
//        }
//        bst.breadth();
//        bst.deleteRootByCopying();
//        bst.breadth();

//        bst.breadth();
//        bst.postorderThenDelete(5);
//        bst.breadth();
        bst.breadth();
        bst.postorderThenRotate(5);
        bst.breadth();
//        bst.breadth();
//        bst.preorderThenRotate(1);
//        bst.breadth();
//        bst.breadth();
//        bst.preorderThenDelete(6);
//        bst.breadth();
        
        
//        bst.breadth();
//        bst.rotateRoot();
//        bst.breadth();

//        bst.breadth();
//        bst.breadthThenDelete();
//        bst.breadth();

//        System.out.println(bst.isHeightBalanced());
//        ArrayList<Person> a = bst.toArray();
//        for (int i = 0; i < a.size(); i++) {
//            System.out.println(a.get(i));
//        }
//        bst.balance(a);
//        bst.breadth();
//        System.out.println(bst.isHeightBalanced());
//        bst.breadth();
//        bst.preorder2();
//        bst.breadth();
//        System.out.println("");
//                                H
//                        /                   \   
//                      C                        M
//                  /       \                 /     \
//                 A         E              L         O
//                         /   \           /
//                       D      F        K
//                                     /
//                                   J
//        bst.inorder();
//        Person[] a = bst.toArray();
//        System.out.println(a.length);
//        for (Person person : a) {
//            System.out.println(person);
//        }
    }
}
