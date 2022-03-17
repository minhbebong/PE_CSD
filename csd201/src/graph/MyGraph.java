/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 *
 * @author Admin
 */
public class MyGraph {

    int n; // sl dinh
    int[][] a; // khai bao mang a(mảng liền kề hoặc trọng số)
    char[] v; //ten cac dinh trong do thi
    int INF = Integer.MAX_VALUE;

    public MyGraph() {
        v = "ABCDEFGHIJKLMN".toCharArray();
    }

    void setData(int[][] b) {
        n = b.length;
        a = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                a[i][j] = b[i][j];
            }
        }
    }

    void display() {
        for (int i = 0; i < n; i++) {
            System.out.print(v[i] + ": ");
            for (int j = 0; j < n; j++) {
                if (a[i][j] > 0 && a[i][j] < INF) {
                    System.out.print(v[j] + " ");
                }
            }
            System.out.println("");
        }
    }

    int deg(int i) { //degree
        int s, j;
        s = 0;
        for (j = 0; j < n; j++) {
            s += a[i][j];
        }
        s += a[i][i];
        return (s);
    }

    void breadthFirst(int k) {
        MyQueue q = new MyQueue();
        int i, h;
        boolean[] enqueued = new boolean[n];
        for (int j = 0; j < n; j++) {
            enqueued[j] = false;
        }
        q.enqueue(new Integer(k));
        enqueued[k] = true;
        while (!q.isEmpty()) {
            h = q.dequeue();
            visit(h);
            for (int j = 0; j < n; j++) {
                if (!enqueued[j] && a[h][j] > 0 && a[h][j] < INF) {
                    q.enqueue(new Integer(j));
                    enqueued[j] = true;
                }
            }
        }
        System.out.println("");
    }

    void depthFirst(boolean[] visited, int i) {
        visit(i);
        visited[i] = true;
        for (int j = 0; j < n; j++) {
            if (a[i][j] > 0 && a[i][j] < INF && (!visited[j])) {
                depthFirst(visited, j);
            }
        }
    }

    void depthFirst(int k) {
        int i;
        boolean[] visited = new boolean[n];
        for (int j = 0; j < n; j++) {
            visited[j] = false;
        }
        depthFirst(visited, k);
        for (int j = 0; j < n; j++) {
            if (!visited[j]) {
                depthFirst(visited, j);
            }
        }
        System.out.println("");
    }

    void visit(int x) {
        System.out.print(v[x] + " ");
    }

    void dijkstra(int k, int h) {
        //tim duong di ngan nhat tu k den cac diem con lai
        boolean[] checked = new boolean[n];
        int[] cost = new int[n];
        int[] pre = new int[n];
        for (int i = 0; i < n; i++) {
            checked[i] = false;
            cost[i] = 99;
            pre[i] = -1;
        }
        cost[k] = 0;
        int u = -1;
        for (int i = 0; i < n; i++) {
            //tim min voi checked = false
            int min = 99;
            for (int j = 0; j < n; j++) {
                if (checked[j] == false && cost[j] < min) {
                    min = cost[j];
                    u = j;
                }
            }
            checked[u] = true;
            for (int j = 0; j < n; j++) {
                if (a[u][j] > 0 && a[u][j] < 99 && checked[j] == false) {
                    if (cost[j] > cost[u] + a[u][j]) {
                        cost[j] = cost[u] + a[u][j];
                        pre[j] = u;
                    }
                }
            }
        }
        Stack path = new Stack();
        int i = h;
        while (true) {
            path.push(i);
            if (i == k) {
                break;
            }
            i = pre[i];
        }
        int[] arr = new int[n];
        int size = 0;
        while (!path.isEmpty()) {
            int temp = path.pop();
            arr[size++] = temp;
        }
        //display
//        for (int i = 0; i < n; i++) {
//            System.out.println("Cost to " + v[i] + " = " + cost[i]);
//        }
        //        //print (0)A->(9)C->(2)F->(9)E
        System.out.print(v[arr[0]] + "");
        for (int j = 1; j < size; j++) {
            System.out.print(" -> (" + a[arr[j - 1]][arr[j]] + ")" + v[arr[j]]);
        }
        System.out.println("");
    }

    void floyd() { //wrong
        int[][] d;
        d = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                d[i][j] = a[i][j];
            }
        }
        int[][] p = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                p[i][j] = 0;
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int l = 0; l < n; l++) {
                    if (d[j][l] > d[j][i] + d[i][l]) {
                        d[j][l] = d[j][i] + d[i][l];
                    }
                }
            }
        }

    }

    void prim(int k) {
        //tim duong di ngan nhat tu k den cac diem con lai
        boolean[] checked = new boolean[n];
        int[] cost = new int[n];
        int[] pre = new int[n];
        for (int i = 0; i < n; i++) {
            checked[i] = false;
            cost[i] = INF;
            pre[i] = -1;
        }
        cost[k] = 0;
        pre[k] = k;
        int u = -1;
        for (int i = 0; i < n; i++) {
            //tim min voi checked = false
            int min = INF;
            for (int j = 0; j < n; j++) {
                if (checked[j] == false && cost[j] < min) {
                    min = cost[j];
                    u = j;
                }
            }
            checked[u] = true;
            for (int j = 0; j < n; j++) {
                if (a[u][j] > 0 && a[u][j] < INF && checked[j] == false) {
                    if (cost[j] > a[u][j]) {
                        cost[j] = a[u][j];
                        pre[j] = u;
                    }
                }
            }
        }
        //display
        for (int i = 0; i < n; i++) {
            System.out.println(v[pre[i]] + " to " + v[i] + ": " + cost[i]);
        }
    }

    static int find(int i, int parent[]) {
        while (parent[i] != i) {
            i = parent[i];
        }
        return i;
    }

    void union(int i, int j, int parent[]) {
        int x = find(i, parent);
        int y = find(j, parent);
        parent[x] = y;
    }

    void kruskal() {
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
        int minCost = 0;
        int edgeCount = 0;
        while (edgeCount < n - 1) {
            int min = INF;
            int x = -1;
            int y = -1;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (find(i, parent) != find(j, parent) && a[i][j] < min) {
                        min = a[i][j];
                        x = i;
                        y = j;
                    }
                }
            }
            union(x, y, parent);
            System.out.println("Edge(" + edgeCount + "): (" + v[x] + ", " + v[y] + ") cost: " + min);
            edgeCount++;
            minCost += min;
        }
        System.out.println("Total cost: " + minCost);
    }

    void euler(int k) {
        int[][] cost = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                cost[i][j] = a[i][j];
            }
        }
        Stack st = new Stack();
        int[] E = new int[100];
        int size = 0;
        st.push(k);
        while (!st.isEmpty()) {
            int r = st.top();
            int temp = -1;
            for (int i = 0; i < n; i++) {
                if (cost[r][i] != 0 && r != i) {
                    temp = i;
                    break;
                }
            }
            if (temp == -1) {
                E[size++] = st.pop();
            } else {
                st.push(temp);
                cost[r][temp] -= 1;
                cost[temp][r] -= 1;
            }
        }
        for (int i = 0; i < size; i++) {
            System.out.print(v[E[i]] + " ");
        }
    }

    void fvisit(int i, RandomAccessFile f) throws Exception {
        f.writeBytes(" " + v[i]);
    }

    void dijkstra(int k, int h, RandomAccessFile f) throws Exception {
        boolean[] checked = new boolean[n];
        int[] cost = new int[n];
        int[] pre = new int[n];
        for (int i = 0; i < n; i++) {
            checked[i] = false;
            cost[i] = 999;
            pre[i] = -1;
        }
        cost[k] = 0;
        int u = -1;
        for (int i = 0; i < n; i++) {
            int min = 999;
            for (int j = 0; j < n; j++) {
                if (checked[j] == false && cost[j] < min) {
                    min = cost[j];
                    u = j;
                }
            }
            checked[u] = true;
            for (int j = 0; j < n; j++) {
                if (a[u][j] > 0 && a[u][j] < 999 && checked[j] == false) {
                    if (cost[j] > cost[u] + a[u][j]) {
                        cost[j] = cost[u] + a[u][j];
                        pre[j] = u;
                    }
                }
            }
        }
        Stack path = new Stack();
        int i = h;
        while (true) {
            path.push(i);
            if (i == k) {
                break;
            }
            i = pre[i];
        }
        int[] arr = new int[n];
        int size = 0;
        while (!path.isEmpty()) {
            int temp = path.pop();
            arr[size++] = temp;
        }
        for (int j = 0; j < size; j++) {
            fvisit(arr[j], f);
        }
        f.writeBytes("\r\n");
        for (int j = 0; j < size; j++) {
            f.writeBytes(" " + cost[arr[j]]);
        }
        f.writeBytes("\r\n");
    }

    void euler(int k, RandomAccessFile f) throws Exception { //matrix 0 1
        int[][] cost = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                cost[i][j] = a[i][j];
            }
        }
        Stack st = new Stack();
        int[] E = new int[100];
        int size = 0;
        st.push(k);
        while (!st.isEmpty()) {
            int r = st.top();
            int temp = -1;
            for (int i = 0; i < n; i++) {
                if (cost[r][i] != 0 && r != i) {
                    temp = i;
                    break;
                }
            }
            if (temp == -1) {
                E[size++] = st.pop();
            } else {
                st.push(temp);
                cost[r][temp] -= 1;
                cost[temp][r] -= 1;
            }
        }
        for (int i = 0; i < size; i++) {
            fvisit(E[i], f);
        }
    }

    int connectedComponent() { //for matrix 0 1
        int count = 0;
        boolean[] checked = new boolean[n];
        for (int i = 0; i < n; i++) {
            checked[i] = false;
        }
        count++;
        connectedComponent(checked, 1);
        for (int i = 0; i < n; i++) {
            if (checked[i] == false) {
                count++;
                connectedComponent(checked, i);
            }
        }
        return count;
    }

    void connectedComponent(boolean[] checked, int k) {
        Queue q = new Queue();
        q.enqueue(k);
        checked[k] = true;
        int temp;
        while (!q.isEmpty()) {
            temp = q.dequeue();
            for (int i = 0; i < n; i++) {
                if (checked[i] == false && a[temp][i] > 0) {
                    q.enqueue(i);
                    checked[i] = true;
                }
            }
        }
    }

    void breadth(boolean[] en, int i, RandomAccessFile f) throws Exception {
        Queue q = new Queue();
        int r, j;
        q.enqueue(i);
        en[i] = true;
        while (!q.isEmpty()) {
            r = q.dequeue();
            fvisit(r, f);
            for (j = 0; j < n; j++) {
                if (!en[j] && a[r][j] > 0) {
                    q.enqueue(j);
                    en[j] = true;
                }
            }
        }
    }

    void breadth(int k, RandomAccessFile f) throws Exception {
        boolean[] en = new boolean[20];
        int i;
        for (i = 0; i < n; i++) {
            en[i] = false;
        }
        breadth(en, k, f);
        for (i = 0; i < n; i++) {
            if (!en[i]) {
                breadth(en, i, f);
            }
        }
    }

    void depth(boolean[] visited, int k, RandomAccessFile f) throws Exception {
        fvisit(k, f);
        visited[k] = true;
        for (int i = 0; i < n; i++) {
            if (!visited[i] && a[k][i] > 0) {
                depth(visited, i, f);
            }
        }
    }

    void depth(int k, RandomAccessFile f) throws Exception {
        boolean[] visited = new boolean[20];
        int i;
        for (i = 0; i < n; i++) {
            visited[i] = false;
        }
        depth(visited, k, f);
        for (i = 0; i < n; i++) {
            if (!visited[i]) {
                depth(visited, i, f);
            }
        }
    }

    void checkEulerCycle(RandomAccessFile f) throws IOException {
        boolean undirected = true;
        boolean connect = true;
        boolean allEvenDeg = true;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (a[i][j] != a[j][i]) {
                    undirected = false;
                }
            }
        }
        if (connectedComponent() != 1) {
            connect = false;
        }
        for (int i = 0; i < n; i++) {
            if (deg(i) % 2 != 0) {
                allEvenDeg = false;
            }
        }
        if (undirected == true) {
            f.writeBytes("The graph is undirected.\r\n");
        } else {
            f.writeBytes("The graph is directed.\r\n");
        }
        if (connect == true) {
            f.writeBytes("The graph is connected.\r\n");
        } else {
            f.writeBytes("The graph is not connected.\r\n");
        }
        if (allEvenDeg == true) {
            f.writeBytes("All vertices have even degree.\r\n");
        } else {
            f.writeBytes("The graph has a vertex with odd degree.\r\n");
        }

        if (undirected == true && connect == true && allEvenDeg == true) {
            f.writeBytes("Conditions for Euler's cycle are satisfied.\r\n");
        } else {
            f.writeBytes("Conditions for Euler's cycle are not satisfied.\r\n");
        }
    }

    void fvisitDeg(int i, RandomAccessFile f) throws Exception {
        f.writeBytes(" " + v[i] + "(" + deg[i] + ")");
    }
}
