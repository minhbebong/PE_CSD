/* This program contains 2 parts: (1) and (2)
   YOUR TASK IS TO COMPLETE THE PART  (2)  ONLY
 */
//(1)==============================================================
import java.util.*;
import java.io.*;

public class MyList {

    Node head, tail;

    MyList() {
        head = tail = null;
    }

    boolean isEmpty() {
        return (head == null);
    }

    void clear() {
        head = tail = null;
    }

    void fvisit(Node p, RandomAccessFile f) throws Exception {
        if (p != null) {
            f.writeBytes(p.info + " ");
        }
    }

    void ftraverse(RandomAccessFile f) throws Exception {
        Node p = head;
        while (p != null) {
            fvisit(p, f); // You will use this statement to write information of the node p to the file
            p = p.next;
        }
        f.writeBytes("\r\n");
    }

    void loadData(int k) { //do not edit this function
        String[] a = Lib.readLineToStrArray("data.txt", k);
        int[] b = Lib.readLineToIntArray("data.txt", k + 1);
        int[] c = Lib.readLineToIntArray("data.txt", k + 2);
        int n = a.length;
        for (int i = 0; i < n; i++) {
            addLast(a[i], b[i], c[i]);
        }
    }

//===========================================================================
//(2)===YOU CAN EDIT OR EVEN ADD NEW FUNCTIONS IN THE FOLLOWING PART========
//===========================================================================
/* 
   Khong su dung tieng Viet co dau de viet ghi chu.
   Neu dung khi chay truc tiep se bao loi va nhan 0 diem
     */
    void addLast(String xForest, int xRate, int xSound) {
        //You should write here appropriate statements to complete this function.
        if (xForest.charAt(0) == 'A') {
            return;
        }
        Node p = new Node(new Bee(xForest, xRate, xSound));
        if (tail == null) {
            tail = p;
            head = p;
        } else {
            tail.next = p;
            tail = p;
        }

    }

    //You do not need to edit this function. Your task is to complete the addLast function above only.
    void f1() throws Exception {
        clear();
        loadData(1);
        String fname = "f1.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        ftraverse(f);
        f.close();
    }

//==================================================================
    void f2() throws Exception {
        clear();
        loadData(5);
        String fname = "f2.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        ftraverse(f);
        Bee x, y;
        x = new Bee("X", 1, 2);
        y = new Bee("Y", 3, 4);
        //------------------------------------------------------------------------------------
        /*You must keep statements pre-given in this function.
       Your task is to insert statements here, just after this comment,
       to complete the question in the exam paper.*/
        addIndex(y, 2);
        addIndex(x, 3);
        //------------------------------------------------------------------------------------
        ftraverse(f);
        f.close();
    }

//==================================================================
    void f3() throws Exception {
        clear();
        loadData(9);
        String fname = "f3.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        ftraverse(f);
        //------------------------------------------------------------------------------------
        /*You must keep statements pre-given in this function.
       Your task is to insert statements here, just after this comment,
       to complete the question in the exam paper.*/
        deleIndex(3);
        deleIndex(2);
        //------------------------------------------------------------------------------------
        ftraverse(f);
        f.close();
    }

//==================================================================
    void f4() throws Exception {
        clear();
        loadData(13);
        String fname = "f4.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        ftraverse(f);
        //------------------------------------------------------------------------------------
        /*You must keep statements pre-given in this function.
       Your task is to insert statements here, just after this comment,
       to complete the question in the exam paper.*/
        sortByIndex(1, 5);
        //------------------------------------------------------------------------------------
        ftraverse(f);
        f.close();
    }

    void addIndex(Bee x, int index) {
        if (index == 1) {
            addFirst(x);
        }
        int i = 1;
        Node p = head;
        while (p != null) {
            i++;
            if (i == index) {
                break;
            }
            p = p.next;
        }
        if (p != null) {
            Node temp = new Node(x);
            temp.next = p.next;
            p.next = temp;
        }
    }

    void addFirst(Bee x) {
        Node p = new Node(x);
        if (head == null) {
            head = p;
            tail = p;
        } else {
            p.next = head;
            head = p;
        }
    }

    void deleIndex(int i) {

        if (i == 1) {
            deleteFromHead();
        } else {
            int count = 1;
            Node temp = head;
            while (temp != null) {
                count++;
                if (count == i) {
                    if (temp.next == tail) {
                        tail = temp;
                    }
                    temp.next = temp.next.next;
                    break;
                }
                temp = temp.next;
            }
        }

    }

    Bee deleteFromTail() {
        if (head == null) {
            return null;
        }
        Node p = head;
        if (head == tail) {
            head = null;
            tail = null;
            return p.info;
        }

        while (p.next != tail) {
            p = p.next;
        }
        Node n = tail;
        p.next = null;
        tail = p;
        return n.info;
    }

    Bee deleteFromHead() {
        if (head == null) {
            return null;
        }
        Node p = head;
        head = head.next;
        return p.info;
    }

    void sortByIndex(int startIndex, int endIndex) {
        if (head == null) {
            return;
        }
        if (startIndex > endIndex) {
            return;
        }
        int i = 1;
        Node p = head;
        Node start = null;
        Node end = null;
        while (p != null) {
            if (i == startIndex) {
                start = p;
            }
            if (i == endIndex) {
                end = p;
                break;
            }
            i++;
            p = p.next;
        }
        if (start == null || end == null) {
            return;
        }
        //sort
        Node pi, pj;
        pi = start;
        while (pi != end.next) {
            pj = pi.next;
            while (pj != end.next) {
                if (pj.info.sound < pi.info.sound) {
                    Bee x = pi.info;
                    pi.info = pj.info;
                    pj.info = x;
                }
                pj = pj.next;
            }
            pi = pi.next;
        }
    }
}
