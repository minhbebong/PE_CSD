/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heap;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 *
 * @author Admin
 */
public class Heap {

    int heapSize;
    int[] heap;

    public Heap(int capacity) {
        this.heapSize = 0;
        this.heap = new int[capacity + 1];
        Arrays.fill(heap, -1);
    }

    public Heap() {
    }

    public boolean isEmpty() {
        return heapSize == 0;
    }

    public boolean isFull() {
        return heapSize == heap.length;
    }

    public void makeEmpty() {
        heapSize = 0;
    }

    int parent(int i) {
        return (i - 1) / 2;
    }

    int kthChild(int i, int k) {
        return i * 2 + k;
    }

    void insert(int x) {
        if (isFull()) {
            throw new NoSuchElementException("Full");
        }
        heap[heapSize] = x;
        heapSize++;
        heapifyUp(heapSize - 1);
    }

    int findMax() {
        if (isEmpty()) {
            throw new NoSuchElementException("Empty");
        }
        return heap[0];
    }

    int delete(int ind) {
        if (isEmpty()) {
            throw new NoSuchElementException("Empty");
        }
        int keyItime = heap[ind];
        heap[ind] = heap[heapSize];
        heapSize--;
        heapifyDown(ind);
        return keyItime;
    }

    void heapifyUp(int ind) {
        int tmp = heap[ind];
        while (ind > 0 && tmp > heap[parent(ind)]) {
            heap[ind] = heap[parent(ind)];
            ind = parent(ind);
        }
        heap[ind] = tmp;
    }

    void heapifyDown(int ind) {
        int child;
        int tmp = heap[ind];
        while (kthChild(ind, 1) < heapSize) {
            child = minChild(ind);
            if (heap[child] > tmp) {
                heap[ind] = heap[child];
            } else {
                break;
            }
            ind = child;
        }
        heap[ind] = tmp;
    }

    int minChild(int ind) {
        int leftChild = kthChild(ind, 1);
        int rightChild = kthChild(ind, 2);
        if (rightChild < heapSize && heap[rightChild] < heap[leftChild]) {
            return rightChild;
        }
        return leftChild;
    }

    public void printHeap() {
        System.out.print("\nHeap = ");
        for (int i = 0; i < heapSize; i++) {
            System.out.print(heap[i] + " ");
        }
        System.out.println();
    }
}
