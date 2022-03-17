/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph;

/**
 *
 * @author Admin
 */
public class TestGraph {

    public static void main(String[] args) {
        int INF = Integer.MAX_VALUE;
//        int[][] input = {
//            {0, 1, 1, 0, 0, 0, 0, 0, 0, 0},
//            {1, 0, 1, 0, 1, 0, 0, 0, 0, 0},
//            {1, 1, 0, 0, 0, 0, 1, 0, 0, 0},
//            {0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
//            {0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
//            {0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
//            {0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
//            {0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
//            {0, 0, 0, 0, 0, 1, 0, 0, 0, 0},
//            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},};
        int[][] input = {
            {0, 1, 0, 0, 1},
            {1, 0, 1, 1, 1},
            {0, 1, 0, 1, 0},
            {0, 1, 1, 0, 2},
            {1, 1, 0, 2, 0}
        };
//        int[][] input = {
//            {0, 2, 0, 0, 1},
//            {2, 0, 1, 1, 1},
//            {0, 1, 0, 1, 0},
//            {0, 1, 1, 0, 2},
//            {1, 1, 0, 2, 0}
//        };
//        System.out.println(input.length);
//        int[][] input = {
//            {0, 10, 99, 99, 99, 99, 8},
//            {99, 0, 2, 10, 25, 80, 99},
//            {30, 99, 0, 8, 3, 20, 99},
//            {20, 99, 99, 0, 99, 5, 10},
//            {99, 99, 99, 4, 0, 99, 99},
//            {8, 99, 99, 99, 99, 0, 5},
//            {8, 99, 99, 99, 99, 99, 0}
//        };
        MyGraph mg = new MyGraph();
        mg.setData(input);
        mg.euler(0);
//        mg.euler(0);
//        mg.dijkstra(0, 5);
//        mg.checkEulerCycle();
//        mg.floyd();
//        System.out.println(mg.connectedComponent());
//        mg.display();
//        mg.breadthFirst(0);
//        mg.depthFirst(5);
//        mg.prim(0);
//        mg.kruskal();
//        System.out.println("");
//        mg.euler(5);
    }
}
