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

    Node search(int x) {
        if (root == null) {
            return null;
        }
        Node p = root;
        while (true) {
            if (x == p.info) {
                return p;
            } else if (x > p.info) {
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

    void insert(int x) {
        if (root == null) {
            Node p = new Node(x);
            root = p;
            return;
        }
        Node p = root;
        while (true) {
            if (x == p.info) {
                System.out.println("X is already exist, no insertion");
                return;
            } else if (x > p.info) {
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
        System.out.print(p.info + " ");
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

    void deleteByMerging(int x) {
        Node p = root;
        Node parent = null;
        while (p != null) {
            if (p.info == x) {
                break;
            }
            parent = p;
            if (p.info > x) {
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

    void deleteByCopy(int x) {
        Node p = root;
        Node parent = null;
        while (p != null) {
            if (p.info == x) {
                break;
            }
            parent = p;
            if (p.info > x) {
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
    Node min(){
        return min(root);
    }
    Node min(Node p) {
        if (p == null) {
            return null;
        }
        Node pre = null;
        Node cur = p;
        while (cur != null) {
            pre = cur;
            cur = cur.left;
        }
        return pre;
    }
    Node max(){
        return max(root);
    }
    Node max(Node p) {
        if (p == null) {
            return null;
        }
        Node pre = null;
        Node cur = p;
        while (cur != null) {
            pre = cur;
            cur = cur.right;
        }
        return pre;
    }
    Node secondHighest(Node p) {
        if (p == null) {
            return null;
        }
        Node pre = null;
        Node cur = p;
        while (cur.right != null) {
            pre = cur;
            cur = cur.right;
        }
        if(pre == null){
            if(cur.left == null){
                return null;
            } else {
                return max(cur.left);
            }
        }
        if(cur.left == null){
            return pre;
        } else {
            return max(cur.left);
        }
    }
    Node secondSmallest(Node p){
        if(p == null){
            return null;
        }
        Node pre = null;
        Node cur = p;
        while(cur.left != null){
            pre = cur;
            cur = cur.left;
        }
        if(pre == null){
            if(cur.right == null){
                return null;
            } else {
                return min(cur.right);
            }
        }
        if(cur.right == null){
            return pre;
        } else {
            return min(cur.right);
        }
    }

    int sum() {
        if (root == null) {
            return 0;
        }
        MyQueue queue = new MyQueue();
        int sum = 0;
        queue.enqueue(root);
        while (!queue.isEmpty()) {
            Node temp = queue.dequeue();
            sum += temp.info;
            if (temp.left != null) {
                queue.enqueue(temp.left);
            }
            if (temp.right != null) {
                queue.enqueue(temp.right);
            }
        }
        return sum;
    }

    int sum(Node p) {
        if (p == null) {
            return 0;
        }
        return p.info + sum(p.left) + sum(p.right);
    }

    int avg() {
        return sum() / count();
    }

    int height(Node p) {
        if (p == null) {
            return 0;
        }
        return 1 + Math.max(height(p.left), height(p.right));
    }

    int cost(Node p) {
        if (p == null) {
            return 0;
        }
        return p.info + Math.max(cost(p.left), cost(p.right));
    }

    int mystery(Node p) {
        if (p == null) {
            return 0;
        }
        return Math.max(mystery(p.left), mystery(p.right));
    }

    Node parent(Node x) {
        if (root == null) {
            return null;
        }
        Node cur = root;
        Node pre = null;
        while (cur != null) {
            if (cur.info == x.info) {
                break;
            } else if (cur.info > x.info) {
                pre = cur;
                cur = cur.left;
            } else {
                pre = cur;
                cur = cur.right;
            }
        }
        return pre;
    }

    Node leftMost(Node p) {
        Node pre = null;
        Node cur = p;
        while (cur != null) {
            pre = cur;
            cur = cur.left;
        }
        return pre;
    }

    Node rightMost(Node p) {
        Node pre = null;
        Node cur = p;
        while (cur != null) {
            pre = cur;
            cur = cur.right;
        }
        return pre;
    }

    boolean isLeaf(Node p) {
        return p.left == null && p.right == null;
    }

    int [] toArray(){
        int [] result = new int [count()];
        toArray(root, result, new Index(0));
        return result;
    }

    void toArray(Node p, int []value, Index index) {
        if(p.left != null){
            toArray(p.left, value, index);
        }
        value[index.info] = p.info;
        index.info++;
        if(p.right != null){
            toArray(p.right, value, index);
        }
        
    }

    void balance(int data[], int first, int last) {
        if (first <= last) {
            int middle = (first + last) / 2;
            insert(data[middle]);
            balance(data, first, middle - 1);
            balance(data, middle + 1, last);

        }
    }

    void balance(int data[]) {
        balance(data, 0, data.length - 1);
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

    boolean isBalance(Node a) {
        return Math.abs(height(a.left) - height(a.right)) <= 1;
    }
    int getLevel(Node p){
        if(p == null || root == null){
            return 0;
        }
        int count = 0;
        Node temp = root;
        while(temp != null){
            count++;
            if(temp == p){
                break;
            } else if(temp.info > p.info){
                temp = temp.left;
            } else {
                temp = temp.right;
            }
        }
        if(temp == null){
            return 0;
        }
        return count;
    }
}
