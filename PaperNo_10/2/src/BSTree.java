/* This program contains 2 parts: (1) and (2)
   YOUR TASK IS TO COMPLETE THE PART  (2)  ONLY
 */
//(1)==============================================================
import java.io.*;
import java.util.*;

public class BSTree {

    Node root;

    BSTree() {
        root = null;
    }

    boolean isEmpty() {
        return (root == null);
    }

    void clear() {
        root = null;
    }

    void visit(Node p) {
        System.out.print("p.info: ");
        if (p != null) {
            System.out.println(p.info + " ");
        }
    }

    void fvisit(Node p, RandomAccessFile f) throws Exception {
        if (p != null) {
            f.writeBytes(p.info + " ");
        }
    }

    void breadth(Node p, RandomAccessFile f) throws Exception {
        if (p == null) {
            return;
        }
        Queue q = new Queue();
        q.enqueue(p);
        Node r;
        while (!q.isEmpty()) {
            r = q.dequeue();
            fvisit(r, f);
            if (r.left != null) {
                q.enqueue(r.left);
            }
            if (r.right != null) {
                q.enqueue(r.right);
            }
        }
    }

    void preOrder(Node p, RandomAccessFile f) throws Exception {
        if (p == null) {
            return;
        }
        fvisit(p, f);
        preOrder(p.left, f);
        preOrder(p.right, f);
    }

    void inOrder(Node p, RandomAccessFile f) throws Exception {
        if (p == null) {
            return;
        }
        inOrder(p.left, f);
        fvisit(p, f);
        inOrder(p.right, f);
    }

    void postOrder(Node p, RandomAccessFile f) throws Exception {
        if (p == null) {
            return;
        }
        postOrder(p.left, f);
        postOrder(p.right, f);
        fvisit(p, f);
    }

    void loadData(int k) { //do not edit this function
        String[] a = Lib.readLineToStrArray("data.txt", k);
        int[] b = Lib.readLineToIntArray("data.txt", k + 1);
        int[] c = Lib.readLineToIntArray("data.txt", k + 2);
        int n = a.length;
        for (int i = 0; i < n; i++) {
            insert(a[i], b[i], c[i]);
        }
    }

//===========================================================================
//(2)===YOU CAN EDIT OR EVEN ADD NEW FUNCTIONS IN THE FOLLOWING PART========
//===========================================================================
    void insert(String xForest, int xRate, int xSound) {
        //You should insert here statements to complete this function

        if (xForest.charAt(0) == 'B') {
            return;
        }
        if (root == null) {
            Node p = new Node(new Bee(xForest, xRate, xSound));
            root = p;
            return;
        }
        Node p = root;
        while (true) {
            if (p.info.rate == xRate) {
                return;
            } else if (p.info.rate < xRate) {
                if (p.right != null) {
                    p = p.right;
                } else {
                    Node temp = new Node(new Bee(xForest, xRate, xSound));
                    p.right = temp;
                    return;
                }
            } else {
                if (p.left != null) {
                    p = p.left;
                } else {
                    Node temp = new Node(new Bee(xForest, xRate, xSound));
                    p.left = temp;
                    return;

                }
            }
        }

    }

//Do not edit this function. Your task is to complete insert function above only.
    void f1() throws Exception {
        clear();
        loadData(1);
        String fname = "f1.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        breadth(root, f);
        f.writeBytes("\r\n");
        inOrder(root, f);
        f.writeBytes("\r\n");
        f.close();
    }

//=============================================================
    void f2() throws Exception {
        clear();
        loadData(5);
        String fname = "f2.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        preOrder(root, f);
        f.writeBytes("\r\n");
        //------------------------------------------------------------------------------------
        /*You must keep statements pre-given in this function.
      Your task is to insert statements here, just after this comment,
      to complete the question in the exam paper.*/
        preOrder2(root, f);
        //------------------------------------------------------------------------------------
        f.writeBytes("\r\n");
        f.close();
    }

//=============================================================
    void f3() throws Exception {
        clear();
        loadData(9);
        String fname = "f3.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        preOrder(root, f);
        f.writeBytes("\r\n");
        //------------------------------------------------------------------------------------
        /*You must keep statements pre-given in this function.
      Your task is to insert statements here, just after this comment,
      to complete the question in the exam paper.*/
        preorderThenDelete(4);
        //------------------------------------------------------------------------------------
        preOrder(root, f);
        f.writeBytes("\r\n");
        f.close();
    }

//=============================================================
    void f4() throws Exception {
        clear();
        loadData(13);;
        String fname = "f4.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        preOrder(root, f);
        f.writeBytes("\r\n");
        //------------------------------------------------------------------------------------
        /*You must keep statements pre-given in this function.
      Your task is to insert statements here, just after this comment,
      to complete the question in the exam paper.*/
        preorderThenRotate(4);
        //------------------------------------------------------------------------------------
        preOrder(root, f);
        f.writeBytes("\r\n");
        f.close();
    }

    void preOrder2(Node p, RandomAccessFile f) throws Exception {
        if (p == null) {
            return;
        }
        if (p.info.sound < 6) {
            fvisit(p, f);
        }
        preOrder2(p.left, f);
        preOrder2(p.right, f);
    }

    void deleteByCopy(Bee x) {
        Node p = root;
        Node parent = null;
        while (p != null) {
            if (p.info.rate == x.rate) {
                break;
            }
            parent = p;
            if (p.info.rate > x.rate) {
                p = p.left;
            } else {
                p = p.right;
            }
        }
        if (p == null) {
            return;
        }
        if ((p.left == null) && (p.right == null)) {
            if (parent == null) {
                root = null;
                return;
            }
            if (parent.left == p) {
                parent.left = null;
            } else {
                parent.right = null;
            }
        }
        if ((p.left != null) && (p.right == null)) {
            if (parent == null) {
                root = p.left;
                return;
            }
            if (parent.left == p) {
                parent.left = p.left;
            } else {
                parent.right = p.left;
            }
        }
        if ((p.left == null) && (p.right != null)) {
            if (parent == null) {
                root = p.right;
                return;
            }
            if (parent.left == p) {
                parent.left = p.right;
            } else {
                parent.right = p.right;
            }
        }

        if ((p.left != null) && (p.right != null)) {
            Node rm = p.left;
            Node parentRm = null;
            while (rm.right != null) {
                parentRm = rm;
                rm = rm.right;
            }
            p.info = rm.info;
            if (parentRm == null) {
                p.left = rm.left;
            } else {
                parentRm.right = rm.left;
            }
        }
    }

    void preorderThenDelete(int x) {
        int a = preorderThenDelete(root, 0, x);
    }

    int preorderThenDelete(Node p, int i, int x) {
        if (p == null) {
            return i;
        }
        i++;
        if (i == x) {
            deleteByCopy(p.info);
            return i;
        }
        if (i < x) {
            i = preorderThenDelete(p.left, i, x);
        }
        if (i < x) {
            i = preorderThenDelete(p.right, i, x);
        }
        return i;
    }

    void preorderThenRotate(int x) {
        int a = preorderThenRotate(root, 0, x);
    }

    int preorderThenRotate(Node p, int i, int x) {
        if (p == null) {
            return i;
        }
        i++;
        if (i == x) {
            if(p.left != null){
                Node temp = parent(p);
                if (temp == null) {
                    root = rotateRight(p);
                } else {
                    if (temp.right == p) {
                        temp.right = rotateRight(p);
                    } else {
                        temp.left = rotateRight(p);
                    }
                }
                return i;
            }
            
        }
        if (i < x) {
            i = preorderThenRotate(p.left, i, x);
        }
        if (i < x) {
            i = preorderThenRotate(p.right, i, x);
        }
        return i;
    }

    Node parent(Node x) {
        Node cur = root;
        Node pre = null;
        while (cur != null) {
            if (cur.info == x.info) {
                break;
            } else if (cur.info.rate > x.info.rate) {
                pre = cur;
                cur = cur.left;
            } else {
                pre = cur;
                cur = cur.right;
            }
        }
        return pre;
    }

    Node rotateRight(Node a) {
        if (a == null) {
            return null;
        }
        if (a.left == null) {
            return a;
        }
        Node b = a.left;
        a.left = b.right;
        b.right = a;
        return b;
    }
}
