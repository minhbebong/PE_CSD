/* This program contains 2 parts: (1) and (2)
   YOUR TASK IS TO COMPLETE THE PART  (2)  ONLY
 */
//(1)============================================
import java.io.*;
import java.util.*;
//-------------------------------------------------------------------------------

public class Graph {

    int[][] a;
    int n;
    char v[];
    int deg[];

    Graph() {
        v = "ABCDEFGHIJKLMNOP".toCharArray();
        deg = new int[20];
        a = new int[20][20];
        n = 0;
    }

    void loadData(int k) {  //do not edit this function
        RandomAccessFile f;
        int i, j, x;
        String s;
        StringTokenizer t;
        a = new int[20][20];
        try {
            f = new RandomAccessFile("data.txt", "r");
            for (i = 0; i < k; i++) {
                f.readLine();
            }
            s = f.readLine();
            s = s.trim();
            n = Integer.parseInt(s);
            for (i = 0; i < n; i++) {
                s = f.readLine();
                s = s.trim();
                t = new StringTokenizer(s);
                for (j = 0; j < n; j++) {
                    x = Integer.parseInt(t.nextToken().trim());
                    a[i][j] = x;
                }
            }
            f.close();
        } catch (Exception e) {
        }

    }

    void dispAdj() {
        int i, j;
        for (i = 0; i < n; i++) {
            System.out.println();
            for (j = 0; j < n; j++) {
                System.out.printf("%4d", a[i][j]);
            }
        }
    }

    void fvisit(int i, RandomAccessFile f) throws Exception {
        f.writeBytes("  " + v[i]);
    }

    void fdispAdj(RandomAccessFile f) throws Exception {
        int i, j;
        f.writeBytes("n = " + n + "\r\n");
        for (i = 0; i < n; i++) {
            f.writeBytes("\r\n");
            for (j = 0; j < n; j++) {
                f.writeBytes("  " + a[i][j]);
            }
        }
        f.writeBytes("\r\n");
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

//===========================================================================
//(2)===YOU CAN EDIT OR EVEN ADD NEW FUNCTIONS IN THE FOLLOWING PART========
//===========================================================================
    void f1() throws Exception {
        loadData(1);
        String fname = "f1.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        depth(0, f);
        f.writeBytes("\r\n");
        //-------------------------------------------------------------------------------------
        /*You must keep statements pre-given in this function.
       Your task is to insert statements here, just after this comment,
       to complete the question in the exam paper.*/
        depth2(0, f);
        //-------------------------------------------------------------------------------------
        f.writeBytes("\r\n");
        f.close();
    }

//=================================================================
    void f2() throws Exception {
        loadData(13);
        String fname = "f2.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        f.writeBytes("\r\n");
        //-------------------------------------------------------------------------------------
        /*You must keep statements pre-given in this function.
       Your task is to insert statements here, just after this comment,
       to complete the question in the exam paper.*/
        // You can use the statement fvisit(i,f); i = 0, 1, 2,...,n-1 to display the vertex i to file f2.txt 
        //  and statement f.writeBytes(" " + k); to write  variable k to the file f2.txt  
        dijkstra2(0, 6, f);
        dijkstra(2, 5, f);
        //-------------------------------------------------------------------------------------
        f.writeBytes("\r\n");
        f.close();
    }

    int depth2(boolean[] visited, int k, int count, RandomAccessFile f) throws Exception {
        count++;
        if (count >= 2 && count <= 6) {
            fvisit(k, f);
        }
        visited[k] = true;
        for (int i = 0; i < n; i++) {
            if (!visited[i] && a[k][i] > 0) {
                count = depth2(visited, i, count, f);
            }
        }
        return count;
    }

    void depth2(int k, RandomAccessFile f) throws Exception {
        boolean[] visited = new boolean[20];
        int i;
        for (i = 0; i < n; i++) {
            visited[i] = false;
        }
        int count = 0;
        count = depth2(visited, k, count, f);
        for (i = 0; i < n; i++) {
            if (!visited[i]) {
                count = depth2(visited, i, count,f);
            }
        }
    }

    void dijkstra(int k, int h, RandomAccessFile f) throws Exception {
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
        for (int j = 0; j < size; j++) {
            fvisit(arr[j], f);
        }
        f.writeBytes("\r\n");
    }

    void dijkstra2(int k, int h, RandomAccessFile f) throws Exception {
        boolean[] checked = new boolean[n];
        int[] cost = new int[n];
        int[] pre = new int[n];
        for (int i = 0; i < n; i++) {
            checked[i] = false;
            cost[i] = 99;
            pre[i] = -1;
        }
        int[] tracking = new int[n];
        int siz = 0;
        cost[k] = 0;
        int u = -1;
        for (int i = 0; i < n; i++) {
            int min = 99;
            for (int j = 0; j < n; j++) {
                if (checked[j] == false && cost[j] < min) {
                    min = cost[j];
                    u = j;
                }
            }
            checked[u] = true;
            tracking[siz++] = u;
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
        for (int j = 0; j < size; j++) {
            fvisit(arr[j], f);
        }
        f.writeBytes("\r\n");
        int ind = -1;
        for (int j = 0; j < siz; j++) {
            if (tracking[j] == h) {
                ind = j;
            }
        }
        for (int j = 0; j <= ind; j++) {
            if (j > ind - 3) {
                fvisit(tracking[j], f);
            }
        }
        f.writeBytes("\r\n");
    }
}
