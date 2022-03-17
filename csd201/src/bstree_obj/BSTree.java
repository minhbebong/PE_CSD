/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bstree_obj;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;

/**
 *
 * @author Admin
 */
public class BSTree {

    Node root;

    public BSTree() {
        root = null;
    }

    boolean isEmpty() {
        return root == null;
    }

    void clear() {
        root = null;
    }

    Node search(Student x) {
        if (root == null) {
            return null;
        }
        Node p = root;
        while (true) {
            if (x.id.equalsIgnoreCase(p.info.id)) {
                return p;
            } else if (x.id.compareToIgnoreCase(p.info.id) > 0) {
                p = p.right;
            } else {
                p = p.left;
            }
            if (p == null) {
                break;
            }
        }
        return p;
    }

    void insert(Student x) {
        if (root == null) {
            Node p = new Node(x);
            root = p;
            return;
        }
        Node p = root;
        while (true) {
            if (p.info.id.equalsIgnoreCase(x.id)) {
                return;
            } else if (p.info.id.compareToIgnoreCase(x.id) < 0) {
                if (p.right != null) {
                    p = p.right;
                } else {
                    Node temp = new Node(x);
                    p.right = temp;
                    return;
                }
            } else {
                if (p.left != null) {
                    p = p.left;
                } else {
                    Node temp = new Node(x);
                    p.left = temp;
                    return;

                }
            }
        }
    }

    void breadth() {
        if (root == null) {
            return;
        }
        MyQueue q = new MyQueue();
        q.enqueue(root);
        Node p;
        while (!q.isEmpty()) {
            p = q.dequeue();
            if (p.left != null) {
                q.enqueue(p.left);
            }
            if (p.right != null) {
                q.enqueue(p.right);
            }
            visit(p);
        }
        System.out.println("");
    }

    void preorder() {
        preorder(root);
        System.out.println("");
    }

    void preorder(Node p) {
        if (p == null) {
            return;
        }
        visit(p);
        preorder(p.left);
        preorder(p.right);
    }

    void inorder() {
        inorder(root);
        System.out.println("");
    }

    void inorder(Node p) {
        if (p == null) {
            return;
        }
        inorder(p.left);
        visit(p);
        inorder(p.right);
    }

    void postorder() {
        postorder(root);
        System.out.println("");
    }

    void postorder(Node p) {
        if (p == null) {
            return;
        }
        postorder(p.left);
        postorder(p.right);
        visit(p);
    }

    void visit(Node p) {
        System.out.print(p.info);
    }

    int count(Node p) {
        if (p == null) {
            return 0;
        }
        return 1 + count(p.left) + count(p.right);
    }

    int count() {
        if (root == null) {
            return 0;
        }
        MyQueue queue = new MyQueue();
        int count = 0;
        queue.enqueue(root);
        while (!queue.isEmpty()) {
            Node temp = queue.dequeue();
            count++;
            if (temp.left != null) {
                queue.enqueue(temp.left);
            }
            if (temp.right != null) {
                queue.enqueue(temp.right);
            }
        }
        return count;
    }

    void deleteByMerging(Student x) {
        Node p = root;
        Node parent = null;
        while (p != null) {
            if (p.info.id.equalsIgnoreCase(x.id)) {
                break;
            }
            parent = p;
            if (p.info.id.compareToIgnoreCase(x.id) > 0) {
                p = p.left;
            } else {
                p = p.right;
            }
        }
        //p chua gia tri x hoac p == null ( khong ton tai trong cay)

        if (p == null) {
            return; //khong chua x trong cay
        }
        //1. p la Node la
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

        //2. p chi co 1 con(trai hoac phai)
        if ((p.left != null) && (p.right == null)) {
            //can xac dinh p la con trai hay phai cua p
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
            //can xac dinh p la con trai hay phai cua p
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

        //3. P co 2 con
        if ((p.left != null) && (p.right != null)) {
            Node rm = p.left;
            while (rm.right != null) {
                rm = rm.right;
            }
            if (parent == null) {
                root = p.left;
                rm.right = p.right;
            } else {
                if (parent.left == p) {
                    parent.left = p.left;
                    rm.right = p.right;
                } else {
                    parent.right = p.left;
                    rm.right = p.right;
                }
            }
        }
    }

    void deleteByCopy(Student x) {
        Node p = root;
        Node parent = null;
        while (p != null) {
            if (p.info.id.equalsIgnoreCase(x.id)) {
                break;
            }
            parent = p;
            if (p.info.id.compareToIgnoreCase(x.id) > 0) {
                p = p.left;
            } else {
                p = p.right;
            }
        }
        //p chua gia tri x hoac p == null ( khong ton tai trong cay)

        if (p == null) {
            return; //khong chua x trong cay
        }
        //1. p la Node la
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

        //2. p chi co 1 con(trai hoac phai)
        if ((p.left != null) && (p.right == null)) {
            //can xac dinh p la con trai hay phai cua p
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
            //can xac dinh p la con trai hay phai cua p
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

        //3. P co 2 con
        if ((p.left != null) && (p.right != null)) {
            Node rm = p.left;
            Node parentRm = null;
            while (rm.right != null) {
                parentRm = rm;
                rm = rm.right;
            }
            //parentRm = null hoặc parentRm != null
            p.info = rm.info;
            if (parentRm == null) {
                p.left = rm.left;
            } else {
                parentRm.right = rm.left;
            }
        }
    }

    Node min() {
        if (root == null) {
            return null;
        }
        Node pre = null;
        Node cur = root;
        while (cur != null) {
            pre = cur;
            cur = cur.left;
        }
        return pre;
    }

    Node max() {
        if (root == null) {
            return null;
        }
        Node pre = null;
        Node cur = root;
        while (cur != null) {
            pre = cur;
            cur = cur.right;
        }
        return pre;
    }

    double sum() {
        if (root == null) {
            return 0;
        }
        MyQueue queue = new MyQueue();
        double sum = 0;
        queue.enqueue(root);
        while (!queue.isEmpty()) {
            Node temp = queue.dequeue();
            sum += temp.info.gpa;
            if (temp.left != null) {
                queue.enqueue(temp.left);
            }
            if (temp.right != null) {
                queue.enqueue(temp.right);
            }
        }
        return sum;
    }

    double sum(Node p) {
        if (p == null) {
            return 0;
        }
        return p.info.gpa + sum(p.left) + sum(p.right);
    }

    double avg() {
        return sum() / count();
    }

    int height(Node p) {
        if (p == null) {
            return 0;
        }
        return 1 + Math.max(height(p.left), height(p.right));
    }

    double cost(Node p) {
        if (p == null) {
            return 0;
        }
        return p.info.gpa + Math.max(cost(p.left), cost(p.right));
    }

    void readFromFile(String fileName) {
        try {
            File f = new File(fileName);
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                StringTokenizer stk = new StringTokenizer(line, "|");
                String id = stk.nextToken().trim();
                String name = stk.nextToken().trim();
                double gpa = Double.parseDouble(stk.nextToken().trim());
                insert(new Student(id, name, gpa));
            }
            br.close();
        } catch (IOException | NumberFormatException e) {
            System.out.println(e);
        }
    }
//    void saveToFile(String filename) {
//        if (root == null) {
//            System.out.println("Empty tree");
//            return;
//        }
//        try {
//            File f = new File(filename);
//            FileWriter fw = new FileWriter(f);
//            MyQueue queue = new MyQueue();
//            Node temp;
//            queue.enqueue(root);
//            while (!queue.isEmpty()) {
//                temp = queue.dequeue();
//                fw.write(temp.info.id + "|" + temp.info.name + "|" + temp.info.gpa + "\n");
//                if (temp.left != null) {
//                    queue.enqueue(temp.left);
//                }
//                if (temp.right != null) {
//                    queue.enqueue(temp.right);
//                }
//            }
//            fw.close();
//        } catch (IOException e) {
//            System.out.println(e);
//        }
//    }
    void preorder(Node p, FileWriter filename) throws IOException {
        if (p == null) {
            return;
        }
        fVisit(p, filename);
        preorder(p.left, filename);
        preorder(p.right, filename);
    }
    void inorder(Node p, FileWriter filename) throws IOException {
        if (p == null) {
            return;
        }
        inorder(p.left, filename);
        fVisit(p, filename);
        inorder(p.right, filename);
    }
    void postorder(Node p, FileWriter filename) throws IOException {
        if (p == null) {
            return;
        }
        postorder(p.left, filename);
        postorder(p.right, filename);
        fVisit(p, filename);
    }
    void breadth(Node p, FileWriter file) throws IOException {
        if (p == null) {
            return;
        }
        MyQueue q = new MyQueue();
        q.enqueue(p);
        Node temp;
        while (!q.isEmpty()) {
            temp = q.dequeue();
            fVisit(temp, file);
            if (temp.left != null) {
                q.enqueue(temp.left);
            }
            if (temp.right != null) {
                q.enqueue(temp.right);
            }
        }
    }
    void fVisit(Node p, FileWriter file) throws IOException {
        if(p != null){
            String temp = p.info.id + " | " + p.info.name + " | " + p.info.gpa + "\n";
            file.append(temp);
        }
    }
    void fVisit1(Node p, FileWriter file) throws IOException {
        if(p != null){
            String temp = p.info.id + " | " + p.info.name + " | " + p.info.gpa + "\n";
            file.append(temp);
        }
    }
        Node rotateLeft(Node a) {
        if(a == null){
            return null;
        }
        if(a.right == null){
            System.out.println("Can not rotate left!");
            return a;
        }
        Node b = a.right;
        a.right = b.left;
        b.left = a;
        return b;
    }

    Node rotateRight(Node a) {
        if(a == null){
            return null;
        }
        if(a.left == null){
            System.out.println("Can not rotate right!");
            return a;
        }
        Node b = a.left;
        a.left = b.right;
        b.right = a;
        return b;
    }

    Node doubleRotateRightLeft(Node a) {
        a.right = rotateRight(a.right);
        Node p = rotateLeft(a);
        return p;
    }

    Node doubleRotateLeftRight(Node a) {
        a.left = rotateLeft(a.left);
        Node p = rotateRight(a);
        return p;
    }
}
