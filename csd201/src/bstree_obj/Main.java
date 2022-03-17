/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bstree_obj;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Admin
 */
public class Main {
    public static void main(String[] args) throws IOException {
        String fileName = "student.txt";
        BSTree bst = new BSTree();
        bst.readFromFile(fileName);
//        bst.inorder();
        bst.preorder();
//        bst.postorder();
        try{
            File f = new File("student_out.txt");
            if(f.exists()){
                f.delete();
            }
            FileWriter fw = new FileWriter(f, true);
//            bst.inorder(bst.root, fw);
//            fw.append("\n");
            bst.preorder(bst.root, fw);
            fw.append("\n");
            bst.root = bst.rotateRight(bst.root);
            bst.preorder(bst.root, fw);
//            bst.postorder(bst.root, fw);
//            fw.append("\n");
//            bst.breadth(bst.root, fw);
           fw.close();
        } catch (IOException e){
            System.out.println(e);
        }
    }
}
