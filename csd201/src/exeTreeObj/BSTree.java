/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exeTreeObj;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
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

    Node search(Person x) {
        if (root == null) {
            return null;
        }
        Node p = root;
        while (true) {
            if (x.name.equalsIgnoreCase(p.info.name)) {
                return p;
            } else if (x.name.compareToIgnoreCase(p.info.name) > 0) {
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

    void insert(String xName, int xAge) {
        if (xName.charAt(0) == 'B') {
            return;
        }
        if (root == null) {
            Node p = new Node(new Person(xName, xAge));
            root = p;
            return;
        }
        Node p = root;
        while (true) {
            if (p.info.name.equalsIgnoreCase(xName)) {
                return;
            } else if (p.info.name.compareToIgnoreCase(xName) < 0) {
                if (p.right != null) {
                    p = p.right;
                } else {
                    Node temp = new Node(new Person(xName, xAge));
                    p.right = temp;
                    return;
                }
            } else {
                if (p.left != null) {
                    p = p.left;
                } else {
                    Node temp = new Node(new Person(xName, xAge));
                    p.left = temp;
                    return;

                }
            }
        }
    }

    int count(Node p) {
        if (p == null) {
            return 0;
        }
        return 1 + count(p.left) + count(p.right);
    }

    int sum(Node p) {
        if (p == null) {
            return 0;
        }
        return p.info.age + sum(p.left) + sum(p.right);
    }

    double avg() {
        return (double) sum(root) / count(root);
    }

    int height(Node p) {
        if (p == null) {
            return 0;
        }
        return 1 + Math.max(height(p.left), height(p.right));
    }

    Node rotateLeft(Node a) {
        if (a == null) {
            return null;
        }
        if (a.right == null) {
            return a;
        }
        Node b = a.right;
        a.right = b.left;
        b.left = a;
        return b;
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

    void readFromFile(String fileName) {
        try {
            File f = new File(fileName);
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                StringTokenizer stk = new StringTokenizer(line, "|");
                String name = stk.nextToken().trim();
                int age = Integer.parseInt(stk.nextToken().trim());
                insert(name, age);
            }
            br.close();
        } catch (IOException | NumberFormatException e) {
            System.out.println(e);
        }
    }

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

    void postorder2(Node p, FileWriter filename) throws IOException {
        if (p == null) {
            return;
        }
        postorder2(p.left, filename);
        postorder2(p.right, filename);
        if (p.info.age < avg()) {
            fVisit2(p, filename);
        }
    }

    void fVisit2(Node p, FileWriter file) throws IOException {
        if (p != null) {
            String temp = p.info.name + " | " + p.info.age + "\n";
            file.append(temp);
        }
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
        if (p != null) {
            String temp = p.info.name + " | " + p.info.age + "\n";
            file.append(temp);
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

    void deleteByMerging(Person x) {
        Node p = root;
        Node parent = null;
        while (p != null) {
            if (p.info.name.equalsIgnoreCase(x.name)) {
                break;
            }
            parent = p;
            if (p.info.name.compareToIgnoreCase(x.name) > 0) {
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

    void deleteByCopy(Person x) {
        Node p = root;
        Node parent = null;
        while (p != null) {
            if (p.info.name.equalsIgnoreCase(x.name)) {
                break;
            }
            parent = p;
            if (p.info.name.compareToIgnoreCase(x.name) > 0) {
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

    void deleteRootByCopying() {
        deleteByCopy(root.info);
    }

    void breadthThenDelete(int x) {
        int count = 0;
        if (root == null) {
            return;
        }
        MyQueue q = new MyQueue();
        q.enqueue(root);
        Node p;
        while (!q.isEmpty()) {
            p = q.dequeue();
            if (p.info.age >= avg()) {
                count++;
                if (count == x) {
                    deleteByCopy(p.info);
                    return;
                }
            }
            if (p.left != null) {
                q.enqueue(p.left);
            }
            if (p.right != null) {
                q.enqueue(p.right);
            }
        }
    }

    void breadthThenRotate(int x) {
        int count = 0;
        if (root == null) {
            return;
        }
        MyQueue q = new MyQueue();
        q.enqueue(root);
        Node p;
        while (!q.isEmpty()) {
            p = q.dequeue();
            if (p.right != null) {
                count++;
                if (count == x) {
                    Node temp = parent(p);
                    if (temp == null) {
                        root = rotateLeft(p);
                    } else {
                        if (temp.right == p) {
                            temp.right = rotateLeft(p);
                        } else {
                            temp.left = rotateLeft(p);
                        }
                    }
                    return;
                }
            }
            if (p.left != null) {
                q.enqueue(p.left);
            }
            if (p.right != null) {
                q.enqueue(p.right);
            }
        }
    }

    void rotateRoot() {
        if (root == null) {
            return;
        }
        if (root.left == null) {
            return;
        }
        Node p = root;
        root = rotateRight(root);
    }

    void preorderThenRotate(int x) {
        int a = preorderThenRotate(root, 0, x);
//        System.out.println(a);
    }

    int preorderThenRotate(Node p, int i, int x) {
        if (p == null) {
            return i;
        }
        if (p.right != null) {
            i++;
            if (i == x) {
                Node temp = parent(p);
                if (temp == null) {
                    root = rotateLeft(p);
                } else {
                    if (temp.right == p) {
                        temp.right = rotateLeft(p);
                    } else {
                        temp.left = rotateLeft(p);
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

    void preorderThenDelete(int x) {
        int a = preorderThenDelete(root, 0, x);
//        System.out.println(a);
    }

    int preorderThenDelete(Node p, int i, int x) {
        if (p == null) {
            return i;
        }
        if (p.right != null) {
            i++;
            if (i == x) {
                deleteByCopy(p.info);
                return i;
            }
        }
        if (i < x) {
            i = preorderThenDelete(p.left, i, x);
        }
        if (i < x) {
            i = preorderThenDelete(p.right, i, x);
        }
        return i;
    }

    void inorderThenRotate(int x) {
        int a = inorderThenRotate(root, 0, x);
//        System.out.println(a);
    }

    int inorderThenRotate(Node p, int i, int x) {
        if (p == null) {
            return i;
        }
        if (i < x) {
            i = inorderThenRotate(p.left, i, x);
        }
        if (p.right != null) {
            i++;
            if (i == x) {
                Node temp = parent(p);
                if (temp == null) {
                    root = rotateLeft(p);
                } else {
                    if (temp.right == p) {
                        temp.right = rotateLeft(p);
                    } else {
                        temp.left = rotateLeft(p);
                    }
                }
                return i;
            }
        }
        if (i < x) {
            i = inorderThenRotate(p.right, i, x);
        }
        return i;
    }

    void inorderThenDelete(int x) {
        int a = inorderThenDelete(root, 0, x);
//        System.out.println(a);
    }

    int inorderThenDelete(Node p, int i, int x) {
        if (p == null) {
            return i;
        }
        if (i < x) {
            i = inorderThenDelete(p.left, i, x);
        }
        if (p.right != null) {
            i++;
            if (i == x) {
                deleteByCopy(p.info);
                return i;
            }
        }
        if (i < x) {
            i = inorderThenDelete(p.right, i, x);
        }
        return i;
    }

    void postorderThenRotate(int x) {
        int a = postorderThenRotate(root, 0, x);
//        System.out.println(a);
    }

    int postorderThenRotate(Node p, int i, int x) {
        if (p == null) {
            return i;
        }
        if (i < x) {
            i = postorderThenRotate(p.left, i, x);
        }
        if (i < x) {
            i = postorderThenRotate(p.right, i, x);
        }
        if (p.right != null) {
            i++;
            if (i == x) {
                Node temp = parent(p);
                if (temp == null) {
                    root = rotateLeft(p);
                } else {
                    if (temp.right == p) {
                        temp.right = rotateLeft(p);
                    } else {
                        temp.left = rotateLeft(p);
                    }
                }
                return i;
            }
        }
        return i;
    }

    void postorderThenDelete(int x) {
        int a = postorderThenDelete(root, 0, x);
//        System.out.println(a);
    }

    int postorderThenDelete(Node p, int i, int x) {
        if (p == null) {
            return i;
        }
        if (i < x) {
            i = postorderThenDelete(p.left, i, x);
        }
        if (i < x) {
            i = postorderThenDelete(p.right, i, x);
        }
        if (p.right != null) {
            i++;
            if (i == x) {
                deleteByCopy(p.info);
                return i;
            }
        }
        return i;
    }

    Node parent(Node x) {
        Node cur = root;
        Node pre = null;
        while (cur != null) {
            if (cur.info == x.info) {
                break;
            } else if (cur.info.name.compareToIgnoreCase(x.info.name) > 0) {
                pre = cur;
                cur = cur.left;
            } else {
                pre = cur;
                cur = cur.right;
            }
        }
        return pre;
    }

    Node parent(String xName) {
        Node cur = root;
        Node pre = null;
        while (cur != null) {
            if (cur.info.name.equalsIgnoreCase(xName)) {
                break;
            } else if (cur.info.name.compareToIgnoreCase(xName) > 0) {
                pre = cur;
                cur = cur.left;
            } else {
                pre = cur;
                cur = cur.right;
            }
        }
        return pre;
    }

    int balanceFactor(Node p) {
        return height(p.right) - height(p.left);
    }

    void breadthWithFactor() {
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
            visitWithFactor(p);
        }
    }

    void visitWithFactor(Node p) {
        System.out.print(p.info + ", " + balanceFactor(p));
    }

    boolean isHeightBalanced() {
        if (root == null) {
            return true;
        }
        MyQueue q = new MyQueue();
        q.enqueue(root);
        Node p;
        while (!q.isEmpty()) {
            p = q.dequeue();
            if (balanceFactor(p) > 1 || balanceFactor(p) < -1) {
                return false;
            }
            if (p.left != null) {
                q.enqueue(p.left);
            }
            if (p.right != null) {
                q.enqueue(p.right);
            }
        }
        return true;
    }

    int getLevel(Node p) {
        if (p == null || root == null) {
            return 0;
        }
        int count = 0;
        Node temp = root;
        while (temp != null) {
            count++;
            if (temp == p) {
                break;
            } else if (temp.info.name.compareToIgnoreCase(p.info.name) > 0) {
                temp = temp.left;
            } else {
                temp = temp.right;
            }
        }
        if (temp == null) {
            return 0;
        }
        return count;
    }

    void breadthWithLevel() {
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
            visitWithLevel(p);
        }
        System.out.println("");
    }

    void visitWithLevel(Node p) {
        System.out.print(p.info + ", " + getLevel(p));
    }

    Person[] toArray() {
        Person[] result = new Person[count(root)];
        toArray(root, result, 0);
        return result;
    }

    int toArray(Node p, Person[] value, int size) {
        if (p == null) {
            return size;
        }
        size = toArray(p.left, value, size);
        value[size++] = p.info;
        size = toArray(p.right, value, size);
        return size;
    }

    void balance(Person[] value, int first, int last) {
        if (first <= last) {
            int middle = (first + last) / 2;
            insert(value[middle].name, value[middle].age);
            balance(value, first, middle - 1);
            balance(value, middle + 1, last);

        }
    }

    void balance(Person[] value) {
        clear();
        balance(value, 0, value.length - 1);
    }
}
