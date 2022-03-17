/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linkedLiskObj;

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
public class MyList {

    Node head;
    Node tail;
    int size;

    public MyList() {
        this.head = null;
        this.tail = null;
    }

    boolean isEmpty() {
        return head == null;
    }

    void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    int size() { //10
        return size;
    }

    //1
    void addFirst(Person x) {
        Node p = new Node(x);
        if (head == null) {
            head = p;
            tail = p;
            size += 1;
        } else {
            p.next = head;
            head = p;
            size += 1;
        }
    }

    //2
    void addLast(Person x) {
        Node p = new Node(x);
        if (tail == null) {
            tail = p;
            head = p;
            size += 1;
        } else {
            tail.next = p;
            tail = p;
            size += 1;
        }
    }

    //3
    void addAfter(Node p, Person x) {
        if (size == 0) {
            return;
        }
        Node q = head;
        while (q != p) {
            q = q.next;
            if (q == null) {
                break;
            }
        }
        if (q != null) {
            Node a = new Node(x);
            a.next = q.next;
            q.next = a;
            size++;
            if (q == tail) {
                tail = a;
            }
        }

    }

    //4
    void traverse() {
        Node q = head;
        while (q != null) {
            System.out.print(q.info);
            q = q.next;
            System.out.println("");
        }
    }

    //5
    Person deleteFromHead() {
        if (head == null) {
            return null;
        }
        Node p = head;
        head = head.next;
        size--;
        return p.info;
    }

    //6
    Person deleteFromTail() {
        if (head == null) {
            return null;
        }
        Node p = head;
        if (size == 1) {
            head = null;
            tail = null;
            size--;
            return p.info;
        }

        while (p.next != tail) {
            p = p.next;
        }
        Node n = tail;
        p.next = null;
        tail = p;
        size--;
        return n.info;
    }

    //7
    Person deleteAfter(Node p) {
        if (p.next != null) {
            Node i = p.next;
            p.next = p.next.next;
            size--;
            return i.info;
        } else {
            return null;
        }

    }

    //8
    void dele(Person x) {
        if (head.info.id.equalsIgnoreCase(x.id)) {
            deleteFromHead();
            return;
        }

        if (head == null) {
            return;
        }
        Node pre = null;
        Node cur = head;
        while (!cur.info.id.equalsIgnoreCase(x.id)) {
            pre = cur;
            cur = cur.next;
            if (cur == null) {
                break;
            }
        }
        if (cur != null) {
            if (cur == tail) {
                tail = pre;
            }
            pre.next = cur.next;
            size--;
        }
    }

    //9
    Node search(Person x) {
        Node p = head;
        if (head == null) {
            return null;
        }
        while (!p.info.id.equalsIgnoreCase(x.id)) {
            p = p.next;
            if (p == null) {
                break;
            }
        }
        return p;
    }

    //11 delete an i-th node on the list. Besure that such a node exists.
    void deleIndex(int i) {

        if (size >= i) {
            if (i == 1) {
                deleteFromHead();
            } else if (size == i) {
                deleteFromTail();
            } else {
                int count = 1;
                Node temp = head;
                while (temp != null) {
                    count++;
                    if (count == i) {
                        temp.next = temp.next.next;
                        size--;
                        break;
                    }
                    temp = temp.next;
                }
            }
        }

    }

    //12 sort theo ID
    void sort() {
        //c1: xuất ra và sắp xếp trên mảng
        Person[] arr = new Person[size];
        Node p = head;
        for (int i = 0; i < arr.length; i++) {
            arr[i] = p.info;
            p = p.next;
        }
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j].id.compareToIgnoreCase(arr[j+1].id) > 0) {
                    Person temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        head = new Node(arr[0]);
        p = head;
        for (int i = 1; i < arr.length; i++) {
            p.next = new Node(arr[i]);
            p = p.next;
            if(i == arr.length-1){
                tail = p;
            }
        }

        //c2: insertion sort
    }

    //13 delete node p if it exists in the list.
    void deleNode(Node p) {
        if (head == null) {
            return;
        }
        if (p == head) {
            deleteFromHead();
        }
        Node pre = null;
        Node cur = head;
        while (cur != p) {
            pre = cur;
            cur = cur.next;
            if (cur == null) {
                break;
            }
        }
        if (cur != null) {
            if (cur == tail) {
                tail = pre;
            }
            pre.next = cur.next;
            size--;
        }
    }

    //14 create and return array containing info of all nodes in the list.
    Person[] toArray() {
        Node p = head;
        Person[] arr;
        arr = new Person[size];
        for (int i = 0; i < size; i++) {
            arr[i] = p.info;
            p = p.next;
        }
        return arr;
    }

    //15 Merge two ordered singly linked lists of integers into one ordered list
    MyList mergeTwoOrderedList(MyList my, MyList my1) {
        MyList res = new MyList();
        Node cur1 = my.head;
        Node cur2 = my1.head;
        if (cur1.info > cur2.info) {
            res.head = cur2;
            res.tail = cur2;
            cur2 = cur2.next;
        } else {
            res.head = cur1;
            res.tail = cur1;
            cur1 = cur1.next;

        }
        while (true) {
            if (cur1 == null) {
                res.tail.next = cur2;
                break;
            }
            if (cur2 == null) {
                res.tail.next = cur1;
                break;
            }
            if (cur1.info > cur2.info) {
                res.tail.next = cur2;
                res.tail = res.tail.next;
                cur2 = cur2.next;
            } else {
                res.tail.next = cur1;
                res.tail = res.tail.next;
                cur1 = cur1.next;

            }
        }
        res.size = my.size + my1.size;
        return res;
    }

    //16  add a node with value x  before the node p.
    void addBefore(Node p, Person x) {
        if (head == p) {
            addFirst(x);
            return;
        }
        if (head == null) {
            return;
        }
        Node pre = null;
        Node cur = head;
        while (cur != p) {
            pre = cur;
            cur = cur.next;
            if (cur == null) {
                break;
            }
        }
        if (cur != null) {
            Node temp = new Node(x);
            pre.next = temp;
            temp.next = cur;
            size++;
        }

    }

    //17 Attach a singly linked list to the end of another singly linked list.
    void attach(MyList my1) {
        tail.next = my1.head;
    }

    //18 max
    int max() {
        int max = Integer.MIN_VALUE;
        if (head == null) {
            return 0;
        }
        Node q = head;
        while (q != null) {
            max = (max > q.info) ? max : q.info;
            q = q.next;
        }
        return max;
    }

    //19 min
    int min() {
        int min = Integer.MAX_VALUE;
        if (head == null) {
            return 0;
        }
        Node q = head;
        while (q != null) {
            min = (min < q.info) ? min : q.info;
            q = q.next;
        }
        return min;
    }
    //20 sum

    int sum() {
        int sum = 0;
        Node p = head;
        while (p != null) {
            sum += p.info;
            p = p.next;
        }
        return sum;
    }

    //21 average
    int avg() {
        return sum() / size;
    }

    //22 check and return true if the list is sorted, return false if the list is not sorted.
    boolean sorted() {
        if (head == null) {
            return true;
        }
        if (size == 1) {
            return true;
        }
        Node pre = head;
        Node cur = head.next;
        while (cur != null) {
            if (pre.info > cur.info) {
                return false;
            }
            pre = cur;
            cur = cur.next;
        }
        return true;
    }

    //23 insert node with value x into sorted list so that the new list is sorted.
    void insert(Person x) {
        if (head == null) {
            addFirst(x);
            return;
        }
        if (head.info >= x) {
            addFirst(x);
            return;
        }
        if (size == 1) {
            addLast(x);
            return;
        }
        Node cur = head;
        Node pre = null;
        while (cur.info < x) {
            pre = cur;
            cur = cur.next;
            if (cur == null) {
                break;
            }
        }
        if (cur == null) {
            addLast(x);
        } else {
            Node temp = new Node(x);
            temp.next = cur;
            pre.next = temp;
            size++;
        }
    }

    //24 reverse a single linked list
    void reverse() {
        Node pre = null;
        Node cur = head;
        Node next = null;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        tail = head;
        head = pre;
    }

    //25 Check whether two singly linked list have the same contents.
    boolean checkContent(MyList my, MyList my1) {
        if (my.size() != my1.size()) {
            return false;
        }
        Node cur1 = my.head;
        Node cur2 = my1.head;
        while (cur1 != null) {
            if (cur1.info != cur2.info) {
                return false;
            }
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return true;
    }

    void readFromFile(String filename) {
        try {
            File f = new File(filename);
            BufferedReader br = new BufferedReader(new FileReader(f));
            String line;
            while ((line = br.readLine()) != null) {
                StringTokenizer stk = new StringTokenizer(line, ",");
                String id = stk.nextToken().trim();
                String name = stk.nextToken().trim();
                int age = Integer.parseInt(stk.nextToken().trim());
                Person temp = new Person(name, id, age);
                addLast(temp);
            }
            br.close();
        } catch (IOException | NumberFormatException e) {
            System.out.println(e);
        }

    }

    void saveToFile(String filename) {
        if (this.size() == 0) {
            System.out.println("Empty list");
            return;
        }
        try {
            File f = new File(filename);
            FileWriter fw = new FileWriter(f);
//            PrintWriter pw = new PrintWriter(fw);
            Node p = head;
            while (p != null) {
//                pw.println(p.info.id+"|"+p.info.name+"|"+p.info.age);
//                p = p.next;

                fw.write(p.info.id + ", " + p.info.name + ", " + p.info.age + System.lineSeparator());
                p = p.next;
            }
//            pw.close();
            fw.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
