/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heap;
/**
 *
 * @author Admin
 */
public class Main {
    public static void main(String[] args) {
        Heap hp = new Heap(8);
        int []arr;
        arr = new int[]{30, 15, 10, 7, 20, 45, 40, 50};
        for (int i = 0; i < arr.length; i++) {
            hp.insert(arr[i]);
        }
        hp.printHeap();
    }
}
