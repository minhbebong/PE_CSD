/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exeLinkedList;

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

    //addLast with condition
    void addLast(String xName, int xAge) {
        if (xName.charAt(0) == 'B' || xAge < 17) {
            return;
        }
        Node p = new Node(new Person(xName, xAge));
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

    void traverse() {
        Node q = head;
        while (q != null) {
            System.out.print(q.info);
            q = q.next;
            System.out.println("");
        }
        System.out.println("");
    }

    //deleteFirstNode match condition
    void deleteFirstNodeWithCon() {
        //search for this Node
        Node temp = searchForFirstWithCon(20);

        //If exist, delete node
        if (temp != null) {
            deleNode(temp);
        }
    }

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

    Person deleteFromHead() {
        if (head == null) {
            return null;
        }
        Node p = head;
        head = head.next;
        size--;
        return p.info;
    }

    //search for age
    Node searchForFirstWithCon(int xAge) {
        Node p = head;
        if (head == null) {
            return null;
        }
        while (p.info.age != xAge) {
            p = p.next;
            if (p == null) {
                break;
            }
        }
        return p;
    }

    //Display first n Object having condition
    void disWithCon() {
        int count = 0;
        Node q = head;
        while (q != null && count != 5) {
            if (q.info.age > 22) {
                System.out.print(q.info + " ");
                count++;
            }
            q = q.next;
        }
    }

    //Max
    int maxAge() {
        int max = Integer.MIN_VALUE;
        if (head == null) {
            return 0;
        }
        Node q = head;
        while (q != null) {
            max = (max > q.info.age) ? max : q.info.age;
            q = q.next;
        }
        return max;
    }

    //find max < upper
    int maxAgeLTUpper(int upper) {
        int max = 0;
        if (head == null) {
            return 0;
        }
        Node q = head;
        while (q != null) {
            max = (max < q.info.age && q.info.age < upper) ? q.info.age : max;
            q = q.next;
        }
        return max;
    }

    //travse with conditon
    void traverseWithCon() {
        Node q = head;
        int n = maxAgeLTUpper(maxAge());
        while (q != null) {
            if (q.info.age == n) {
                System.out.print(q.info + " ");
            }
            q = q.next;
        }
    }

    //sort using interchange
    void sort() { //sort by age
        Node pi, pj;
        Person x;
        pi = head;
        while (pi != null) {
            pj = pi.next;
            while (pj != null) {
                if (pj.info.age > pi.info.age) {
                    x = pi.info;
                    pi.info = pj.info;
                    pj.info = x;
                }
                pj = pj.next;
            }
            pi = pi.next;
        }
    }

    //sort index
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
                if (pj.info.name.compareToIgnoreCase(pi.info.name) < 0) {
                    Person x = pi.info;
                    pi.info = pj.info;
                    pj.info = x;
                }
                pj = pj.next;
            }
            pi = pi.next;
        }
    }

    //delete Last Node with condition
    void deleteLastNodeWithCon() {
        Node temp = searchForLastWithCon();
        if (temp != null) {
            deleNode(temp);
        }
    }

    Node searchForLastWithCon() {
        Node result = null;
        Node q = head;
        while (q != null) {
            if (q.info.age == 20) {
                result = q;
            }
            q = q.next;
        }
        return result;
    }

    //display n last
    void displayLast5WithCon() {
        int n = countWithCon();
        int count = 1;
        Node q = head;
        while (q != null) {
            if (q.info.age > 22) {
                if (count > (n - 5)) {
                    System.out.print(q.info);
                }
                count++;
            }
            q = q.next;
        }
    }

    int countWithCon() {
        int count = 0;
        Node q = head;
        while (q != null) {
            if (q.info.age > 22) {
                count++;
            }
            q = q.next;
        }
        return count;
    }

    void readFromFile(String filename) {
        try {
            File f = new File(filename);
            BufferedReader br = new BufferedReader(new FileReader(f));
            String line;
            while ((line = br.readLine()) != null) {
                StringTokenizer stk = new StringTokenizer(line, "|");
//                String id = stk.nextToken().trim();
                String name = stk.nextToken().trim();
//                System.out.println(name);
                int age = Integer.parseInt(stk.nextToken().trim());

                addLast(name, age);
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

                fw.write(p.info.name + ", " + p.info.age + System.lineSeparator());
                p = p.next;
            }
//            pw.close();
            fw.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    //add index
    void addIndex(String xName, int xAge, int index) {
        if (index == 1) {
            addFirst(new Person(xName, xAge));
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
            Node temp = new Node(new Person(xName, xAge));
            temp.next = p.next;
            p.next = temp;
        }
    }

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

    void deleIndex(int i) { //need to modify

        if (size >= i) {
            if (i == 0) {
                deleteFromHead();
            } else if (size == i - 1) {
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
}
