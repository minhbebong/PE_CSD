/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tree;

/**
 *
 * @author Admin
 */
public class Main {
    public static void main(String[] args) {
        BSTree bst = new BSTree();
        int []arr;
        arr = new int[]{30, 15, 10, 7, 20, 45, 40, 50, 47, 8};
        for (int i = 0; i < arr.length; i++) {
            bst.insert(arr[i]);
        }
//        bst.inorder();
//        int []a = bst.toArray();
//        for (int i = 0; i < a.length; i++) {
//            System.out.println(a[i]);
//        }
//        System.out.println(bst.search(100));
//        System.out.println(bst.rightMost(bst.root).info);
//        bst.breadth();
//        bst.inorder(bst.search(100));
//        System.out.println("");
//        bst.postorder(bst.search(30));
//        System.out.println("");
//        bst.preorder(bst.search(30));
//        
//        System.out.println("");
//        System.out.println(bst.count());
//        System.out.println(bst.count(bst.root));
//        System.out.println(bst.max().info);
//        System.out.println(bst.min().info);
//        System.out.println(bst.sum());
//        System.out.println(bst.sum(bst.root));
//        System.out.println(bst.avg());
//        System.out.println(bst.height(bst.root));
//        System.out.println(bst.mystery(bst.root));
//        
//        bst.preorder();
////        System.out.println("");
////        bst.inorder(bst.root);
////        System.out.println("");
////        bst.postorder(bst.root);
////        System.out.println("");
//////        Node temp = bst.leftMost(bst.search(15).right);
//////        System.out.println(bst.parent(temp).info);
//        bst.deleteByCopy(30);
//        bst.deleteByMerging(30);
//        bst.preorder();
        System.out.println(bst.secondHighest(bst.root).info);
        System.out.println(bst.min().info);
//        bst.root = bst.rotateLeft(bst.root);
        bst.preorder();
        System.out.println(bst.getLevel(bst.search(8)));
    }
}
