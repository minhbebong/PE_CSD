/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avltree;

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
        root = insert(root, x);
    }

    Node insert(Node p, int x) {
        if (p == null) {
            p = new Node(x);
        } else if (x > p.info) {
            p.right = insert(p.right, x);
            if (height(p.right) - height(p.left) == 2) {
                if (p.right.info > x) {
                    p = doubleRotateRightLeft(p);
                } else {
                    p = rotateLeft(p);
                }
            }

        } else if (x < p.info) {
            p.left = insert(p.left, x);
            if (height(p.right) - height(p.left) == -2) {
                if (p.left.info < x) {
                    p = doubleRotateLeftRight(p);
                } else {
                    p = rotateRight(p);
                }
            }
        } else {
        }
        p.height = max(height(p.left), height(p.right)) + 1;
        return p;
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
        System.out.print("[" + p.info + ", " + p.height + "]");
    }

    int count(Node p) {
        if (p == null) {
            return 0;
        }
        return 1 + count(p.left) + count(p.right);
    }

    int count() {
        return count(root);
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
//            if (isLeaf(rightMost(p.left)) && !isLeaf(leftMost(p.right))) {
//                Node temp = rightMost(p.left);
//                if (temp == p.left) {
//                    p.info = temp.info;
//                    p.left = null;
//                } else {
//                    int n = temp.info;
//                    temp = parent(rightMost(p.left));
//                    temp.right = null;
//                    p.info = n;
//                }
//            } else if (!isLeaf(rightMost(p.left)) && isLeaf(leftMost(p.right))) {
//                Node temp = leftMost(p.right);
//                if (temp == p.right) {
//                    p.info = temp.info;
//                    p.right = null;
//                } else {
//                    int n = temp.info;
//                    temp = parent(leftMost(p.right));
//                    temp.left = null;
//                    p.info = n;
//                }
//            } else {
//                Node temp = rightMost(p.left);
//                if (temp == p.left) {
//                    p.info = temp.info;
//                    p.left = temp.left;
//                } else {
//                    int n = temp.info;
//                    temp = parent(rightMost(p.left));
//                    temp.right = rightMost(p.left).left;
//                    p.info = n;
//                }
//            }
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
        return p.height;
    }

    Node rotateLeft(Node a) {
        Node b = a.right;
        a.right = b.left;
        b.left = a;

        a.height = max(height(a.left), height(a.right)) + 1;
        b.height = max(height(b.right), a.height) + 1;
        return b;
    }

    Node rotateRight(Node a) {
        Node b = a.left;
        a.left = b.right;
        b.right = a;

        a.height = max(height(a.left), height(a.right)) + 1;
        b.height = max(height(b.left), a.height) + 1;
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

    int max(int a, int b) {
        return (a > b) ? a : b;
    }
    void delete(int x){
        root = delete(root, x);
    }
    Node delete(Node p, int x) {
        if (p == null) {
            return null;
        } else if (x < p.info) {
            p.left = delete(p.left, x);
            if (height(p.right) - height(p.left) == 2) {
                if (height(p.right.right) - height(p.right.left) < 0) {
                    p = doubleRotateRightLeft(p);
                } else {
                    p = rotateLeft(p);
                }
            }
        } else if (x > p.info) {
            p.right = delete(p.right, x);
            if (height(p.right) - height(p.left) == -2) {
                if (height(p.left.right) - height(p.left.left) > 0) {
                    p = doubleRotateLeftRight(p);
                } else {
                    p = rotateRight(p);
                }
            }
        } else {
            //1. p la Node la
            if ((p.left == null) && (p.right == null)) {
                return null;
            }
            //2. p chi co 1 con(trai hoac phai)
            if ((p.left != null) && (p.right == null)) {
                p = p.left;
            }
            if ((p.left == null) && (p.right != null)) {
                p = p.right;
            }
            //3. P co 2 con
            if ((p.left != null) && (p.right != null)) {
                Node rm = p.left;
                while (rm.right != null) {
                    rm = rm.right;
                }
                int key = rm.info;
                p.info = key;
                p.left = delete(p.left, key);
                if (height(p.right) - height(p.left) == 2) {
                if (height(p.right.right) - height(p.right.left) < 0) {
                    p = doubleRotateRightLeft(p);
                } else {
                    p = rotateLeft(p);
                }
            }
            }
        }
        p.height = max(height(p.left), height(p.right)) + 1;
        return p;
    }
}
